package ggum.oo.presentation.mypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import ggum.oo.R
import ggum.oo.domain.model.response.MypageResponseModel

class MypageMyClubRVA(
    private val clubs: List<MypageResponseModel.AllClubListElementModel>,
    private val currentClubName: String, // 현재 동아리 이름
    private val userRole: String // 사용자 역할
) : RecyclerView.Adapter<MypageMyClubRVA.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val clubNameTextView: TextView = itemView.findViewById(R.id.tv_my_club_item)

        fun bind(club: MypageResponseModel.AllClubListElementModel) {
            clubNameTextView.text = club.clubName

            // 현재 동아리와 운영자인지 확인하여 배경 리소스 변경
            if (club.clubName == currentClubName && userRole == "MANAGER") {
                itemView.setBackgroundResource(R.drawable.shape_rect_100_yellow_fill)
                clubNameTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
            } else {
                itemView.setBackgroundResource(R.drawable.shape_rect_100_gray_line)
                clubNameTextView.setTextColor(ContextCompat.getColor(itemView.context, R.color.black))
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
