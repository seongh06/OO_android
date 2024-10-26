package ggum.oo.presentation.mypage

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import ggum.oo.R
import ggum.oo.data.ClubItem
import ggum.oo.databinding.ItemDialogClubBinding // ViewBinding을 사용한다고 가정

class DialogClubRVA(private val clubList: MutableList<ClubItem>) : RecyclerView.Adapter<DialogClubRVA.ClubViewHolder>() {

    class ClubViewHolder(private val binding: ItemDialogClubBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(clubItem: ClubItem, position: Int, onAccept: (Int) -> Unit, onReject: () -> Unit) {
            binding.tvDialogClubNameItem.text = clubItem.name
            binding.tvDialogClubNumberItem.text = clubItem.number

            // 상태에 따라 버튼 텍스트 및 배경 설정
            updateButtonState(clubItem)

            // 승인 버튼 클릭 리스너
            binding.tvDialogClubAcceptItem.setOnClickListener {
                if (!clubItem.isCompleted) { // 완료 상태가 아닐 경우에만 승인
                    clubItem.isCompleted = true // 완료 상태로 변경
                    onAccept(position) // 승인 콜백 호출, 현재 위치 전달
                } else {
                    // 이미 완료된 경우 승인 취소
                    clubItem.isCompleted = false // 완료 상태 해제
                    onAccept(position) // 승인 콜백 호출, 현재 위치 전달
                    updateButtonState(clubItem) // 버튼 상태 업데이트
                }
            }

            // 거절 버튼 클릭 리스너
            binding.tvDialogClubRejectItem.setOnClickListener {
                if (!clubItem.isCompleted) { // 완료 상태가 아닐 경우에만 삭제
                    onReject() // 거절 콜백 호출
                } else {
                    // 완료된 동아리 신청자는 삭제할 수 없음을 사용자에게 알림
                    Toast.makeText(binding.root.context, "완료된 신청자는 삭제할 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // 버튼 상태 업데이트 메서드
        private fun updateButtonState(clubItem: ClubItem) {
            if (clubItem.isCompleted) {
                binding.tvDialogClubAcceptItem.text = "완료"
                binding.tvDialogClubAcceptItem.setBackgroundResource(R.drawable.shape_rect_6_gray_fill)
                binding.tvDialogClubAcceptItem.isEnabled = true // 완료 상태에서도 클릭 가능
            } else {
                binding.tvDialogClubAcceptItem.text = "승인"
                binding.tvDialogClubAcceptItem.setBackgroundResource(R.drawable.shape_rect_6_yellow_fill)
                binding.tvDialogClubAcceptItem.isEnabled = true // 승인 버튼 활성화
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubViewHolder {
        val binding = ItemDialogClubBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClubViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        holder.bind(clubList[position], position, {
            notifyItemChanged(it) // UI 업데이트
        }, {
            // 거절 처리
            val removedItem = clubList[position]
            clubList.removeAt(position) // 리스트에서 아이템 삭제
            notifyItemRemoved(position) // 리스트 변경 알림
            notifyItemRangeChanged(position, clubList.size) // 삭제 후 아이템 인덱스 조정
        })
    }

    override fun getItemCount() = clubList.size

    fun updateClubList(newClubList: List<ClubItem>) {
        clubList.clear()
        clubList.addAll(newClubList)
        notifyDataSetChanged() // 데이터 변경 알림
    }
}
