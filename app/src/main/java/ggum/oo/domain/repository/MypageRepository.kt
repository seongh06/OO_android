package ggum.oo.domain.repository

import ggum.oo.data.dto.NoneBaseResponse
import ggum.oo.data.dto.request.ClubManagerRequestDto
import ggum.oo.data.dto.response.MypageResponseDto
import ggum.oo.domain.model.request.ClubManagerRequestModel
import ggum.oo.domain.model.request.ClubMypageRequestModel
import ggum.oo.domain.model.request.ClubRequestModel
import ggum.oo.domain.model.response.MypageResponseModel


interface MypageRepository {

    suspend fun clubRequest(request: ClubRequestModel): Result<NoneBaseResponse>

    suspend fun clubReject(request: ClubMypageRequestModel): Result<NoneBaseResponse>

    suspend fun clubAccept(request: ClubMypageRequestModel): Result<NoneBaseResponse>

    suspend fun mypage(): Result<MypageResponseModel>

    suspend fun clubManager(request: ClubManagerRequestModel):Result<NoneBaseResponse>

}