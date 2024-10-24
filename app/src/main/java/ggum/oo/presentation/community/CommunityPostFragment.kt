package ggum.oo.presentation.community

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.data.service.CommentItem
import ggum.oo.data.service.CommentList
import ggum.oo.data.service.ContentItem
import ggum.oo.data.service.ContentList
import ggum.oo.databinding.FragmentCommunityPostBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.presentation.search.ContentRVA

@AndroidEntryPoint
class CommunityPostFragment : BaseFragment<FragmentCommunityPostBinding>(R.layout.fragment_community_post) {

    private lateinit var commentRVA: CommentRVA
    private var commentItems: List<CommentItem> = listOf() // 댓글 리스트
    private val navigator by lazy { findNavController() }
    private var postId: Int? = null // 게시물 ID

    override fun initObserver() {
        // 필요한 옵저버 설정
    }

    override fun initView() {
        // 게시물 ID 가져오기
        postId = arguments?.let {
            CommunityPostFragmentArgs.fromBundle(it).id // 전달받은 ID
        }

        setupRecyclerView()
        loadComments() // 댓글 로드
    }

    private fun setupRecyclerView() {
        commentRVA = CommentRVA(commentItems) // 초기화 시 빈 리스트 사용
        binding.rvCommunityPostComment.apply {
            adapter = commentRVA
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun loadComments() {
        // ID에 해당하는 댓글을 가져오는 로직
        // 예시: 댓글 리스트를 필터링하여 가져오기
        commentItems = CommentList.items.filter { it.postId == postId } // 댓글을 게시물 ID로 필터링
        commentRVA.updateList(commentItems) // 어댑터에 댓글 업데이트
    }
}
