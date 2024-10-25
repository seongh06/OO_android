package ggum.oo.data.service

import ggum.oo.data.dto.NoneBaseResponse
import ggum.oo.data.dto.request.SignUpLoginRequestDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface LoginService {
    // api 명세서의 api 주소 기입
    @PUT("/api/authentication")
    suspend fun authentication(
        @Body email: String,
        @Body authCode : String
    ): NoneBaseResponse

    @PUT("/api/auth-code")
    suspend fun authCode(
        @Body email: String
    ):NoneBaseResponse

    @POST("/api/sign-up")
    suspend fun signUp(
        @Body request: SignUpLoginRequestDto
    ): NoneBaseResponse

    @GET("/api/validNickname")
    suspend fun validNickname(
        @Query("nickname") nickName: String
    ): NoneBaseResponse

    @GET("/api/validEmail")
    suspend fun validEmail(
        @Query("email") email: String
    ): NoneBaseResponse
}