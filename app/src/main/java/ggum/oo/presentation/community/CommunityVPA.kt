package ggum.oo.presentation.community

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ggum.oo.presentation.community.list.AllCommunityListFragment
import ggum.oo.presentation.community.list.InSchoolCommunityListFragment
import ggum.oo.presentation.community.list.OutSchoolCommunityListFragment
import ggum.oo.presentation.search.list.AllListFragment
import ggum.oo.presentation.search.list.InSchoolListFragment
import ggum.oo.presentation.search.list.OutSchoolListFragment


class CommunityVPA(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> AllCommunityListFragment()
            1 -> InSchoolCommunityListFragment()
            2 -> OutSchoolCommunityListFragment()
            else -> throw IllegalStateException("Invalid position")
        }
    }
}