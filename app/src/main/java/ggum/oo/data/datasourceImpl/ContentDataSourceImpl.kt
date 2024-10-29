package ggum.oo.data.datasourceImpl

import ggum.oo.data.datasource.ContentDataSource
import ggum.oo.data.datasource.MypageDataSource
import ggum.oo.data.dto.BaseResponse
import ggum.oo.data.dto.ListBaseResponse
import ggum.oo.data.dto.NoneBaseResponse
import ggum.oo.data.dto.request.ClubManagerRequestDto
import ggum.oo.data.dto.request.ClubMypageRequestDto
import ggum.oo.data.dto.request.ClubRequestDto
import ggum.oo.data.dto.response.ContentListResponseDto
import ggum.oo.data.dto.response.MypageResponseDto
import ggum.oo.data.service.ContentService
import ggum.oo.data.service.MypageService
import javax.inject.Inject

class ContentDataSourceImpl @Inject constructor(
    private val contentService: ContentService
): ContentDataSource{

    override suspend fun contentList(postId: Int): BaseResponse<ContentListResponseDto> =
        contentService.contentList(postId)

}