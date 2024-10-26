package ggum.oo.presentation.promotion

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ggum.oo.R
import ggum.oo.data.CommentItem

class PromotionImageRVA(
    private var items: List<Int>, // List<Int>로 리소스 ID를 받음
) : RecyclerView.Adapter<PromotionImageRVA.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.iv_promotion_post_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_promotion_image, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.imageView.setImageResource(items[position]) // 리소스 ID로 이미지 설정
    }

    override fun getItemCount(): Int = items.size // items의 크기 반환

    fun updateList(newList: List<Int>) {
        items = newList // items를 새로운 리스트로 업데이트
        notifyDataSetChanged() // 데이터가 변경되었음을 알림
    }
}
