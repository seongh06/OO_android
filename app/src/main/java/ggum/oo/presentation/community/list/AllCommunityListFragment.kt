package ggum.oo.presentation.community.list

import android.os.Bundle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.data.service.ContentItem
import ggum.oo.data.service.ContentList
import ggum.oo.databinding.FragmentPostListBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.presentation.search.ContentRVA

@AndroidEntryPoint
class AllCommunityListFragment  : BaseFragment<FragmentPostListBinding>(R.layout.fragment_post_list) {

    private lateinit var contentItems: List<ContentItem>
    private lateinit var contentRVA: ContentRVA
    private val navigator by lazy { findNavController() }

    companion object {
        fun newInstance(contentItems: List<ContentItem>): AllCommunityListFragment {
            val fragment = AllCommunityListFragment()
            val args = Bundle().apply {
                putParcelableArrayList("contentItems", ArrayList(contentItems)) // Parcelable로 변환하여 전달
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            contentItems = it.getParcelableArrayList("contentItems") ?: emptyList() // 데이터 수신
        }
    }

    override fun initObserver() {

    }

    override fun initView() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val communityItems = ContentList.items.filter { ! it.category }
        contentRVA = ContentRVA(communityItems) { contentItem ->
            onContentItemClick(contentItem) // 클릭 처리
        }

        binding.rvPostList.apply {
            adapter = contentRVA
            layoutManager = LinearLayoutManager(requireContext())
        }
    }


    private fun onContentItemClick(item: ContentItem) {
        val bundle = Bundle().apply {
            putInt("id", item.id) // ID를 Bundle에 추가
        }
        navigator.navigate(R.id.communityPostFragment, bundle) // Bundle을 사용하여 네비게이션
    }
}