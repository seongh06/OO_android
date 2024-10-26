package ggum.oo.presentation.login

import android.app.Application
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.ContactsContract.CommonDataKinds.Nickname
import android.provider.Settings.Global.putString
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import dagger.hilt.android.lifecycle.HiltViewModel
import ggum.oo.data.dto.BaseResponse
import ggum.oo.data.dto.NoneBaseResponse
import ggum.oo.data.dto.request.LoginRequestDto
import ggum.oo.data.dto.request.SignUpLoginRequestDto
import ggum.oo.data.service.LoginService
import ggum.oo.domain.model.request.AuthRequestModel
import ggum.oo.domain.model.request.LoginRequestModel
import ggum.oo.domain.model.request.SignUpLoginRequestModel
import ggum.oo.domain.model.response.LoginResponseModel
import ggum.oo.domain.repository.LoginRepository
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import org.json.JSONException
import org.json.JSONObject
import timber.log.Timber
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val loginService: LoginService,
    private val spf: SharedPreferences,
    application: Application,
    private val repository: LoginRepository
) : AndroidViewModel(application) {
    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    private val _phone = MutableLiveData<String>()
    private val _nickname = MutableLiveData<String>()
    private val _authCode = MutableLiveData<String>()
    val authCode: LiveData<String> get() = _authCode
    val email: LiveData<String> get() = _email
    val password: LiveData<String> get() = _password
    val phone: LiveData<String> get() = _phone
    val nickname: LiveData<String> get() = _nickname
    val signupStatus = MutableLiveData<Result<Unit>>()
    private val _validNicknameStatus = MutableLiveData<Boolean>()
    val validNicknameStatus: LiveData<Boolean> get() = _validNicknameStatus
    private val _validEmailStatus = MutableLiveData<Boolean>()
    val validEmailStatus: LiveData<Boolean> get() = _validEmailStatus
    val authenticationStatus: LiveData<Boolean> get() = _authenticationStatus // 올바른 getter 구문
    private val _authenticationStatus = MutableLiveData<Boolean>()
    val _authCodeStatus = MutableLiveData<Boolean>()
    private val _navigateToSignupEmail = MutableLiveData<Boolean>()
    val navigateToSignupEmail: LiveData<Boolean> get() = _navigateToSignupEmail



    fun updateEmail(newEmail: String) {
        _email.value = newEmail
    }

    fun updatePassword(newPassword: String) {
        _password.value = newPassword
    }

    fun updatePhone(newPhone: String) {
        _phone.value = newPhone
    }

    fun updateNickname(newNickname: String) {
        _nickname.value = newNickname
    }

    fun signUp() {
        val signupRequest = SignUpLoginRequestModel(
            _email.value ?: "",
            _password.value ?: "",
            _phone.value ?: "",
            _nickname.value ?: ""
        )

        viewModelScope.launch {
            sendSignupData(signupRequest)
        }
    }

    fun authCode(email: String) {
        viewModelScope.launch {
            repository.authCode(email)
                .onSuccess {
                    Timber.d("AuthCode", "인증 코드가 성공적으로 전송되었습니다.")
                }.onFailure { error->
                    Timber.e("AuthCode","$error")
                }
        }
    }

    fun validNickname(nickname: String) {
        viewModelScope.launch {
            repository.validNickname(nickname)
                .onSuccess { response ->
                    _validNicknameStatus.value = (response.code == "COM-000")
                }
                .onFailure { error ->
                    Timber.e(error)
                    _validNicknameStatus.value = false
                }
        }
    }

    fun validEmail(email: String) {
        viewModelScope.launch {
            repository.validEmail(email)
                .onSuccess { response ->
                    when (response.code) {
                        "COM-000" -> {
                            _validEmailStatus.value = true
                        }
                        "MEM-999" -> {
                            _navigateToSignupEmail.value = true // Fragment로 이동 신호
                        }
                        else -> {
                            _validEmailStatus.value = false
                        }
                    }
                }
                .onFailure { error ->
                    Timber.e(error)
                    _validEmailStatus.value = false
                }
        }
    }



    private suspend fun sendSignupData(signupRequest: SignUpLoginRequestModel) {
        repository.signUp(signupRequest).onSuccess {
            spf.edit().apply {
                putString("email", signupRequest.email)
                putString("password", signupRequest.password)
                putString("phoneNumber", signupRequest.phoneNumber)
                putString("nickname", signupRequest.nickname)
                apply()
            }
            signupStatus.postValue(Result.success(Unit)) // 성공 시 LiveData 업데이트
        }.onFailure {
            Timber.e("API call failed: ${it.message}")
            signupStatus.postValue(Result.failure(it)) // 실패 시 LiveData 업데이트
        }
    }

    fun authentication(authCode: String) {
        val email = _email.value ?: ""
        val authRequest = AuthRequestModel(email, authCode)
        viewModelScope.launch {
            repository.authentication(authRequest)
                .onSuccess { response ->
                    _authenticationStatus.value = (response.code == "COM-000")
                }
                .onFailure { error ->
                    _authenticationStatus.value = false
                }
        }
    }

    private val _loginResult = MutableLiveData<Result<LoginResponseModel>>()
    val loginResult: LiveData<Result<LoginResponseModel>> get() = _loginResult

    private val _loginStatus = MutableLiveData<Boolean>()
    val loginStatus: LiveData<Boolean> get() = _loginStatus

    fun login(request: LoginRequestModel) {
        viewModelScope.launch {
            // 로그인 메서드 호출
            val result = repository.login(request)

            // 결과를 LiveData에 저장
            _loginResult.value = result

            // 결과 처리
            result.onSuccess { loginResponse ->
                // SharedPreferences에서 accessToken 가져오기
                val accessToken = getAccessToken() // SharedPreferences에서 가져옴
                Timber.d("로그인 성공, accessToken 가져오기: $accessToken")

                if (!accessToken.isNullOrEmpty()) {
                    // accessToken을 SharedPreferences에 저장
                    spf.edit().putString("jwt", accessToken).apply()
                    Timber.d("accessToken 저장 완료: $accessToken")
                    _loginStatus.value = true // 로그인 성공
                } else {
                    Timber.e("accessToken이 저장되어 있지 않습니다.") // 에러 로그
                    _loginStatus.value = false // 로그인 실패
                }
            }.onFailure { error ->
                Timber.e("로그인 실패: ${error.message}") // 실패 로그
                _loginStatus.value = false // 로그인 실패
            }
        }
    }

    private fun getAccessToken(): String? {
        return spf.getString("accessToken", null) // SharedPreferences에서 accessToken 가져오기
    }

    private fun saveAccessToken(token: String) {
        spf.edit().apply {
            putString("accessToken", token)
            apply()
        }
    }
}