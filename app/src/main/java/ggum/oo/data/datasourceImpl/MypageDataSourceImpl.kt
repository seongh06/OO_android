package ggum.oo.data.datasourceImpl

import ggum.oo.data.datasource.MypageDataSource
import ggum.oo.data.dto.BaseResponse
import ggum.oo.data.dto.NoneBaseResponse
import ggum.oo.data.dto.request.ClubMypageRequestDto
import ggum.oo.data.dto.response.MypageResponseDto
import ggum.oo.data.service.MypageService
import javax.inject.Inject

class MypageDataSourceImpl @Inject constructor(
    private val mypageService: MypageService
): MypageDataSource{

    override suspend fun clubRequest(clubName: String, studentId: String, name: String): NoneBaseResponse =
        mypageService.clubRequest(clubName, studentId, name)

    override suspend fun clubReject(request: ClubMypageRequestDto): NoneBaseResponse =
        mypageService.clubReject(request)

    override suspend fun clubAccept(request: ClubMypageRequestDto): NoneBaseResponse =
        mypageService.clubAccept(request)

    override suspend fun mypage(): BaseResponse<MypageResponseDto> =
        mypageService.mypage()

}