package ggum.oo.data.datasourceImpl

import ggum.oo.data.datasource.ContentDataSource
import ggum.oo.data.dto.BaseResponse
import ggum.oo.data.dto.response.CommentResponseDto
import ggum.oo.data.service.ContentService
import javax.inject.Inject

class ContentDataSourceImpl @Inject constructor(
    private val contentService: ContentService
):ContentDataSource {

    override suspend fun communityComment(postId: Int, content: String): BaseResponse<CommentResponseDto> =
        contentService.communityComment(postId, content)


}