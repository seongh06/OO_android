package ggum.oo.domain.repository

import ggum.oo.domain.model.response.CommunityResponseModel

interface ContentRepository {
    suspend fun communityComment(postId: Int, content: String): Result<CommunityResponseModel>
}