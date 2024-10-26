package ggum.oo.data.datasource

import ggum.oo.data.dto.BaseResponse
import ggum.oo.data.dto.NoneBaseResponse
import ggum.oo.data.dto.request.AuthRequestDto
import ggum.oo.data.dto.request.SignUpLoginRequestDto

interface LoginDataSource {
    suspend fun authentication(requestDto: AuthRequestDto): NoneBaseResponse

    suspend fun authCode(email: String):NoneBaseResponse

    suspend fun signUp(request: SignUpLoginRequestDto): NoneBaseResponse

    suspend fun validNickname(nickname: String): NoneBaseResponse

    suspend fun validEmail(email: String): NoneBaseResponse

    suspend fun login(email: String, password:String): BaseResponse<String>
}