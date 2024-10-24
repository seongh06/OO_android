package ggum.oo.data.service

data class CommentItem(
    val id: Int,
    val postId: Int, // 댓글이 속한 게시물의 ID
    val nickname: String,
    val body: String,
    val writer: Boolean // 작성자 여부
)
