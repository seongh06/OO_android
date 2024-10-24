package ggum.oo.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ggum.oo.R
import ggum.oo.data.ContentItem

class HomePromotionRVA(private var contentList: List<ContentItem>) : RecyclerView.Adapter<HomePromotionRVA.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_home_content_title_item)
        private val tvBody: TextView = itemView.findViewById(R.id.tv_home_content_body_item)

        fun bind(contentItem: ContentItem) {
            setTextWithLimit(tvTitle, contentItem.title, 10)
            setTextWithLimit(tvBody, getFirstSentence(contentItem.body), 20)

        }

        private fun setTextWithLimit(textView: TextView, text: String, maxLength: Int) {
            val displayText = if (text.length > maxLength) {
                text.substring(0, maxLength) + "..."
            } else {
                text
            }
            textView.text = displayText
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_home_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contentList[position])
    }

    override fun getItemCount(): Int = contentList.size

    fun updateList(newList: List<ContentItem>) {
        contentList = newList.take(3) // 상위 3개 아이템만 유지
        notifyDataSetChanged() // 데이터가 변경되었음을 알림
    }

    private fun getFirstSentence(text: String): String {
        return text.split("\n")[0] // 첫 번째 줄만 반환
    }

}
