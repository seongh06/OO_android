package ggum.oo.presentation.community

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.data.CommentItem
import ggum.oo.data.CommentList
import ggum.oo.databinding.FragmentCommunityPostBinding
import ggum.oo.presentation.base.BaseFragment

@AndroidEntryPoint
class CommunityPostFragment : BaseFragment<FragmentCommunityPostBinding>(R.layout.fragment_community_post) {

    private lateinit var communityCommentRVA: CommunityCommentRVA
    private var commentItems: List<CommentItem> = listOf() // 댓글 리스트
    private val navigator by lazy { findNavController() }
    private lateinit var hashtagRVA: HashtagRVA
    private var postId: Int? = null // 게시물 ID

    private val hashtagList = listOf("가톨릭대학교", "미디어기술콘텐츠학과", "김승혁", "자살쇼")

    override fun initObserver() {

    }

    override fun initView() {
            postId = arguments?.let {
            CommunityPostFragmentArgs.fromBundle(it).id // 전달받은 ID
        }
        setupHashtagRecyclerView()
        setupCommentRecyclerView()
        loadComments() // 댓글 로드
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
        // 예시: 댓글 리스트를 필터링하여 가져오기
        commentItems = CommentList.items.filter { it.postId == postId } // 댓글을 게시물 ID로 필터링
        communityCommentRVA.updateList(commentItems) // 어댑터에 댓글 업데이트
    }
}
