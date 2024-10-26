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
import ggum.oo.data.ContentItem
import ggum.oo.data.ContentList
import ggum.oo.databinding.FragmentCommunityPostBinding
import ggum.oo.databinding.FragmentPromotionPostBinding
import ggum.oo.databinding.FragmentPromotionWriteBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.presentation.community.CommunityCommentRVA
import ggum.oo.presentation.community.CommunityPostFragmentArgs
import ggum.oo.presentation.community.HashtagRVA
import ggum.oo.util.extension.setOnSingleClickListener


@AndroidEntryPoint
class PromotionPostFragment : BaseFragment<FragmentPromotionPostBinding>(R.layout.fragment_promotion_post) {

    private lateinit var communityCommentRVA: CommunityCommentRVA
    private lateinit var promotionImageRVA: PromotionImageRVA
    private var commentItems: List<CommentItem> = listOf() // 댓글 리스트
    private val navigator by lazy { findNavController() }
    private lateinit var hashtagRVA: HashtagRVA
    private var postId: Int? = null // 게시물 ID
    private var imageList: List<Int> = listOf() // 클래스 수준에서 이미지 리스트를 초기화합니다.
    private var isLiked = false // 좋아요 상태 변수
    private val hashtagList = listOf("가톨릭대학교", "가대", "가대생", "GGUM해커톤")

    override fun initView() {
        postId = arguments?.let {
            PromotionPostFragmentArgs.fromBundle(it).id // 전달받은 ID
        }

        // 게시물 정보를 로드합니다.
        loadPostDetails()

        setupCommentRecyclerView()
/*
        setupHashtagRecyclerView()
*/
        loadComments() // 댓글 로드

        binding.ivPromotionPostBack.setOnSingleClickListener {
            navigator.navigateUp()
        }
        binding.ivPromotionPostLike.setOnSingleClickListener {
            isLiked = !isLiked // 상태 토글
            if (isLiked) {
                binding.ivPromotionPostLike.setBackgroundResource(R.drawable.ic_selected_like) // 좋아요 상태일 때 배경
            } else {
                binding.ivPromotionPostLike.setBackgroundResource(R.drawable.ic_unselected_like) // 좋아요 해제 상태일 때 배경
            }
        }
    }

    private fun loadPostDetails() {
        // postId에 해당하는 ContentItem을 가져오는 로직
        val contentItem = ContentList.items.find { it.id == postId }
        contentItem?.let {
            // UI 요소에 데이터 설정
            binding.tvPromotionPostTitle.text = it.title
            binding.tvPromotionPostBody.text = it.body
            binding.tvPromotionPostDate.text = it.date

            it.image?.let { imageResId ->
                // 이미지 리스트에 추가
                imageList = listOf(imageResId) // 여기서 imageList를 새로 구성합니다.
                setupImageRecyclerView(imageList) // RecyclerView 업데이트
            } ?: run {
                binding.rvPromotionPostImage.visibility = View.GONE // 이미지가 없으면 숨김
            }

            // area에 따라 교내/교외 표시
            val areaText = if (it.area) "교내" else "교외"
            binding.tvPromotionPostArea.text = areaText // area에 대한 TextView 추가 필요

        }
    }

    override fun initObserver() {
        // 필요 시 옵저버 설정
    }

    private fun setupImageRecyclerView(imageList: List<Int>) {
        promotionImageRVA = PromotionImageRVA(imageList)
        binding.rvPromotionPostImage.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
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
        commentItems = CommentList.items.filter { it.postId == postId } // 댓글을 게시물 ID로 필터링
        communityCommentRVA.updateList(commentItems) // 어댑터에 댓글 업데이트
    }
}

