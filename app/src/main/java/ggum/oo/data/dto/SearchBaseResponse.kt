package  ggum.oo.data.dto

data class SearchBaseResponse<T>(
    val data: List<T>,
    val message: String
)