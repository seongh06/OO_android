package ggum.oo.presentation.mypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ggum.oo.R
import ggum.oo.data.ClubRequestItem
import ggum.oo.databinding.ItemDialogClubRequestBinding

class DialogClubRequestRVA(private val clubRequestList: MutableList<ClubRequestItem>) : RecyclerView.Adapter<DialogClubRequestRVA.ClubViewHolder>() {

    class ClubViewHolder(private val binding: ItemDialogClubRequestBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clubItem: ClubRequestItem) {
            binding.tvDialogClubNameItem.text = clubItem.name
            updateButtonState(clubItem)
        }

        private fun updateButtonState(clubItem: ClubRequestItem) {
            when (clubItem.isCompleted) {
                1 -> { // 대기 상태
                    binding.tvDialogClubStateItem.text = "대기"
                    binding.tvDialogClubStateItem.setBackgroundResource(R.drawable.shape_rect_6_gray_fill)
                    binding.tvDialogClubStateItem.isEnabled = true
                }
                2 -> { // 수락 상태
                    binding.tvDialogClubStateItem.text = "승인"
                    binding.tvDialogClubStateItem.setBackgroundResource(R.drawable.shape_rect_6_yellow_fill)
                    binding.tvDialogClubStateItem.isEnabled = false // 수락 후 비활성화
                }
                3 -> { // 거절 상태
                    binding.tvDialogClubStateItem.text = "거절"
                    binding.tvDialogClubStateItem.setBackgroundResource(R.drawable.shape_rect_6_red_fill)
                    binding.tvDialogClubStateItem.isEnabled = false // 거절 후 비활성화
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubViewHolder {
        val binding = ItemDialogClubRequestBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClubViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        holder.bind(clubRequestList[position])
    }

    override fun getItemCount() = clubRequestList.size

    fun updateClubList(newClubList: List<ClubRequestItem>) {
        clubRequestList.clear()
        clubRequestList.addAll(newClubList)
        notifyDataSetChanged() // 데이터 변경 알림
    }
}
