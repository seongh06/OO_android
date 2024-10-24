package ggum.oo.presentation.community

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ggum.oo.data.service.ContentItem
import ggum.oo.presentation.community.list.AllCommunityListFragment
import ggum.oo.presentation.community.list.InSchoolCommunityListFragment
import ggum.oo.presentation.community.list.OutSchoolCommunityListFragment
import ggum.oo.presentation.search.list.AllListFragment
import ggum.oo.presentation.search.list.InSchoolListFragment
import ggum.oo.presentation.search.list.OutSchoolListFragment


class CommunityVPA(fragment: Fragment, private val contentItems: List<ContentItem>) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3 // 예: 3개의 탭

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> AllCommunityListFragment.newInstance(contentItems) // 통합 탭
            1 -> InSchoolCommunityListFragment.newInstance(contentItems) // 교내 탭
            2 -> OutSchoolCommunityListFragment.newInstance(contentItems) // 교외 탭
            else -> throw IllegalStateException("Invalid position")
        }
    }
}
