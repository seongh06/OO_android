package ggum.oo.presentation.community

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import ggum.oo.R
import ggum.oo.data.service.ContentItem

class CommunityRVA(private val communityList: List<ContentItem>) : RecyclerView.Adapter<CommunityRVA.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvArea: TextView = itemView.findViewById(R.id.tv_content_area_item)
        private val tvTitle: TextView = itemView.findViewById(R.id.tv_content_title_item)
        private val tvBody: TextView = itemView.findViewById(R.id.tv_content_body_item)
        private val ivImage: ImageView = itemView.findViewById(R.id.iv_content_img)
        private val cvImageBlock: CardView = itemView.findViewById(R.id.cv_content_img_block)
        private val tvHour: TextView = itemView.findViewById(R.id.tv_content_hour_item)
        private val tvCommentCount: TextView = itemView.findViewById(R.id.tv_content_comment)

        fun bind(contentItem: ContentItem) {
            // Boolean 값을 기반으로 area 표시
            tvArea.text = if (contentItem.area) "교내" else "교외"
            tvTitle.text = contentItem.title
            setTextWithLimit(tvBody, contentItem.body, 30) // 최대 100자 제한

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
        holder.bind(communityList[position])
    }

    override fun getItemCount(): Int = communityList.size
}
