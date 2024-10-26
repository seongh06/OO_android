package ggum.oo.presentation.home

import androidx.navigation.NavController
import androidx.viewpager2.widget.ViewPager2
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.data.ContentList
import ggum.oo.databinding.FragmentHomeBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    private val sliderHandler = Handler(Looper.getMainLooper())
    private var sliderRunnable: Runnable? = null
    private lateinit var homeCommunityRVA: HomeCommunityRVA
    private lateinit var homePromotionRVA: HomePromotionRVA
    private val navigator by lazy { findNavController() }

    override fun initObserver() {
        // 필요에 따라 옵저버 설정
    }

    override fun initView() {
        setupBanner() // binding이 초기화된 후 호출
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val homecontentItems = ContentList.items // 전체 리스트 가져오기
        val filteredcommunityItems = homecontentItems.filter { !it.category }
        val filteredpromotionItems = homecontentItems.filter { it.category }
        homeCommunityRVA = HomeCommunityRVA(filteredcommunityItems.take(3))
        homePromotionRVA = HomePromotionRVA(filteredpromotionItems.take(3))
        binding.rvHomeCommunity.apply {
            adapter = homeCommunityRVA
            layoutManager = LinearLayoutManager(requireContext())
        }
        binding.rvHomePromotion.apply {
            adapter = homePromotionRVA
            layoutManager = LinearLayoutManager(requireContext())
        }
        binding.ivSearchBarSearchIcon.setOnSingleClickListener {
            navigator.navigate(R.id.action_homeFragment_to_searchResultFragment)
        }
    }

    private fun setupBanner() {
        // binding이 null인지 확인
        if (binding == null) return // binding이 null이면 실행 중지

        val bannerAdapter = BannerVPA(this)
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_example_aespa))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_example_lion))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_example_gdsc))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_example_umc))
        bannerAdapter.addFragment(BannerFragment(R.drawable.img_example_goorm))

        binding.vpHomeBanner.adapter = bannerAdapter
        binding.vpHomeBanner.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        val indicator = binding.ciHomeBanner
        indicator.setViewPager(binding.vpHomeBanner)

        autoSlide() // autoSlide 호출
    }

    private fun autoSlide() {
        sliderRunnable = Runnable {
            // binding이 null인지 확인
            if (binding == null) return@Runnable // binding이 null이면 실행 중지

            val viewPager = binding.vpHomeBanner
            viewPager.adapter?.let { adapter ->
                viewPager.currentItem =
                    if (viewPager.currentItem < adapter.itemCount - 1) {
                        viewPager.currentItem + 1
                    } else {
                        0
                    }
            }

            // 다음 슬라이드를 위해 다시 Runnable을 post
            sliderHandler.postDelayed(sliderRunnable!!, 3000)
        }

        // Runnable을 첫 번째로 post
        sliderHandler.post(sliderRunnable!!)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("fragment onDestroyView", "onDestroyView")
        sliderHandler.removeCallbacks(sliderRunnable!!) // Runnable 제거
    }
}
