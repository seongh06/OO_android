package ggum.oo.presentation.home

import androidx.navigation.NavController
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.databinding.FragmentHomeBinding
import ggum.oo.presentation.base.BaseFragment

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var navController: NavController
    override fun initView() {
        banner()
    }

    override fun initObserver() {

    }

    private fun banner() {
        val bannerVPA = BannerVPA(this)
        bannerVPA.addFragment(BannerFragment(R.drawable.img_main_page_banner))
        bannerVPA.addFragment(BannerFragment(R.drawable.img_mainpage_ad))
        bannerVPA.addFragment(BannerFragment(R.drawable.img_example_content))
        binding.vpMainpageBanner.adapter = bannerVPA
        binding.vpMainpageBanner.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        val banner = binding.banner
        banner.setViewPager(binding.vpMainpageBanner)
        autoSlide()
    }

    private val sliderHandler = Handler(Looper.getMainLooper())
    private var sliderRunnable: Runnable? = null

    private fun autoSlide() {
        sliderRunnable = Runnable {
            val viewPager = binding.vpMainpageBanner
            viewPager.adapter?.let { adapter ->
                viewPager.currentItem =
                    if (viewPager.currentItem < adapter.itemCount - 1) {
                        viewPager.currentItem + 1
                    } else {
                        0
                    }
            }
            sliderHandler.postDelayed(sliderRunnable!!, 3000)
        }
        sliderHandler.post(sliderRunnable!!)
    }
}