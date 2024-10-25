package ggum.oo.data.service

import ggum.oo.data.dto.BaseResponse
import ggum.oo.data.dto.NoneBaseResponse
import ggum.oo.data.dto.request.AuthRequestDto
import ggum.oo.data.dto.request.SignUpLoginRequestDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query

interface LoginService {
    @PUT("/api/authentication")
    suspend fun authentication(
        @Body requestDto: AuthRequestDto
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

    @GET("/api/login")
    suspend fun login(
        @Query("email") email: String,
        @Query("password") password: String
    ): BaseResponse<String>
}