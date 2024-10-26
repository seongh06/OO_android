package ggum.oo.data.datasource

import ggum.oo.data.dto.BaseResponse
import ggum.oo.data.dto.NoneBaseResponse
import ggum.oo.data.dto.request.ClubManagerRequestDto
import ggum.oo.data.dto.request.ClubMypageRequestDto
import ggum.oo.data.dto.request.ClubRequestDto
import ggum.oo.data.dto.response.MypageResponseDto

interface MypageDataSource {

    suspend fun clubRequest(request: ClubRequestDto): NoneBaseResponse

    suspend fun clubReject(request: ClubMypageRequestDto): NoneBaseResponse

    suspend fun clubAccept(request: ClubMypageRequestDto): NoneBaseResponse

    suspend fun mypage(): BaseResponse<MypageResponseDto>

    suspend fun clubManger(requestDto: ClubManagerRequestDto): NoneBaseResponse
}