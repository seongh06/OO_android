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
import ggum.oo.presentation.base.BaseDialogFragment


class DialogClubFragment : BaseDialogFragment<DialogClubBinding>(R.layout.dialog_club) {

    private val viewModel: MypageViewModel by activityViewModels()
    private lateinit var adapter: DialogClubRVA

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return dialog
    }

    override fun initObserver() {
        viewModel.clubList.observe(viewLifecycleOwner) { clubList ->
            Log.d("DialogClubFragment", "ClubList updated: $clubList") // 로그 추가
            adapter.updateClubList(clubList) // 어댑터에 데이터 업데이트
            // 신청자가 없을 때 메시지 보이기
            if (clubList.isEmpty()) {
                binding.tvDialogMessage.visibility = View.VISIBLE // 메시지 보이기
                binding.rvDialogClub.visibility = View.GONE // RecyclerView 숨기기
            } else {
                binding.tvDialogMessage.visibility = View.GONE // 메시지 숨기기
                binding.rvDialogClub.visibility = View.VISIBLE // RecyclerView 보이기
            }
        }
    }

    override fun initView() {
        // RecyclerView 및 어댑터 설정
        binding.rvDialogClub.layoutManager = LinearLayoutManager(context)
        adapter = DialogClubRVA(mutableListOf()) // 빈 리스트로 초기화
        binding.rvDialogClub.adapter = adapter

        // LiveData 관찰
        initObserver() // 여기서 관찰 메서드를 호출해야 합니다.

        // 닫기 버튼 클릭 리스너 설정
        binding.btnConfirm.setOnClickListener {
            dismiss() // 다이얼로그 닫기
        }
    }

}
