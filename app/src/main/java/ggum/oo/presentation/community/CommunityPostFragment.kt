package ggum.oo.presentation.community

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.data.CommentItem
import ggum.oo.data.CommentList
import ggum.oo.data.ContentList
import ggum.oo.databinding.FragmentCommunityPostBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class CommunityPostFragment : BaseFragment<FragmentCommunityPostBinding>(R.layout.fragment_community_post) {

    private lateinit var communityCommentRVA: CommunityCommentRVA
    private var commentItems: List<CommentItem> = listOf() // 댓글 리스트
    private val navigator by lazy { findNavController() }
    private lateinit var hashtagRVA: HashtagRVA
    private var postId: Int? = null // 게시물 ID
    private var isLiked = false // 좋아요 상태 변수

    private val hashtagList = listOf("가톨릭대학교", "가대", "가대생", "GGUM해커톤")

    override fun initObserver() {
        // 필요 시 옵저버 설정
    }

    override fun initView() {
        postId = arguments?.let {
            CommunityPostFragmentArgs.fromBundle(it).id // 전달받은 ID
        }
        loadPostDetails() // 게시물 정보 로드
        setupHashtagRecyclerView()
        setupCommentRecyclerView()
        loadComments() // 댓글 로드

        binding.ivLike.setOnSingleClickListener {
            isLiked = !isLiked // 상태 토글
            if (isLiked) {
                binding.ivLike.setBackgroundResource(R.drawable.ic_selected_like) // 좋아요 상태일 때 배경
            } else {
                binding.ivLike.setBackgroundResource(R.drawable.ic_unselected_like) // 좋아요 해제 상태일 때 배경
            }
        }
        binding.ivCommunityPostBack.setOnSingleClickListener {
            navigator.navigateUp()
        }
    }

    private fun loadPostDetails() {
        // postId에 해당하는 ContentItem을 가져오는 로직
        val contentItem = ContentList.items.find { it.id == postId }
        contentItem?.let {
            // UI 요소에 데이터 설정
            binding.tvCommunityPostTitle.text = it.title
            binding.tvCommunityPostBody.text = it.body
            binding.tvCommunityPostDate.text = it.date

            // area에 따라 교내/교외 표시
            val areaText = if (it.area) "교내" else "교외"
            binding.tvCommunityPostArea.text = areaText
        }
    }

    private fun setupCommentRecyclerView() {
        communityCommentRVA = CommunityCommentRVA(commentItems) // 초기화 시 빈 리스트 사용
        binding.rvCommunityPostComment.apply {
            adapter = communityCommentRVA
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupHashtagRecyclerView() {
        hashtagRVA = HashtagRVA(hashtagList)
        binding.rvCommunityPostHashtag.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.rvCommunityPostHashtag.adapter = hashtagRVA
    }

    private fun loadComments() {
        // ID에 해당하는 댓글을 가져오는 로직
        commentItems = CommentList.items.filter { it.postId == postId } // 댓글을 게시물 ID로 필터링
        communityCommentRVA.updateList(commentItems) // 어댑터에 댓글 업데이트
    }
}
