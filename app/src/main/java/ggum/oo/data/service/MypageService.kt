package ggum.oo.data.service

import ggum.oo.data.dto.BaseResponse
import ggum.oo.data.dto.NoneBaseResponse
import ggum.oo.data.dto.request.ClubMypageRequestDto
import ggum.oo.data.dto.response.MypageResponseDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MypageService {

    @POST("/api/mypage/club-request")
    suspend fun clubRequest(
        @Body clubName: String,
        @Body studentId: String,
        @Body name: String
    ): NoneBaseResponse

    @POST("/api/mypage/club-reject")
    suspend fun clubReject(
        @Body requestDto: ClubMypageRequestDto
    ): NoneBaseResponse

    @POST("/api/mypage/club-accept")
    suspend fun clubAccept(
        @Body requestDto: ClubMypageRequestDto
    ): NoneBaseResponse

    @GET("api/mypage")
    suspend fun mypage(): BaseResponse<MypageResponseDto>
}