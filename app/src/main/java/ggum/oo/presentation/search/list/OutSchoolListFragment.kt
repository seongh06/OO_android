package ggum.oo.presentation.search.list

import androidx.recyclerview.widget.LinearLayoutManager
import ggum.oo.R
import ggum.oo.data.ContentData
import ggum.oo.data.service.ContentList
import ggum.oo.databinding.FragmentPostListBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.presentation.search.ContentRVA

class OutSchoolListFragment : BaseFragment<FragmentPostListBinding>(R.layout.fragment_post_list) {

    private lateinit var contentRVA: ContentRVA

    override fun initObserver() {

    }

    override fun initView() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val contentItems = ContentList.items.filter { !it.area }
        contentRVA = ContentRVA(contentItems)
        binding.rvPostList.apply {
            adapter = contentRVA
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}