package ggum.oo.domain.model.response


data class LoginResponseModel(
    val code: String,
    val data: LoginDataResponseModel?
){
    data class LoginDataResponseModel(
        val nickname: String
    )
}
