package ggum.oo.data.dto.request

import java.io.Serializable

data class AuthRequestDto(
    val email: String,
    val authCode: String
)
