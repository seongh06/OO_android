package ggum.oo.data.service

import ggum.oo.data.dto.BaseResponse
import ggum.oo.data.dto.response.CommentResponseDto
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface ContentService {
    @POST("/api/jejal/{postId}/comment")
    suspend fun communityComment(
        @Query("postId") postId: Int,
        @Body content: String
    ):BaseResponse<CommentResponseDto>
}