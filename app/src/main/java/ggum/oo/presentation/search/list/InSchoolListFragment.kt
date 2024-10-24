package ggum.oo.presentation.search.list

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import ggum.oo.R
import ggum.oo.data.ContentData
import ggum.oo.data.service.ContentList
import ggum.oo.databinding.FragmentPostListBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.presentation.search.ContentRVA

class InSchoolListFragment : BaseFragment<FragmentPostListBinding>(R.layout.fragment_post_list) {

    private lateinit var contentRVA: ContentRVA

    override fun initObserver() {

    }

    override fun initView() {
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val contentItems = ContentList.items.filter { it.area }
        contentRVA = ContentRVA(contentItems) { item ->
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