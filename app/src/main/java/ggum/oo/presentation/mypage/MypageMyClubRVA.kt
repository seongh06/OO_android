package ggum.oo.presentation.mypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ggum.oo.R
import ggum.oo.domain.model.response.MypageResponseModel

class MypageMyClubRVA(
    private val clubs: List<MypageResponseModel.MyClubListElementModel>,
    private val currentClubName: String // 현재 관리 중인 동아리 이름을 추가
) : RecyclerView.Adapter<MypageMyClubRVA.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val clubNameTextView: TextView = itemView.findViewById(R.id.tv_my_club_item)

        fun bind(club: MypageResponseModel.MyClubListElementModel) {
            clubNameTextView.text = club.clubName

            // 현재 관리 중인 동아리와 일치하는 경우 배경 리소스 변경
            if (club.clubName == currentClubName) {
                itemView.setBackgroundResource(R.drawable.shape_rect_100_yellow_fill) // 선택된 동아리 배경 리소스
            } else {
                itemView.setBackgroundResource(R.drawable.shape_rect_100_gray_line) // 기본 배경
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mypage_my_club, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(clubs[position])
    }

    override fun getItemCount(): Int = clubs.size
}

