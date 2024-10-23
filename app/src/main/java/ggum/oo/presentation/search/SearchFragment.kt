package ggum.oo.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.databinding.FragmentSearchBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.presentation.search.SearchHistoryRVA
import ggum.oo.presentation.search.SearchViewModel
import ggum.oo.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private lateinit var adapter: SearchHistoryRVA
    private val navigator by lazy { findNavController() }
    private val viewModel : SearchViewModel by activityViewModels()


    private val searchHistory = listOf(
        "첫 번째 검색어",
        "두 번째 검색어",
        "세 번째 검색어",
        "네 번째 검색어",
        "다섯 번째 검색어"
    )

    override fun initView() {
        binding.etSearchBlock.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                viewModel.searchResult.value = s.toString()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        adapter = SearchHistoryRVA(searchHistory)
        binding.rvSearchHistory.adapter = adapter

        // LinearLayoutManager 설정
        binding.rvSearchHistory.layoutManager = LinearLayoutManager(requireContext())

        binding.ivSearchButton.setOnSingleClickListener {
            navigator.navigate(R.id.action_searchFragment_to_searchResultFragment)
        }
    }

    override fun initObserver() {
        // 관찰할 데이터가 있다면 여기에 추가
    }
}
