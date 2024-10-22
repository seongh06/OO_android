package ggum.oo.presentation.community

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.databinding.FragmentCommunityBinding
import ggum.oo.databinding.FragmentMypageBinding
import ggum.oo.databinding.FragmentSearchResultBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.presentation.search.ContentVPA

@AndroidEntryPoint
class CommunityFragment : BaseFragment<FragmentCommunityBinding>(R.layout.fragment_community) {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var communityVPA: CommunityVPA

    override fun initView() {
        tabLayout = binding.tabCommunityCategory
        viewPager = binding.vpCommunityList

        setupViewPager()
    }

    private fun setupViewPager() {
        communityVPA = CommunityVPA(this)
        viewPager.adapter = communityVPA

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "통합"
                1 -> "교내"
                2 -> "교외"
                else -> null
            }
        }.attach()
    }

    override fun initObserver() {

    }
}
