package ggum.oo.presentation.search.list

import android.os.Bundle
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.data.ContentItem
import ggum.oo.data.ContentList
import ggum.oo.databinding.FragmentPostListBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.presentation.search.ContentRVA

@AndroidEntryPoint
class InSchoolListFragment : BaseFragment<FragmentPostListBinding>(R.layout.fragment_post_list) {

    private lateinit var contentRVA: ContentRVA
    private var contentList: List<ContentItem> = listOf() // 전체 데이터 리스트
    private val navigator by lazy { findNavController() }

    override fun initObserver() {

    }

    override fun initView() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        contentList = ContentList.items.filter {  it.area  }
        contentRVA = ContentRVA(contentList) { contentItem ->
            onContentItemClick(contentItem) // 클릭 처리
        }
        binding.rvPostList.apply {
            adapter = contentRVA
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun onContentItemClick(contentItem: ContentItem) {
        val bundle = Bundle().apply {
            putInt("id", contentItem.id) // 클릭된 아이템의 ID를 Bundle에 추가
            putInt("category", if (contentItem.category) 0 else 1) // category 값을 추가 (true: community, false: promotion)
        }

        // 카테고리에 따라 다른 Fragment로 네비게이션
        if (!contentItem.category) { // category가 true인 경우
            navigator.navigate(R.id.action_searchResultFragment_to_communityPostFragment, bundle)
        } else { // category가 false인 경우
            navigator.navigate(R.id.action_searchResultFragment_to_promotionPostFragment, bundle)
        }
    }
}