package ggum.oo.data.datasource

import ggum.oo.data.dto.BaseResponse
import ggum.oo.data.dto.response.CommentResponseDto

interface ContentDataSource {
    suspend fun communityComment(postId: Int,content:String): BaseResponse<CommentResponseDto>
}