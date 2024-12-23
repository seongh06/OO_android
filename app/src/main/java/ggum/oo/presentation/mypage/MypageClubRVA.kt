package ggum.oo.presentation.mypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ggum.oo.R

class MypageClubRVA(
    private val club: List<String>,
    private val onClubSelected: (String) -> Unit // 선택된 동아리 이름을 전달할 콜백
) : RecyclerView.Adapter<MypageClubRVA.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.tv_club_item)

        fun bind(clubName: String) {
            textView.text = clubName

            // 아이템 클릭 리스너 설정
            itemView.setOnClickListener {
                onClubSelected(clubName) // 선택된 동아리 이름을 전달
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mypage_club_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(club[position])
    }

    override fun getItemCount(): Int = club.size
}
