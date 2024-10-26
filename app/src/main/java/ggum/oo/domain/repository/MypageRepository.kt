package ggum.oo.domain.repository

import ggum.oo.data.dto.NoneBaseResponse
import ggum.oo.domain.model.request.ClubMypageRequestModel
import ggum.oo.domain.model.response.MypageResponseModel


interface MypageRepository {

    suspend fun clubRequest(memberId:Int, clubName: String, studentId:String,name:String): Result<NoneBaseResponse>

    suspend fun clubReject(request: ClubMypageRequestModel): Result<NoneBaseResponse>

    suspend fun clubAccept(request: ClubMypageRequestModel): Result<NoneBaseResponse>

    suspend fun mypage(memberId: Int): Result<MypageResponseModel>

}