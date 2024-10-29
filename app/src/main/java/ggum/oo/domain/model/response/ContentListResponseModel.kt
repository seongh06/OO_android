package ggum.oo.domain.model.response

import java.io.Serializable

data class ContentListResponseModel(
    val post: ContentPostResponseModel,
    val comments: List<ContentCommentsResponseModel>
):Serializable {
    data class ContentPostResponseModel(
        val id: Int,
        val title: String,
        val content: String,
        val hashtag: String,
        val postType: Boolean,
        val createdAt: String,
        val commentCount: Int,
        val isExternal: Boolean,
        val memberId: Int
    )

    data class ContentCommentsResponseModel(
        val id: Int,
        val content: String,
        val createdAt: String,
        val memberId: Int,
        val author: Boolean
    )
}