package  ggum.oo.data.dto

data class BaseResponse<T>(
    val code: String,
    val data: T,
    val message: String
)