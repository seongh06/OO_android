package ggum.oo.data.datasource

import ggum.oo.data.dto.BaseResponse
import ggum.oo.data.dto.response.ContentListResponseDto
import ggum.oo.presentation.base.BaseFragment

interface ContentDataSource {

    suspend fun contentList(postId: Int): BaseResponse<ContentListResponseDto>

}