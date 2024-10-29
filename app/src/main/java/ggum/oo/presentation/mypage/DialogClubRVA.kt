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
import ggum.oo.domain.model.response.MypageResponseModel
import ggum.oo.presentation.login.SignupViewModel

class DialogClubRVA(
    private val waitingMembers: MutableList<MypageResponseModel.WaitingMemberListElementModel>,
    var onAccept: (String, String) -> Unit, // 승인 콜백을 public으로 변경
    var onReject: (String, String) -> Unit // 거절 콜백을 public으로 변경
) : RecyclerView.Adapter<DialogClubRVA.ClubViewHolder>() {

    class ClubViewHolder(private val binding: ItemDialogClubBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(member: MypageResponseModel.WaitingMemberListElementModel, onAccept: (String, String) -> Unit, onReject: (String, String) -> Unit) {
            binding.tvDialogClubNameItem.text = member.name
            binding.tvDialogClubNumberItem.text = member.studentId

            // 승인 버튼 클릭 리스너
            binding.tvDialogClubAcceptItem.setOnClickListener {
                onAccept(member.email, member.studentId) // 승인 콜백 호출
            }

            // 거절 버튼 클릭 리스너
            binding.tvDialogClubRejectItem.setOnClickListener {
                onReject(member.email, member.studentId) // 거절 콜백 호출
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClubViewHolder {
        val binding = ItemDialogClubBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClubViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClubViewHolder, position: Int) {
        holder.bind(waitingMembers[position], onAccept, onReject)
    }

    override fun getItemCount() = waitingMembers.size

    fun updateWaitingMembers(newWaitingMembers: List<MypageResponseModel.WaitingMemberListElementModel>) {
        waitingMembers.clear()
        waitingMembers.addAll(newWaitingMembers)
        notifyDataSetChanged() // 데이터 변경 알림
    }
}
