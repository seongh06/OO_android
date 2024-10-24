package ggum.oo.presentation.promotion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.databinding.FragmentCommunityPostBinding
import ggum.oo.databinding.FragmentPromotionPostBinding
import ggum.oo.databinding.FragmentPromotionWriteBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.presentation.community.HashtagRVA


@AndroidEntryPoint
class PromotionPostFragment : BaseFragment<FragmentPromotionPostBinding>(R.layout.fragment_promotion_post) {

    private lateinit var imageAdapter: PromotionImageRVA
    private lateinit var hashtagAdapter: HashtagRVA

    private val imageList = listOf(
        R.drawable.img_logo, // 이미지 리소스 추가
        R.drawable.img_logo, // 추가 이미지
        R.drawable.img_logo  // 추가 이미지
    )

    private val hashtagList = listOf("가톨릭대학교", "미디어기술콘텐츠학과", "김승혁", "자살쇼")

    override fun initView() {
        setupImageRecyclerView()
        setupHashtagRecyclerView()
    }

    override fun initObserver() {

    }

    private fun setupImageRecyclerView() {
        imageAdapter = PromotionImageRVA(imageList)
        binding.rvPromotionPostImage.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPromotionPostImage.adapter = imageAdapter
    }

    private fun setupHashtagRecyclerView() {
        hashtagAdapter = HashtagRVA(hashtagList)
        binding.rvPromotionPostHashtag.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPromotionPostHashtag.adapter = hashtagAdapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView() // UI 초기화
        initObserver() // 데이터 관찰 초기화
    }
}