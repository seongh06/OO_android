package ggum.oo.presentation.home

import androidx.navigation.NavController
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.databinding.FragmentHomeBinding
import ggum.oo.presentation.base.BaseFragment

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private lateinit var navController: NavController
    override fun initView() {

    }

    override fun initObserver() {

    }
}