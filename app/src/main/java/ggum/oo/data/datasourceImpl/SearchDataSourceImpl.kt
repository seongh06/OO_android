package ggum.oo.data.datasourceImpl

import ggum.oo.data.datasource.SearchDataSource
import ggum.oo.data.dto.BaseResponse
import ggum.oo.data.dto.SearchBaseResponse
import ggum.oo.data.dto.response.SearchResponseDto
import ggum.oo.data.service.SearchService
import javax.inject.Inject

class SearchDataSourceImpl @Inject constructor(
    private val searchService: SearchService
):SearchDataSource{
    override suspend fun search(keyword: String): BaseResponse<SearchResponseDto> =
        searchService.search(keyword)

    override suspend fun searchHistory(userId: Int): SearchBaseResponse<String> =
        searchService.searchHistory(userId)
}