package ggum.oo.presentation.community.list

import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.data.service.CommunityList
import ggum.oo.data.service.ContentList
import ggum.oo.databinding.FragmentPostListBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.presentation.community.CommunityRVA
import ggum.oo.presentation.community.CommunityVPA
import ggum.oo.presentation.search.ContentRVA

@AndroidEntryPoint
class InSchoolCommunityListFragment  : BaseFragment<FragmentPostListBinding>(R.layout.fragment_post_list) {

    private lateinit var communityRVA: CommunityRVA
    override fun initObserver() {

    }

    override fun initView() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val communityItems = CommunityList.items.filter { it.area }
        communityRVA = CommunityRVA(communityItems)
        binding.rvPostList.apply {
            adapter = communityRVA
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}