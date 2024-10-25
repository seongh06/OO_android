package ggum.oo.data.dto.request

import java.io.Serializable

data class SignUpLoginRequestDto(
    val email: String,
    val password: String,
    val phoneNumber: String,
    val nickname: String
):Serializable {
    fun toSignUpLoginRequestDto() =
        SignUpLoginRequestDto(email, password, phoneNumber, nickname)
}
