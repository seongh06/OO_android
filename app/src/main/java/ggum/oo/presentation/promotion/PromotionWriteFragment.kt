package ggum.oo.presentation.promotion

import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.databinding.DialogPostBinding
import ggum.oo.databinding.FragmentPromotionWriteBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class PromotionWriteFragment : BaseFragment<FragmentPromotionWriteBinding>(R.layout.fragment_promotion_write) {

    private val navigator by lazy { findNavController() }

    private var selectedLayout: LinearLayout? = null

    override fun initView() {
        binding.layoutPromotionWriteIn.setOnClickListener {
            selectLayout(binding.layoutPromotionWriteIn, binding.ivPromotionWriteIn, binding.tvPromotionWriteIn)
        }

        binding.layoutPromotionWriteOut.setOnClickListener {
            selectLayout(binding.layoutPromotionWriteOut, binding.ivPromotionWriteOut, binding.tvPromotionWriteOut)
        }
    }

    override fun initObserver() {
        goToCommunity()

        binding.ivPromotionWriteBack.setOnSingleClickListener {
            navigator.navigate(R.id.action_communityWriteFragment_to_communityFragment)
        }
    }

    private fun goToCommunity() {
        binding.tvPromotionWriteSuccess.setOnSingleClickListener {
            showDialog()
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
        if (layout == binding.layoutPromotionWriteIn) {
            binding.ivPromotionWriteIn.setImageResource(R.drawable.shape_circle_unselected)
            binding.tvPromotionWriteIn.setTextColor(ContextCompat.getColor(requireContext(), R.color.light_gray))
        } else {
            binding.ivPromotionWriteOut.setImageResource(R.drawable.shape_circle_unselected)
            binding.tvPromotionWriteOut.setTextColor(ContextCompat.getColor(requireContext(), R.color.light_gray))
        }
    }

    private fun showDialog() {
        val dialogView =
            LayoutInflater.from(requireContext()).inflate(R.layout.dialog_post, null)

        val dialogBinding = DialogPostBinding.bind(dialogView)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setCancelable(false)
            .create()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()

        val layoutParams = dialog.window?.attributes
        layoutParams?.dimAmount = 0.8f
        dialog.window?.attributes = layoutParams

        dialogBinding.btnConfirm.setOnSingleClickListener {
            dialog.dismiss()
            navigator.navigate(R.id.action_promotionWriteFragment_to_promotionFragment)
        }
    }
}

