package ggum.oo.presentation.promotion

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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
    private var imageList: MutableList<Int> = mutableListOf() // 선택한 이미지 URI 리스트
    private lateinit var promotionImageRVA: PromotionImageRVA // RecyclerView 어댑터

    // 권한 요청 코드 정의
    private val REQUEST_PERMISSION_READ_EXTERNAL_STORAGE = 1001

    override fun initView() {
        binding.layoutPromotionWriteIn.setOnClickListener {
            selectLayout(binding.layoutPromotionWriteIn, binding.ivPromotionWriteIn, binding.tvPromotionWriteIn)
        }

        binding.layoutPromotionWriteOut.setOnClickListener {
            selectLayout(binding.layoutPromotionWriteOut, binding.ivPromotionWriteOut, binding.tvPromotionWriteOut)
        }

        setupImageRecyclerView() // RecyclerView 초기화
    }

    override fun initObserver() {
        goToCommunity()

        binding.ivPromotionWriteBack.setOnSingleClickListener {
            navigator.navigate(R.id.action_communityWriteFragment_to_communityFragment)
        }
    }

    private fun goToCommunity() {
        binding.tvPromotionWriteSuccess.setOnSingleClickListener {
            showDialog() // 성공 메시지 다이얼로그
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

    private fun selectLayout(selected: LinearLayout, imageView: ImageView, textView: TextView) {
        selectedLayout?.let { resetLayout(it) }

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

    private fun setupImageRecyclerView() {
        promotionImageRVA = PromotionImageRVA(imageList)
        binding.rvPromotionWriteImage.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPromotionWriteImage.adapter = promotionImageRVA
    }
}
