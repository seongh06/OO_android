package ggum.oo.presentation.search.list

import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.data.ContentData
import ggum.oo.data.service.ContentItem
import ggum.oo.data.service.ContentList
import ggum.oo.databinding.FragmentPostListBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.presentation.search.ContentRVA
import ggum.oo.presentation.search.SearchViewModel

@AndroidEntryPoint
class AllListFragment  : BaseFragment<FragmentPostListBinding>(R.layout.fragment_post_list) {

    private lateinit var contentRVA: ContentRVA
    private val viewModel: SearchViewModel by activityViewModels() // ViewModel 가져오기
    private var contentList: List<ContentItem> = listOf() // 전체 데이터 리스트
    override fun initObserver() {
        viewModel.searchResult.observe(viewLifecycleOwner) { query ->
            filterContent(query)
        }
    }

    override fun initView() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val contentItems = ContentList.items // 전체 리스트 가져오기
        contentRVA = ContentRVA(contentItems)
        binding.rvPostList.apply {
            adapter = contentRVA
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun filterContent(query: String) {
        val filteredList = if (query.isEmpty()) {
            contentList // 검색어가 없으면 전체 리스트 반환
        } else {
            contentList.filter {
                it.title.contains(query, ignoreCase = true) || it.body.contains(query, ignoreCase = true)
            }
        }
        contentRVA.updateList(filteredList) // 필터링된 리스트로 업데이트
    }
}