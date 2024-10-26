package ggum.oo.data.dto.response

import androidx.recyclerview.widget.LinearLayoutManager
import ggum.oo.domain.model.response.CommunityResponseModel

data class CommentResponseDto(
    val id: Int,
    val post: List<PostElementDto>,
    val member: MemberElementDto,
    val content: String,
    val createdAt: String
){
    data class PostElementDto(
        val id: Int,
        val title: String,
        val content: String,
        val hashtag: String,
        val postType: Boolean,
        val createdAt: String,
        val member: MemberPostElementDto,
        val commentCount: Int,
        val isExternal: Boolean
    ){
        data class MemberPostElementDto(
            val id: Int,
            val email: String,
            val nickname: String,
            val password: String,
            val phoneNumber: String,
            val refreshToken: String,
            val role: String,
            val auth: Boolean,
            val authCode: AuthCodePostElementDto,
            val myClubs: List<MyClubsPostElementDto>
        ){  fun toMemberPostElementModel() =
                CommunityResponseModel.PostElementModel.MemberPostElementModel(id, email, nickname, password, phoneNumber, refreshToken, role, auth, authCode.toAuthCodePostElementModel(), myClubs.map { it.toMyClubsPostElementModel() })
            data class AuthCodePostElementDto(
                val id: Int,
                val code: String,
                val member: String
            ){
                fun toAuthCodePostElementModel() =
                    CommunityResponseModel.PostElementModel.MemberPostElementModel.AuthCodePostElementModel(id, code, member)
            }
            data class MyClubsPostElementDto(
                val id: Int,
                val name: String,
                val studentId: String,
                val isConfirmed: String,
                val clubAndHeadMem:ClubAndHeadMemPostElementDto,
                val member: String
            ) {
                fun toMyClubsPostElementModel() =
                    CommunityResponseModel.PostElementModel.MemberPostElementModel.MyClubsPostElementModel(id, name, studentId, isConfirmed, clubAndHeadMem.toClubAndHeadMemPostElementModel(), member)

                data class ClubAndHeadMemPostElementDto(
                    val id: Int,
                    val clubName: String,
                    val managerEmail: String,
                    val clubMembers: List<String>
                ){
                    fun toClubAndHeadMemPostElementModel() =
                        CommunityResponseModel.PostElementModel.MemberPostElementModel.MyClubsPostElementModel.ClubAndHeadMemPostElementModel(id, clubName, managerEmail, clubMembers)
                }
            }
        }
    }
    data class MemberElementDto(
        val id: Int,
        val email: String,
        val nickname: String,
        val password: String,
        val phoneNumber: String,
        val refreshToken: String,
        val role: String,
        val auth: Boolean,
        val authCode: AuthCodeElementDto,
        val myClubs: List<MyClubsElementDto>
    ){
        data class AuthCodeElementDto(
            val id: Int,
            val code: String,
            val member: String
        )
        data class MyClubsElementDto(
            val id: Int,
            val name: String,
            val studentId: String,
            val isConfirmed: String,
            val clubAndHeadMem:ClubAndHeadMemElementDto,
            val member: String
        ){
            data class ClubAndHeadMemElementDto(
                val id: Int,
                val clubName: String,
                val managerEmail: String,
                val clubMembers: List<String>
            ){
                fun toClubAndHeadMemElementModel() =
                    CommunityResponseModel.MemberElementModel.MyClubsElementModel.ClubAndHeadMemElementModel(id, clubName, managerEmail, clubMembers)
            }
        }
    }
}
