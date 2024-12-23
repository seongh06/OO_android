package ggum.oo.data.repositoryImpl

import ggum.oo.data.datasource.MypageDataSource
import ggum.oo.data.dto.NoneBaseResponse
import ggum.oo.domain.model.request.ClubManagerRequestModel
import ggum.oo.domain.model.request.ClubMypageRequestModel
import ggum.oo.domain.model.request.ClubRequestModel
import ggum.oo.domain.model.response.MypageResponseModel
import ggum.oo.domain.repository.MypageRepository
import javax.inject.Inject

class MypageRepositoryImpl @Inject constructor(
    private val mypageDataSource: MypageDataSource
) : MypageRepository {

    override suspend fun clubRequest(request:ClubRequestModel): Result<NoneBaseResponse> =
        runCatching { mypageDataSource.clubRequest(request.toClubRequestDto()) }

    override suspend fun clubReject(request: ClubMypageRequestModel): Result<NoneBaseResponse> =
        runCatching { mypageDataSource.clubReject(request.toClubMypageRequestDto()) }

    override suspend fun clubAccept(request: ClubMypageRequestModel): Result<NoneBaseResponse> =
        runCatching { mypageDataSource.clubAccept(request.toClubMypageRequestDto()) }

    override suspend fun mypage(): Result<MypageResponseModel> =
        runCatching { mypageDataSource.mypage().data.toMypageResponseModel() }

    override suspend fun clubManager(request: ClubManagerRequestModel): Result<NoneBaseResponse> =
        runCatching { mypageDataSource.clubManger(request.toClubManagerRequestDto()) }

}