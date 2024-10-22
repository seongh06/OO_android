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
    override fun initView(


    ) : View {

        val bannerAdapter = HomeBannerVPA(this)
        bannerAdapter.addFragment(HomeBannerFragment())
        binding.HomeBannerVPA.adapter = bannerAdapter
        binding.HomeBannerVPA.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        return binding.root
    }

    override fun initObserver() {

    }

}