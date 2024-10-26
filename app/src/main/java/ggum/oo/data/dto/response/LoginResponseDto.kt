package ggum.oo.data.dto.response

import ggum.oo.domain.model.response.LoginResponseModel

data class LoginResponseDto(
    val code: String,
    val data: LoginDataResponseDto?
) {
    fun toLoginResponseModel(): LoginResponseModel {
        return LoginResponseModel(code, data?.toLoginDataResponseModel())
    }

    data class LoginDataResponseDto(
        val nickname: String
    ) {
        fun toLoginDataResponseModel() =
            LoginResponseModel.LoginDataResponseModel(nickname)
    }
}
