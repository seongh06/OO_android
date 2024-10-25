package ggum.oo.data.repositoryImpl

import ggum.oo.data.datasource.LoginDataSource
import ggum.oo.data.dto.NoneBaseResponse
import ggum.oo.domain.model.request.SignUpLoginRequestModel
import ggum.oo.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource
) : LoginRepository {
    override suspend fun authentication(email: String, authCode:String): Result<NoneBaseResponse> =
        runCatching { loginDataSource.authentication(email, authCode) }

    override suspend fun authCode(email: String): Result<NoneBaseResponse> =
        runCatching { loginDataSource.authCode(email)}

    override suspend fun signUp(request: SignUpLoginRequestModel): Result<NoneBaseResponse> =
        runCatching { loginDataSource.signUp(request.toSignUpLoginRequestDto())}

    override suspend fun validNickname(nickname: String): Result<NoneBaseResponse> =
        runCatching { loginDataSource.validNickname(nickname) }

    override suspend fun validEmail(email: String): Result<NoneBaseResponse> =
        runCatching { loginDataSource.validEmail(email)}
}