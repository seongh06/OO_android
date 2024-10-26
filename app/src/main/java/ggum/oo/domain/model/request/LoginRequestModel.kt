package ggum.oo.domain.model.request

import ggum.oo.data.dto.request.LoginRequestDto
import ggum.oo.data.dto.request.SignUpLoginRequestDto
import java.io.Serializable

data class LoginRequestModel(
    val email: String,
    val password: String
):Serializable {
    fun toLoginRequestDto() =
        LoginRequestDto(email,password)
}
