package  ggum.oo.data.dto

data class ListBaseResponse<T>(
    val code: String,
    val data: List<T>,
    val message: String
)