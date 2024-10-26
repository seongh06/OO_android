package ggum.oo.domain.model.response

data class MypageResponseModel(
    val nickName: String,
    val email: String,
    val role: String,
    val myClubList: List<MyClubListElementModel>,
    val waitingMemberList: List<WaitingMemberListElementModel>,
    val allClubList: List<AllClubListElementModel>,
    val currentClubName: String
) {
    data class MyClubListElementModel(
        val clubName: String,
        val isConfirmed: String
    )
    data class WaitingMemberListElementModel(
        val name: String,
        val email: String,
        val studentId : String,
        val isConfirmed: String
    )
    data class AllClubListElementModel(
        val name: String,
        val isConfirmed: String
    )
}
