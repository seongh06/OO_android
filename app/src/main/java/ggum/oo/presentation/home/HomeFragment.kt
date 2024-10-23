package ggum.oo.presentation.home

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.navigation.NavController
import androidx.viewpager2.widget.ViewPager2
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.databinding.FragmentHomeBinding
import ggum.oo.presentation.base.BaseFragment

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun initObserver() {
        // 필요에 따라 옵저버 설정
    }

    override fun initView() {
    }

}
