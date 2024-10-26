package ggum.oo.data.datasource

import ggum.oo.data.dto.BaseResponse
import ggum.oo.data.dto.SearchBaseResponse
import ggum.oo.data.dto.response.SearchResponseDto

interface SearchDataSource {
    suspend fun search(keyword:String):BaseResponse<SearchResponseDto>

    suspend fun searchHistory(userId: Int):SearchBaseResponse<String>
}