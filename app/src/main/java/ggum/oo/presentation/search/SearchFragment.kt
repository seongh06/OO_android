package ggum.oo.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.databinding.FragmentSearchBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.presentation.search.SearchHistoryRVA

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private lateinit var adapter: SearchHistoryRVA
    private val searchHistory = listOf(
        "첫 번째 검색어",
        "두 번째 검색어",
        "세 번째 검색어",
        "네 번째 검색어",
        "다섯 번째 검색어"
    )

    override fun initView() {
        // RecyclerView 설정
        adapter = SearchHistoryRVA(searchHistory)
        binding.rvSearchHistory.adapter = adapter

        // LinearLayoutManager 설정
        binding.rvSearchHistory.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun initObserver() {
        // 관찰할 데이터가 있다면 여기에 추가
    }
}
