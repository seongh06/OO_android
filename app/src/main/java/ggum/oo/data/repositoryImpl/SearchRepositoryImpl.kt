package ggum.oo.data.repositoryImpl

import ggum.oo.data.datasource.SearchDataSource
import ggum.oo.domain.model.response.SearchResponseModel
import ggum.oo.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val searchDataSource: SearchDataSource
): SearchRepository {
    override suspend fun search(keyword: String): Result<SearchResponseModel> =
        runCatching { searchDataSource.search(keyword).data.toSearchResponseDto() }

    override suspend fun search(userId: Int): Result<String> =
        runCatching { searchDataSource.searchHistory(userId).data.toString() }
}