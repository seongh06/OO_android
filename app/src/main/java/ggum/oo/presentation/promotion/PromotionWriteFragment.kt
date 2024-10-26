package ggum.oo.presentation.promotion

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
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
import ggum.oo.data.ContentItem
import ggum.oo.data.ContentList
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
        binding.ivPromotionWriteBack.setOnSingleClickListener {
            navigator.navigateUp()
        }

        setupImageRecyclerView() // RecyclerView 초기화

        val titleEditText: EditText = binding.etPromotionWriteTitle
        val bodyEditText: EditText = binding.etPromotionWriteBody

        binding.tvPromotionWriteSuccess.setOnClickListener {
            showDialog()
            onSubmitButtonClick(titleEditText, bodyEditText)
        }
    }

    private fun onSubmitButtonClick(titleEditText: EditText, bodyEditText: EditText) {
        val title = titleEditText.text.toString()
        val body = bodyEditText.text.toString()

        // 교내/교외 구분
        val area = selectedLayout == binding.layoutPromotionWriteIn // 선택된 레이아웃에 따라 true/false 설정

        // submitPost 호출
        submitPost(title, body, area)

        // 입력 필드 초기화
        titleEditText.text.clear()
        bodyEditText.text.clear()
    }

    private fun submitPost(title: String, body: String, area: Boolean) {
        // 현재 날짜를 고정하여 2024년으로 설정하고 랜덤 날짜 생성
        val date = "2024-10-${(1..31).random()}" // 1부터 31 사이의 랜덤 날짜 생성

        // 새로운 ContentItem 생성
        val newContentItem = ContentItem(
            id = ContentList.items.size + 1, // 새로운 ID 생성
            category = true, // 항상 false
            area = area, // 교내/교외 여부
            title = title, // 사용자 입력으로 설정
            body = body, // 사용자 입력으로 설정
            image = null, // 이미지 리소스 ID는 null
            date = date, // 랜덤 날짜
            commentCount = 0, // 초기 댓글 수
            isFavorite = false // 기본값 설정
        )

        // 새로운 항목 추가
        ContentList.addItem(newContentItem)

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
