package ggum.oo.domain.model.request

import ggum.oo.data.dto.request.ClubManagerRequestDto
import ggum.oo.data.dto.request.ClubMypageRequestDto
import ggum.oo.data.dto.request.ClubRequestDto
import java.io.Serializable

class ClubManagerRequestModel (
    val email:String,
    val clubName: String
): Serializable {
    fun toClubManagerRequestDto() =
        ClubManagerRequestDto(email, clubName)
}

