package ggum.oo.presentation.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ggum.oo.R
import ggum.oo.data.service.ContentItem

class ContentRVA(
    private var items: List<ContentItem>, // items를 var로 변경하여 업데이트 가능하도록 설정
    private val onItemClick: (ContentItem) -> Unit // 클릭 이벤트 핸들러
) : RecyclerView.Adapter<ContentRVA.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvArea: TextView = itemView.findViewById(R.id.tv_content_area_item)
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_content_title_item)
        private val tvBody: TextView = itemView.findViewById(R.id.tv_content_body_item)
        private val ivImage: ImageView = itemView.findViewById(R.id.iv_content_img)
        private val cvImageBlock: CardView = itemView.findViewById(R.id.cv_content_img_block)
        private val tvHour: TextView = itemView.findViewById(R.id.tv_content_hour_item)
        private val tvCommentCount: TextView = itemView.findViewById(R.id.tv_content_comment)

        fun bind(contentItem: ContentItem) {
            itemView.setOnClickListener { onItemClick(contentItem) } // 클릭 리스너 설정
            tvArea.text = if (contentItem.area) "교내" else "교외"
            setTextWithLimit(tvTitle, contentItem.title, 10)
            setTextWithLimit(tvBody, contentItem.body, 30) // 최대 30자 제한

            // 이미지가 null인 경우 CardView를 GONE으로 설정
            if (contentItem.image != null) {
                ivImage.setImageResource(contentItem.image)
                cvImageBlock.visibility = View.VISIBLE // CardView 보이기
            } else {
                cvImageBlock.visibility = View.GONE // CardView 숨기기
            }

            tvHour.text = contentItem.date
            tvCommentCount.text = contentItem.commentCount.toString()
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_content, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position]) // items 리스트에서 아이템 바인딩
    }

    override fun getItemCount(): Int = items.size // items의 크기 반환

    fun updateList(newList: List<ContentItem>) {
        items = newList // items를 새로운 리스트로 업데이트
        notifyDataSetChanged() // 데이터가 변경되었음을 알림
    }
}
