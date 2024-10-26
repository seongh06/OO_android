package ggum.oo.domain.model.request

import ggum.oo.data.dto.request.ClubMypageRequestDto
import java.io.Serializable

class ClubMypageRequestModel (
    val email: String,
    val clubName: String
): Serializable {
    fun toClubMypageRequestDto() =
        ClubMypageRequestDto(email, clubName)
}

