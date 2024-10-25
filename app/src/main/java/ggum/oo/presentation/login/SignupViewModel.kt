package ggum.oo.presentation.login

import android.app.Application
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.ContactsContract.CommonDataKinds.Nickname
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import ggum.oo.data.dto.NoneBaseResponse
import ggum.oo.data.dto.request.SignUpLoginRequestDto
import ggum.oo.domain.model.request.SignUpLoginRequestModel
import ggum.oo.domain.repository.LoginRepository
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import timber.log.Timber
import java.io.ByteArrayOutputStream
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val spf: SharedPreferences,
    application: Application,
    private val repository: LoginRepository
) : AndroidViewModel(application) {
    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    private val _phone = MutableLiveData<String>()
    private val _nickname = MutableLiveData<String>()
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

    fun authenticateEmail(email: String, authCode: String) {
        viewModelScope.launch {
            repository.authentication(email, authCode)
                .onSuccess {response ->
                    _authenticationStatus.value = (response.code == "COM-000")
                }.onFailure { error ->
                    Timber.e(error)
                    _authenticationStatus.value = false
                }
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
                    _validEmailStatus.value = (response.code == "COM-000")
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
}
