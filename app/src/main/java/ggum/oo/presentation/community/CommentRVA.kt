package ggum.oo.presentation.community

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ggum.oo.R
import ggum.oo.data.CommentItem

class CommentRVA(private var commentList: List<CommentItem>) : RecyclerView.Adapter<CommentRVA.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvQa: TextView = itemView.findViewById(R.id.tv_comment_qa_item)
        private val tvNickname: TextView = itemView.findViewById(R.id.tv_comment_nickname_item)
        private val tvBody: TextView = itemView.findViewById(R.id.tv_comment_body_item)

        fun bind(commentItem: CommentItem) {
            tvNickname.text = commentItem.nickname ?: "알 수 없음"
            tvBody.text = commentItem.body ?: "내용 없음"
            if (commentItem.writer) {
                tvQa.text = "Q."
                tvQa.setTextColor(ContextCompat.getColor(itemView.context, R.color.warning)) // warning 색상
            } else {
                tvQa.text = "A."
                tvQa.setTextColor(ContextCompat.getColor(itemView.context, R.color.answer)) // blue 색상
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(commentList[position])
    }

    override fun getItemCount(): Int = commentList.size

    fun updateList(newList: List<CommentItem>) {
        val diffResult = DiffUtil.calculateDiff(CommentDiffCallback(commentList, newList))
        commentList = newList
        diffResult.dispatchUpdatesTo(this)
    }

    class CommentDiffCallback(
        private val oldList: List<CommentItem>,
        private val newList: List<CommentItem>
    ) : DiffUtil.Callback() {
        override fun getOldListSize() = oldList.size
        override fun getNewListSize() = newList.size

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id // ID로 비교
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition] // 객체의 내용 비교
        }
    }
}
