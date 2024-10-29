package ggum.oo.domain.repository

import ggum.oo.domain.model.response.ContentListResponseModel

interface ContentRepository {

    suspend fun contentList(postId: Int): Result<ContentListResponseModel>

}