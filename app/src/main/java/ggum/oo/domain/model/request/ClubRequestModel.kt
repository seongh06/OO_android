package ggum.oo.domain.model.request

import ggum.oo.data.dto.request.ClubMypageRequestDto
import ggum.oo.data.dto.request.ClubRequestDto
import java.io.Serializable

class ClubRequestModel (
    val clubName: String,
    val studentId: String,
    val name: String
): Serializable {
    fun toClubRequestDto() =
        ClubRequestDto(clubName, studentId, name)
}