package ggum.oo.data.repositoryImpl

import android.content.SharedPreferences
import ggum.oo.data.datasource.LoginDataSource
import ggum.oo.data.dto.NoneBaseResponse
import ggum.oo.data.dto.response.LoginResponseDto
import ggum.oo.data.service.LoginService
import ggum.oo.domain.model.request.AuthRequestModel
import ggum.oo.domain.model.request.LoginRequestModel
import ggum.oo.domain.model.request.SignUpLoginRequestModel
import ggum.oo.domain.model.response.LoginResponseModel
import ggum.oo.domain.repository.LoginRepository
import timber.log.Timber
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val loginDataSource: LoginDataSource,
    private val loginService: LoginService,
    private val spf: SharedPreferences
) : LoginRepository {
    override suspend fun authentication(request : AuthRequestModel): Result<NoneBaseResponse> =
        runCatching { loginDataSource.authentication(request.toAuthRequestDto()) }

    override suspend fun authCode(email: String): Result<NoneBaseResponse> =
        runCatching { loginDataSource.authCode(email)}

    override suspend fun signUp(request: SignUpLoginRequestModel): Result<NoneBaseResponse> =
        runCatching { loginDataSource.signUp(request.toSignUpLoginRequestDto())}

    override suspend fun validNickname(nickname: String): Result<NoneBaseResponse> =
        runCatching { loginDataSource.validNickname(nickname) }

    override suspend fun validEmail(email: String): Result<NoneBaseResponse> =
        runCatching { loginDataSource.validEmail(email)}

    override suspend fun login(request: LoginRequestModel): Result<LoginResponseModel> {
        return try {
            val response = loginService.login(request.toLoginRequestDto())

            if (response.isSuccessful) {
                // 응답 DTO를 모델로 변환
                val loginResponseDto = response.body()

                // Authorization 헤더에서 accessToken 추출
                val accessToken = response.headers()["Authorization"]?.substringAfter("Bearer ")
                Timber.d("AccessToken 수신: $accessToken") // AccessToken 로그

                // LoginResponseModel로 변환
                val loginResponseModel = loginResponseDto?.toLoginResponseModel()

                // null 체크
                loginResponseModel?.let {
                    // accessToken을 저장 (필요시)
                    if (!accessToken.isNullOrEmpty()) {
                        saveAccessToken(accessToken) // SharedPreferences에 저장
                    }
                    Result.success(it)
                } ?: run {
                    Timber.e("로그인 응답이 null입니다.") // 변환 실패 로그
                    Result.failure(Exception("로그인 응답이 null입니다."))
                }
            } else {
                Timber.e("로그인 실패: ${response.message()}") // 로그인 실패 로그
                Result.failure(Exception("로그인 실패: ${response.message()}"))
            }
        } catch (e: Exception) {
            Timber.e("로그인 중 예외 발생: ${e.message}") // 예외 발생 로그
            Result.failure(e) // 예외 처리
        }
    }

    // LoginResponseDto를 LoginResponseModel로 변환하는 확장 함수
    fun LoginResponseDto.toLoginResponseModel() = LoginResponseModel(
        code = this.code,
        data = this.data?.toLoginDataResponseModel()
    )

    fun LoginResponseModel.LoginDataResponseModel.toLoginDataModel() = LoginResponseModel.LoginDataResponseModel(
        nickname = this.nickname
    )
    private fun saveAccessToken(token: String) {
        spf.edit().apply {
            putString("accessToken", token)
            apply()
        }
    }
}