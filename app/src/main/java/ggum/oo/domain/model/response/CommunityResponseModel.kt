package ggum.oo.domain.model.response

data class CommunityResponseModel(
    val id: Int,
    val post: List<PostElementModel>,
    val member: MemberElementModel,
    val content: String,
    val createdAt: String
){
    data class PostElementModel(
        val id: Int,
        val title: String,
        val content: String,
        val hashtag: String,
        val postType: Boolean,
        val createdAt: String,
        val member: MemberPostElementModel,
        val commentCount: Int,
        val isExternal: Boolean
    ){
        data class MemberPostElementModel(
            val id: Int,
            val email: String,
            val nickname: String,
            val password: String,
            val phoneNumber: String,
            val refreshToken: String,
            val role: String,
            val auth: Boolean,
            val authCode: AuthCodePostElementModel,
            val myClubs: List<MyClubsPostElementModel>
        ){
            data class AuthCodePostElementModel(
                val id: Int,
                val code: String,
                val member: String
            )
            data class MyClubsPostElementModel(
                val id: Int,
                val name: String,
                val studentId: String,
                val isConfirmed: String,
                val clubAndHeadMem:ClubAndHeadMemPostElementModel,
                val member: String
            ){
                data class ClubAndHeadMemPostElementModel(
                    val id: Int,
                    val clubName: String,
                    val managerEmail: String,
                    val clubMembers: List<String>
                )
            }
        }
    }
    data class MemberElementModel(
        val id: Int,
        val email: String,
        val nickname: String,
        val password: String,
        val phoneNumber: String,
        val refreshToken: String,
        val role: String,
        val auth: Boolean,
        val authCode: AuthCodeElementModel,
        val myClubs: List<MyClubsElementModel>
    ){
        data class AuthCodeElementModel(
            val id: Int,
            val code: String,
            val member: String
        )
        data class MyClubsElementModel(
            val id: Int,
            val name: String,
            val studentId: String,
            val isConfirmed: String,
            val clubAndHeadMem:ClubAndHeadMemElementModel,
            val member: String
        ){
            data class ClubAndHeadMemElementModel(
                val id: Int,
                val clubName: String,
                val managerEmail: String,
                val clubMembers: List<String>
            )
        }
    }
}
