package ggum.oo.data.repositoryImpl

import ggum.oo.data.datasource.ContentDataSource
import ggum.oo.data.datasource.MypageDataSource
import ggum.oo.data.dto.NoneBaseResponse
import ggum.oo.domain.model.request.ClubManagerRequestModel
import ggum.oo.domain.model.request.ClubMypageRequestModel
import ggum.oo.domain.model.request.ClubRequestModel
import ggum.oo.domain.model.response.ContentListResponseModel
import ggum.oo.domain.model.response.MypageResponseModel
import ggum.oo.domain.repository.ContentRepository
import ggum.oo.domain.repository.MypageRepository
import javax.inject.Inject

class ContentRepositoryImpl @Inject constructor(
    private val contentDataSource: ContentDataSource
) : ContentRepository {

    override suspend fun contentList(postId: Int): Result<ContentListResponseModel> =
        runCatching { contentDataSource.contentList(postId).data.toContentListResponseModel()}

}