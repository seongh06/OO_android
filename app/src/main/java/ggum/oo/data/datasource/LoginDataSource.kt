package ggum.oo.data.datasource

import ggum.oo.data.dto.NoneBaseResponse
import ggum.oo.data.dto.request.SignUpLoginRequestDto

interface LoginDataSource {
    suspend fun authentication(email: String, authCode: String): NoneBaseResponse

    suspend fun authCode(email: String):NoneBaseResponse

    suspend fun signUp(request: SignUpLoginRequestDto): NoneBaseResponse

    suspend fun validNickname(nickname: String): NoneBaseResponse

    suspend fun validEmail(email: String): NoneBaseResponse
}