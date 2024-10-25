package ggum.oo.domain.model.request

import ggum.oo.data.dto.request.AuthRequestDto
import ggum.oo.data.dto.request.SignUpLoginRequestDto
import java.io.Serializable

data class AuthRequestModel(
    val email: String,
    val authCode: String
):Serializable {
    fun toAuthRequestDto() =
        AuthRequestDto(email, authCode)
}
