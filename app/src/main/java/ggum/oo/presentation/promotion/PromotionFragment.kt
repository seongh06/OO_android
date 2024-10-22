package ggum.oo.presentation.promotion

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.databinding.FragmentPromotionBinding
import ggum.oo.databinding.FragmentSearchResultBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.presentation.search.ContentVPA

@AndroidEntryPoint
class PromotionFragment : BaseFragment<FragmentPromotionBinding>(R.layout.fragment_promotion) {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var promotionVPA: PromotionVPA

    override fun initView() {
        tabLayout = binding.tabSearchResultCategory
        viewPager = binding.vpSearchResultList

        setupViewPager()
    }

    private fun setupViewPager() {
        promotionVPA = PromotionVPA(this)
        viewPager.adapter = promotionVPA

        // TabLayout과 ViewPager2 연결
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "통합"
                1 -> "교내"
                2 -> "교외"
                3 -> "관심"
                else -> null
            }
        }.attach()
    }

    override fun initObserver() {

    }
}
