package ggum.oo.presentation.search

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ggum.oo.domain.model.response.ContentListResponseModel
import ggum.oo.domain.repository.ContentRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContentViewModel @Inject constructor(
    private val spf: SharedPreferences,
    application: Application,
    private val repository: ContentRepository
): AndroidViewModel(application) {

    private val _contentDetails = MutableLiveData<Result<ContentListResponseModel>>()
    val contentDetails: LiveData<Result<ContentListResponseModel>> get() = _contentDetails

    fun fetchContentDetails(postId: Int) {
        viewModelScope.launch {
            repository.contentList(postId).onSuccess { response ->
                _contentDetails.value = Result.success(response)
            }.onFailure { error ->
                Log.e("CommunityViewModel", "Error fetching content details: ${error.localizedMessage}")
            }
        }
    }
}
