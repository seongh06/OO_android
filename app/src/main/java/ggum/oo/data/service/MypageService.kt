package ggum.oo.data.service

import ggum.oo.data.dto.BaseResponse
import ggum.oo.data.dto.NoneBaseResponse
import ggum.oo.data.dto.request.ClubManagerRequestDto
import ggum.oo.data.dto.request.ClubMypageRequestDto
import ggum.oo.data.dto.request.ClubRequestDto
import ggum.oo.data.dto.response.MypageResponseDto
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface MypageService {

    @POST("/api/mypage/club-request")
    suspend fun clubRequest(
        @Body request: ClubRequestDto
    ): NoneBaseResponse

    @DELETE("/api/mypage/club-reject")
    suspend fun clubReject(
        @Body requestDto: ClubMypageRequestDto
    ): NoneBaseResponse

    @POST("/api/mypage/club-accept")
    suspend fun clubAccept(
        @Body requestDto: ClubMypageRequestDto
    ): NoneBaseResponse

    @GET("api/mypage")
    suspend fun mypage(): BaseResponse<MypageResponseDto>

    @POST("api/mypage/club-manager")
    suspend fun clubManager(
        @Body request: ClubManagerRequestDto
    ):NoneBaseResponse
}