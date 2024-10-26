package ggum.oo.data.dto.request

import ggum.oo.domain.model.response.MypageResponseModel

data class ApiResponse(
    val message: String,
    val data: List<MypageResponseModel> // data는 List로 정의
)
