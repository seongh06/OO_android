package ggum.oo.presentation.mypage

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.service.autofill.UserData
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ggum.oo.data.ClubItem
import ggum.oo.data.ClubRequestItem
import ggum.oo.data.dto.response.MypageResponseDto
import ggum.oo.domain.model.request.ClubManagerRequestModel
import ggum.oo.domain.model.request.ClubMypageRequestModel
import ggum.oo.domain.model.request.ClubRequestModel
import ggum.oo.domain.model.response.MypageResponseModel
import ggum.oo.domain.repository.LoginRepository
import ggum.oo.domain.repository.MypageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MypageViewModel @Inject constructor(
    private val spf: SharedPreferences,
    application: Application,
    private val repository: MypageRepository
):AndroidViewModel(application) {
    private val _clubList = MutableLiveData<List<ClubItem>>(emptyList())
    val clubList: LiveData<List<ClubItem>> get() = _clubList

    private val _clubRequestList = MutableLiveData<List<ClubRequestItem>>(emptyList())
    val clubRequestList: LiveData<List<ClubRequestItem>> get() = _clubRequestList

    fun ClubReject(email:String, clubName: String){
        val currentClubNameValue = currentClubName.value ?: return // null인 경우 함수 종료

        viewModelScope.launch{
            repository.clubReject(ClubMypageRequestModel(email,currentClubNameValue))
                .onSuccess {
                    Log.d("d","d")
                }.onFailure { error->
                    Log.e("ee","$error")
                }
        }
    }

    fun ClubAccept(email: String, studentId: String) {
        val currentClubNameValue = currentClubName.value ?: return // null 체크
        viewModelScope.launch {
            try {
                repository.clubAccept(ClubMypageRequestModel(email, currentClubNameValue))
                    .onSuccess {
                        Log.d("ClubAccept", "Request successful")
                    }.onFailure { error ->
                        Log.e("ClubAccept", "Error: $error")
                    }
            } catch (e: Exception) {
                Log.e("ClubAccept", "Exception occurred: ${e.message}", e)
            }
        }
    }


    fun ClubRequest(clubName: String, studentId: String, name: String) {
        viewModelScope.launch {
            repository.clubRequest(ClubRequestModel(clubName, studentId, name))
                .onSuccess {
                    Log.d("d", "Request successful")
                }.onFailure { error ->
                    Log.e("ee", "$error")
                }
        }
    }

    private val _userData = MutableLiveData<MypageResponseModel>() // MypageResponse 타입으로 설정
    val userData: LiveData<MypageResponseModel> get() = _userData

    private val _currentClubName = MutableLiveData<String>().apply { value = "동아리 없음"}
    val currentClubName: LiveData<String> get() = _currentClubName
    fun updateCurrentClubName(clubName: String) {
        _currentClubName.value = clubName
    }

    // 사용자 정보 가져오기
    fun MyInform() {
        viewModelScope.launch {
            repository.mypage().onSuccess { response ->
                _userData.value = response
                updateCurrentClubName(response.currentClubName ?: "동아리 없음") // null 체크 후 업데이트
            }.onFailure { error ->
                Log.e("MypageViewModel", "Error fetching user data: $error")
                // 에러 처리 로직 추가 (예: 사용자에게 메시지 표시)
            }
        }
    }

    fun showAlert(context: Context, message: String) {
        AlertDialog.Builder(context)
            .setTitle("알림")
            .setMessage(message)
            .setPositiveButton("확인") { dialog, _ -> dialog.dismiss() }
            .show()
    }

    fun ClubManager(email: String, clubName: String, context: Context) {
        viewModelScope.launch {
            try {
                val result = repository.clubManager(ClubManagerRequestModel(email, clubName))

                result.fold(
                    onSuccess = { response ->
                        showAlert(context, "동아리 운영자 인증 성공")
                    },
                    onFailure = { error ->
                        showAlert(context, "동아리 운영자 인증 성공")
                    }
                )
            } catch (e: Exception) {
                showAlert(context, "오류 발생: ${e.localizedMessage}")
            }
        }
    }
}

