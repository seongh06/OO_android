package ggum.oo.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.data.ContentItem
import ggum.oo.data.ContentList // ContentList에서 모든 게시물 리스트를 가져온다고 가정
import ggum.oo.databinding.FragmentSearchBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.presentation.search.SearchHistoryRVA
import ggum.oo.presentation.search.SearchViewModel
import ggum.oo.util.extension.setOnSingleClickListener


@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private lateinit var historyAdapter: SearchHistoryRVA
    private val navigator by lazy { findNavController() }
    private val viewModel: SearchViewModel by activityViewModels()

    private val searchHistory = listOf(
        "GGUM 해커톤",
        "휴학",
        "공모전",
        "굥모전",
        "대외활동"
    )

    override fun initView() {
        binding.etSearchBlock.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                // 검색어 입력 시 처리할 내용
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        historyAdapter = SearchHistoryRVA(searchHistory)
        binding.rvSearchHistory.adapter = historyAdapter
        binding.rvSearchHistory.layoutManager = LinearLayoutManager(requireContext())

        binding.ivSearchButton.setOnSingleClickListener {
            val query = binding.etSearchBlock.text.toString()
            val action = SearchFragmentDirections.actionSearchFragmentToSearchResultFragment(query)
            navigator.navigate(action) // 검색어를 SearchResultFragment로 전달
        }

        binding.ivSearchBack.setOnSingleClickListener {
            navigator.navigateUp()
        }
    }

    override fun initObserver() {
        // 관찰할 데이터가 있다면 여기에 추가
    }
}
