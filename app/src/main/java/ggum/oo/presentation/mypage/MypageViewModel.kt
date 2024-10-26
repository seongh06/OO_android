package ggum.oo.presentation.mypage

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ggum.oo.data.ClubItem
import ggum.oo.data.ClubRequestItem
import ggum.oo.domain.model.request.ClubMypageRequestModel
import ggum.oo.domain.repository.LoginRepository
import ggum.oo.domain.repository.MypageRepository
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

    fun loadClubs() {
        // 데이터 로드 로직 (예: 서버 호출)
        Log.d("MypageViewModel", "loadClubs called") // 로그 추가
        _clubList.value = listOf(
            ClubItem("김승혁", "202322008"),
            ClubItem("김승현", "202322009"),
            ClubItem("김승혐", "202322010"),
            ClubItem("김승헐", "202322011"),
            ClubItem("김승헉", "202322012"),
            ClubItem("김승힝", "202322013"),
            ClubItem("김승핑", "202322014"),
        )
    }

    fun loadClubRequests() {
        Log.d("MypageViewModel", "loadClubRequests called")
        _clubRequestList.value = listOf(
            ClubRequestItem("구름톤", 1),
            ClubRequestItem("GDG", 2),
            ClubRequestItem("UMC", 3),
            ClubRequestItem("멋쟁이사자처럼", 1),
        )
    }
    fun ClubReject(email:String, clubName: String){
        viewModelScope.launch{
            repository.clubReject(ClubMypageRequestModel(email,clubName))
                .onSuccess {
                    Log.d("d","d")
                }.onFailure { error->
                    Log.e("ee","$error")
                }
        }
    }

    fun ClubAccept(email:String, clubName: String){
        viewModelScope.launch{
            repository.clubAccept(ClubMypageRequestModel(email,clubName))
                .onSuccess {
                    Log.d("d","d")
                }.onFailure { error->
                    Log.e("ee","$error")
                }
        }
    }
}
