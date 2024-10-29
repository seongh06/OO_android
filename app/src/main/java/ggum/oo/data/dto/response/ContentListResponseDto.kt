package ggum.oo.data.dto.response

import ggum.oo.domain.model.response.ContentListResponseModel

data class ContentListResponseDto(
    val post: ContentPostResponseDto,
    val comments: List<ContentCommentsResponseDto>
){
    data class ContentPostResponseDto(
        val id: Int,
        val title: String,
        val content: String,
        val hashtag: String,
        val postType: Boolean,
        val createdAt: String,
        val commentCount: Int,
        val isExternal: Boolean,
        val memberId: Int
    ){
        fun toContentPostResponseModel() =
            ContentListResponseModel.ContentPostResponseModel(id, title,content, hashtag, postType, createdAt, commentCount, isExternal, memberId)
    }
    data class ContentCommentsResponseDto(
        val id: Int,
        val content: String,
        val createdAt: String,
        val memberId: Int,
        val author: Boolean
    ){
        fun toContentCommentsResponseModel() =
            ContentListResponseModel.ContentCommentsResponseModel(id, content, createdAt, memberId, author)
    }
    fun toContentListResponseModel() =
        ContentListResponseModel(post.toContentPostResponseModel(), comments.map { it.toContentCommentsResponseModel() })
}
