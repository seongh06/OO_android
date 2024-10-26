package ggum.oo.presentation.promotion

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import ggum.oo.R
import ggum.oo.data.ContentItem
import ggum.oo.presentation.promotion.list.AllPromotionListFragment
import ggum.oo.presentation.promotion.list.FavoritePromotionListFragment
import ggum.oo.presentation.promotion.list.InSchoolPromotionListFragment
import ggum.oo.presentation.promotion.list.OutSchoolPromotionListFragment
import ggum.oo.presentation.search.list.AllListFragment
import ggum.oo.presentation.search.list.FavoriteListFragment
import ggum.oo.presentation.search.list.InSchoolListFragment
import ggum.oo.presentation.search.list.OutSchoolListFragment

class PromotionVPA(fragment: Fragment, private val contentItems: List<ContentItem>): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> AllPromotionListFragment.newInstance(contentItems)
            1 -> InSchoolPromotionListFragment.newInstance(contentItems)
            2 -> OutSchoolPromotionListFragment.newInstance(contentItems)
            3 -> FavoritePromotionListFragment.newInstance(contentItems)
            else -> throw IllegalStateException("Invalid position")
        }
    }
}