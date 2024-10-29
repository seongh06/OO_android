package ggum.oo.presentation.mypage

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ggum.oo.R
import ggum.oo.databinding.DialogClubBinding // ViewBinding
import ggum.oo.domain.model.response.MypageResponseModel
import ggum.oo.presentation.base.BaseDialogFragment

class DialogClubFragment : BaseDialogFragment<DialogClubBinding>(R.layout.dialog_club) {

    private val viewModel: MypageViewModel by activityViewModels()
    private lateinit var adapter: DialogClubRVA

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

    override fun initView() {
        // RecyclerView 및 어댑터 설정
        binding.rvDialogClub.layoutManager = LinearLayoutManager(context)
        adapter = DialogClubRVA(mutableListOf(),
            { email, studentId -> viewModel.ClubAccept(email, studentId) }, // 승인 처리
            { email, studentId -> viewModel.ClubReject(email, studentId) } // 거절 처리
        ) // 빈 리스트로 초기화
        binding.rvDialogClub.adapter = adapter

        // LiveData 관찰
        initObserver() // 여기서 관찰 메서드를 호출해야 합니다.

        // 닫기 버튼 클릭 리스너 설정
        binding.btnConfirm.setOnClickListener {
            dismiss() // 다이얼로그 닫기
        }
    }

    override fun initObserver() {
        // 사용자 데이터를 관찰
        viewModel.userData.observe(viewLifecycleOwner) { userData ->
            Log.d("DialogClubFragment", "UserData updated: $userData") // 로그 추가

            // 대기 중인 멤버 리스트 업데이트
            val waitingMembers = userData.waitingMemberList
            adapter.updateWaitingMembers(waitingMembers) // 어댑터에 데이터 업데이트

            // 대기 중인 멤버가 없을 때 메시지 보이기
            if (waitingMembers.isEmpty()) {
                binding.tvDialogMessage.visibility = View.VISIBLE // 메시지 보이기
                binding.rvDialogClub.visibility = View.GONE // RecyclerView 숨기기
            } else {
                binding.tvDialogMessage.visibility = View.GONE // 메시지 숨기기
                binding.rvDialogClub.visibility = View.VISIBLE // RecyclerView 보이기
            }
        }
    }

    // Fragment가 시작될 때 사용자 정보를 가져오도록 설정
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.MyInform() // 사용자 정보 가져오기
    }

    fun setWaitingMembers(
        waitingMembers: List<MypageResponseModel.WaitingMemberListElementModel>,
        onAccept: (String, String) -> Unit,
        onReject: (String, String) -> Unit
    ) {
        adapter.updateWaitingMembers(waitingMembers) // 어댑터에 데이터 업데이트
        adapter.onAccept = onAccept // 승인 콜백 설정
        adapter.onReject = onReject // 거절 콜백 설정
    }

}
