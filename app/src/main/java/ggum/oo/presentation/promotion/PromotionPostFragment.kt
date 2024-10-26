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
import ggum.oo.data.CommentItem
import ggum.oo.data.CommentList
import ggum.oo.databinding.FragmentCommunityPostBinding
import ggum.oo.databinding.FragmentPromotionPostBinding
import ggum.oo.databinding.FragmentPromotionWriteBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.presentation.community.CommunityCommentRVA
import ggum.oo.presentation.community.CommunityPostFragmentArgs
import ggum.oo.presentation.community.HashtagRVA


@AndroidEntryPoint
class PromotionPostFragment : BaseFragment<FragmentPromotionPostBinding>(R.layout.fragment_promotion_post) {

    private lateinit var communityCommentRVA: CommunityCommentRVA
    private lateinit var promotionImageRVA: PromotionImageRVA
    private var commentItems: List<CommentItem> = listOf() // 댓글 리스트
    private val navigator by lazy { findNavController() }
    private lateinit var hashtagRVA: HashtagRVA
    private var postId: Int? = null // 게시물 ID

    private val imageList = listOf(
        R.drawable.img_logo, // 이미지 리소스 추가
        R.drawable.img_logo, // 추가 이미지
        R.drawable.img_logo  // 추가 이미지
    )

    private val hashtagList = listOf("가톨릭대학교", "미디어기술콘텐츠학과", "김승혁", "자살쇼")

    override fun initView() {
        postId = arguments?.let {
            PromotionPostFragmentArgs.fromBundle(it).id // 전달받은 ID
        }

        setupCommentRecyclerView()
        setupImageRecyclerView()
        setupHashtagRecyclerView()
        loadComments() // 댓글 로드

        binding.ivPromotionPostBack.setOnClickListener{
            findNavController().navigateUp()
        }

    }

    override fun initObserver() {

    }

    private fun setupImageRecyclerView() {
        promotionImageRVA = PromotionImageRVA(imageList)
        binding.rvPromotionPostImage.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPromotionPostImage.adapter = promotionImageRVA
    }

    private fun setupHashtagRecyclerView() {
        hashtagRVA = HashtagRVA(hashtagList)
        binding.rvPromotionPostHashtag.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvPromotionPostHashtag.adapter = hashtagRVA
    }

    private fun setupCommentRecyclerView() {
        communityCommentRVA = CommunityCommentRVA(commentItems) // 초기화 시 빈 리스트 사용
        binding.rvPromotionPostComment.apply {
            adapter = communityCommentRVA
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun loadComments() {
        // ID에 해당하는 댓글을 가져오는 로직
        // 예시: 댓글 리스트를 필터링하여 가져오기
        commentItems = CommentList.items.filter { it.postId == postId } // 댓글을 게시물 ID로 필터링
        communityCommentRVA.updateList(commentItems) // 어댑터에 댓글 업데이트
    }
}