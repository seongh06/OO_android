package ggum.oo.domain.repository

import ggum.oo.data.dto.NoneBaseResponse
import ggum.oo.domain.model.request.AuthRequestModel
import ggum.oo.domain.model.request.SignUpLoginRequestModel


interface LoginRepository {
    suspend fun authentication(request: AuthRequestModel): Result<NoneBaseResponse>

    suspend fun authCode(email: String): Result<NoneBaseResponse>

    suspend fun signUp(request: SignUpLoginRequestModel): Result<NoneBaseResponse>

    suspend fun validNickname(nickname: String): Result<NoneBaseResponse>

    suspend fun validEmail(email: String): Result<NoneBaseResponse>

    suspend fun login(email: String, password: String): Result<String>
}