package ggum.oo.data.service

import ggum.oo.data.dto.BaseResponse
import ggum.oo.data.dto.ListBaseResponse
import ggum.oo.data.dto.response.ContentListResponseDto
import ggum.oo.presentation.base.BaseFragment
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Path

interface ContentService {

    @GET("/api/jejal/{postId}")
    suspend fun contentList(
        @Path ("postId") postId: Int
    ):BaseResponse<ContentListResponseDto>
}