/*
package ggum.oo.data.repositoryImpl

import ggum.oo.data.datasource.ContentDataSource
import ggum.oo.domain.model.response.CommunityResponseModel
import ggum.oo.domain.repository.ContentRepository
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentDataSource: ContentDataSource
):ContentRepository {

    override suspend fun communityComment(postId: Int, content: String): Result<CommunityResponseModel> =
        runCatching { contentDataSource.communityComment(postId, content).data.to }

}*/
