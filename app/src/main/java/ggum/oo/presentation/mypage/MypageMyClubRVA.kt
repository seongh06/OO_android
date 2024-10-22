package ggum.oo.presentation.mypage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ggum.oo.R

class MypageMyClubRVA(private val myClub: List<String>) : RecyclerView.Adapter<MypageMyClubRVA.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.tv_my_club_item)

        fun bind(myClubName: String) {
            textView.text = myClubName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_mypage_my_club, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(myClub[position])
    }

    override fun getItemCount(): Int = myClub.size
}
