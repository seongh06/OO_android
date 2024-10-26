package ggum.oo.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ggum.oo.databinding.FragmentBannerBinding

class BannerFragment(val imgRes: Int) : Fragment() {

    private lateinit var binding: FragmentBannerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBannerBinding.inflate(inflater, container, false)

        // 이미지 설정
        binding.ivBanner.setImageResource(imgRes)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // 필요한 경우 binding 해제
    }
}
