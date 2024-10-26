package ggum.oo.data.dto.response

import ggum.oo.domain.model.response.MypageResponseModel

data class MypageResponseDto(
    val nickName: String,
    val email: String,
    val role: String,
    val myClubList: List<MyClubListElementDto>,
    val waitingMemberList: List<WaitingMemberListElementDto>,
    val allClubList: List<AllClubListElementDto>,
    val currentClubName: String
){
    data class MyClubListElementDto(
        val clubName: String,
        val isConfirmed: String
    ) {
        fun toMyClubListElementModel() =
            MypageResponseModel.MyClubListElementModel(clubName, isConfirmed)
    }
    data class WaitingMemberListElementDto(
        val name: String,
        val email: String,
        val studentId : String,
        val isConfirmed: String
    ){
        fun toWaitingMemberListElementModel() =
            MypageResponseModel.WaitingMemberListElementModel(name, email, studentId, isConfirmed)
    }
    data class AllClubListElementDto(
        val clubName: String
    ){
        fun toAllClubListElementModel() =
            MypageResponseModel.AllClubListElementModel(clubName)
    }
    fun toMypageResponseModel() =
        MypageResponseModel(nickName, email, role, myClubList.map { it.toMyClubListElementModel()}, waitingMemberList.map{it.toWaitingMemberListElementModel()}, allClubList.map { it.toAllClubListElementModel() }, currentClubName)
}
