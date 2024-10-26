package ggum.oo.data.dto.response

import ggum.oo.domain.model.response.SearchResponseModel

data class SearchResponseDto(
    val title: String,
    val content: String,
    val createdAt: String,
    val commentCount: Int,
    val isExternal: Boolean
){
    fun toSearchResponseDto() =
        SearchResponseModel(title, content, createdAt, commentCount, isExternal)
}
