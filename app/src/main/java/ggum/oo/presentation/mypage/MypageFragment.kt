package ggum.oo.presentation.mypage

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.data.ClubList
import ggum.oo.databinding.FragmentMypageBinding
import ggum.oo.domain.model.response.MypageResponseModel
import ggum.oo.presentation.base.BaseFragment
import androidx.lifecycle.Observer
import ggum.oo.presentation.login.SignupViewModel
import ggum.oo.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class MypageFragment : BaseFragment<FragmentMypageBinding>(R.layout.fragment_mypage) {

    private lateinit var clubAdapter: MypageClubRVA
    private lateinit var myClubAdapter: MypageMyClubRVA
    private val viewModel: MypageViewModel by activityViewModels()

    private var selectedClubName: String = "동아리 없음" // 선택된 동아리 이름 변수
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver() // Observer 설정
        viewModel.MyInform() // 사용자 정보 가져오기
    }

    override fun initObserver() {
        viewModel.userData.observe(viewLifecycleOwner, Observer { userData ->
            userData?.let {
                // UI에 사용자 정보를 반영
                binding.tvMypageNickname.text = it.nickName // UserData의 nickName
                binding.tvMypageEmail.text = it.email // UserData의 email
            }
        })
    }


    private fun setupMyClubRecyclerView(myClubList: List<MypageResponseModel.MyClubListElementModel>, currentClubName: String) {
        val confirmedClubs = myClubList.filter { it.isConfirmed == "CONFIRMED" }
        val adapter = MypageMyClubRVA(confirmedClubs, currentClubName) // currentClubName 전달
        binding.rvMypageMyClubList.adapter = adapter
        binding.rvMypageMyClubList.layoutManager = LinearLayoutManager(requireContext())
    }



    override fun initView() {
        clubAdapter = MypageClubRVA(ClubList.clubName) { clubName ->
            selectedClubName = clubName
            binding.etMypageClubName.setText(clubName) // 선택된 동아리 이름을 EditText에 설정
        }

        binding.rvMypageClubList.adapter = clubAdapter
        binding.rvMypageClubList.layoutManager = LinearLayoutManager(requireContext())

        binding.ivMypageToggleButton.setOnClickListener {
            binding.rvMypageClubList.visibility =
                if (binding.rvMypageClubList.visibility == View.VISIBLE) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
        }
        binding.layoutMypageManager.setOnSingleClickListener {
            val email = binding.etMypageName.text.toString()
            val clubName = binding.etMypageClubName.text.toString()

            viewModel.ClubManager(email, clubName, requireContext())
            binding.ivMypageBack.setOnClickListener {
                findNavController().navigateUp()
            }

            binding.layoutMypageClubMember.setOnClickListener {
                showClubDialog()
            }

            binding.tvJoinButton.setOnSingleClickListener {
                // 선택된 동아리 이름이 null인지 확인
                if (selectedClubName != "동아리 없음") {
                    // EditText에서 사용자 입력값 가져오기
                    val name = binding.etMypageName.text.toString()
                    val studentId = binding.etMypageNumber.text.toString()

                    // 클럽 요청 메서드 호출
                    viewModel.ClubRequest(selectedClubName, studentId, name)
                } else {
                    // 선택된 동아리가 없을 경우 처리 (예: Toast 메시지)
                    Toast.makeText(requireContext(), "동아리를 선택해 주세요.", Toast.LENGTH_SHORT).show()
                }
            }

            binding.tvMypageProfileInform.setOnSingleClickListener {
                viewModel.ClubReject("email", "clubName")
            }
        }
    }
    fun showClubDialog() {
        val dialogFragment = DialogClubFragment()
        dialogFragment.show(childFragmentManager, "DialogClubFragment")
    }

    fun showClubRequestDialog() {
        val dialogFragment = DialogClubRequestFragment()
        dialogFragment.show(childFragmentManager, "DialogClubRequestFragment")
    }
}
