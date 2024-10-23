package ggum.oo.presentation.community

import androidx.navigation.fragment.findNavController
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
import ggum.oo.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class CommunityFragment : BaseFragment<FragmentCommunityBinding>(R.layout.fragment_community) {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var communityVPA: CommunityVPA
    private val navigator by lazy { findNavController() }

    override fun initView() {
        tabLayout = binding.tabCommunityCategory
        viewPager = binding.vpCommunityList

        setupViewPager()
        goToWriteCommunity()
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

    private fun goToWriteCommunity() {
        binding.fabCommunityWriteBtn.setOnSingleClickListener {
            navigator.navigate(R.id.action_communityFragment_to_communityWriteFragment)
        }
    }

    override fun initObserver() {

    }
}
