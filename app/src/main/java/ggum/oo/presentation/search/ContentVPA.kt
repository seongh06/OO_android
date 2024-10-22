package ggum.oo.presentation.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ggum.oo.R
import ggum.oo.presentation.search.list.AllListFragment
import ggum.oo.presentation.search.list.FavoriteListFragment
import ggum.oo.presentation.search.list.InSchoolListFragment
import ggum.oo.presentation.search.list.OutSchoolListFragment

class ContentVPA(fragment: Fragment): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> AllListFragment()
            1 -> InSchoolListFragment()
            2 -> OutSchoolListFragment()
            3 -> FavoriteListFragment()
            else -> throw IllegalStateException("Invalid position")
        }
    }
}