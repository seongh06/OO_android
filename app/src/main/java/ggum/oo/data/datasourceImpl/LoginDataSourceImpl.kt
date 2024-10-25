package ggum.oo.data.datasourceImpl

import ggum.oo.data.datasource.LoginDataSource
import ggum.oo.data.dto.BaseResponse
import ggum.oo.data.dto.NoneBaseResponse
import ggum.oo.data.dto.request.AuthRequestDto
import ggum.oo.data.dto.request.SignUpLoginRequestDto
import ggum.oo.data.service.LoginService
import javax.inject.Inject
import kotlin.math.log

class LoginDataSourceImpl @Inject constructor(
    private val loginService: LoginService
): LoginDataSource{

    override suspend fun authentication(requestDto: AuthRequestDto): NoneBaseResponse = loginService.authentication(requestDto)

    override suspend fun authCode(email: String): NoneBaseResponse = loginService.authCode(email)

    override suspend fun signUp(request: SignUpLoginRequestDto): NoneBaseResponse = loginService.signUp(request)

    override suspend fun validNickname(nickname: String): NoneBaseResponse = loginService.validNickname(nickname)

    override suspend fun validEmail(email: String): NoneBaseResponse = loginService.validEmail(email)

    override suspend fun login(email: String, password: String): BaseResponse<String> = loginService.login(email, password)
}