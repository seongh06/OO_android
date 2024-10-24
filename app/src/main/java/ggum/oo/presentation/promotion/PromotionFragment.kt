package ggum.oo.presentation.promotion

import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.data.ContentList
import ggum.oo.databinding.FragmentPromotionBinding
import ggum.oo.databinding.FragmentSearchResultBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.presentation.search.ContentVPA
import ggum.oo.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class PromotionFragment : BaseFragment<FragmentPromotionBinding>(R.layout.fragment_promotion) {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2
    private lateinit var promotionVPA: PromotionVPA
    private val navigator by lazy { findNavController() }

    override fun initView() {
        tabLayout = binding.tabSearchResultCategory
        viewPager = binding.vpSearchResultList

        setupViewPager()
        goToWritePromotion()
    }

    private fun setupViewPager() {

        val contentItems = ContentList.items // 필요한 데이터를 가져옵니다.

        promotionVPA = PromotionVPA(this, contentItems)
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

    private fun goToWritePromotion() {
        binding.fabPromotionWriteBtn.setOnSingleClickListener {
            navigator.navigate(R.id.action_promotionFragment_to_promotionWriteFragment)
        }
    }
}
