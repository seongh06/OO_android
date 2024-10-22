package ggum.oo.data.service

data class ContentItem(
    val area: Boolean,
    val title: String,
    val body: String,
    val image: Int?,
    val date: String,
    val commentCount: Int,
    val isFavorite: Boolean
)
