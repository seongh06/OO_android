package ggum.oo.domain.model.request

import ggum.oo.data.dto.request.SignUpLoginRequestDto
import java.io.Serializable

data class SignUpLoginRequestModel(
    val email: String,
    val password: String,
    val phoneNumber: String,
    val nickname: String
):Serializable {
    fun toSignUpLoginRequestDto() =
        SignUpLoginRequestDto(email,password, phoneNumber, nickname)
}
