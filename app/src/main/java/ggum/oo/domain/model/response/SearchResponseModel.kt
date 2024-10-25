package ggum.oo.domain.model.response

data class SearchResponseModel(
    val title: String,
    val content: String,
    val createdAt: String,
    val commentCount: Int,
    val isExternal: Boolean
)