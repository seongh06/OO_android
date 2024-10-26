package ggum.oo.presentation.home

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import ggum.oo.data.ContentItem
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val spf: SharedPreferences,
    application: Application
) : AndroidViewModel(application) {

    private val _items = MutableLiveData<List<ContentItem>>()
    val items: LiveData<List<ContentItem>> get() = _items

    fun loadItems() {
        _items.value = listOf(/* 데이터 삽입 */)
    }
}