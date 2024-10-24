package ggum.oo.presentation.promotion.list

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.data.ContentList
import ggum.oo.databinding.FragmentPostListBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.presentation.search.ContentRVA

@AndroidEntryPoint
class OutSchoolPromotionListFragment  : BaseFragment<FragmentPostListBinding>(R.layout.fragment_post_list) {

    private lateinit var contentRVA : ContentRVA
    override fun initObserver() {

    }

    override fun initView() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val communityItems = ContentList.items.filter { ! it.area &&  it.category}
        contentRVA = ContentRVA(communityItems) { item ->
            // 클릭 시 수행할 작업
            Log.d("InSchoolPromotion", "Clicked item: ${item.id}")
            // 필요한 네비게이션 처리 추가
        }
        binding.rvPostList.apply {
            adapter = contentRVA
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}