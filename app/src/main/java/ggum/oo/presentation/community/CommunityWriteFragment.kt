package ggum.oo.presentation.community

import android.content.Intent
import android.graphics.Color
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.databinding.FragmentCommunityBinding
import ggum.oo.databinding.FragmentCommunityWriteBinding
import ggum.oo.presentation.MainActivity
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class CommunityWriteFragment : BaseFragment<FragmentCommunityWriteBinding>(R.layout.fragment_community_write) {

    private val navigator by lazy { findNavController() }

    private var selectedLayout: LinearLayout? = null

    override fun initView() {
        binding.layoutCommunityWriteIn.setOnClickListener {
            selectLayout(binding.layoutCommunityWriteIn, binding.ivCommunityWriteIn, binding.tvCommunityWriteIn)
        }

        binding.layoutCommunityWriteOut.setOnClickListener {
            selectLayout(binding.layoutCommunityWriteOut, binding.ivCommunityWriteOut, binding.tvCommunityWriteOut)
        }
    }

    override fun initObserver() {
        goToCommunity()

        binding.ivCommunityWriteBack.setOnSingleClickListener {
            navigator.navigate(R.id.action_communityWriteFragment_to_communityFragment)
        }
    }

    private fun goToCommunity() {
        binding.tvCommunityWriteSuccess.setOnSingleClickListener {
            navigator.navigate(R.id.action_communityWriteFragment_to_communityFragment)
        }
    }

    private fun selectLayout(selected: LinearLayout, imageView: ImageView, textView: TextView) {
        selectedLayout?.let {
            resetLayout(it)
        }

        selectedLayout = selected

        imageView.setImageResource(R.drawable.shape_circle_selected)
        textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.oo_yellow))
    }

    private fun resetLayout(layout: LinearLayout) {
        if (layout == binding.layoutCommunityWriteIn) {
            binding.ivCommunityWriteIn.setImageResource(R.drawable.shape_circle_unselected)
            binding.tvCommunityWriteIn.setTextColor(ContextCompat.getColor(requireContext(), R.color.light_gray))
        } else {
            binding.ivCommunityWriteOut.setImageResource(R.drawable.shape_circle_unselected)
            binding.tvCommunityWriteOut.setTextColor(ContextCompat.getColor(requireContext(), R.color.light_gray))
        }
    }
}

