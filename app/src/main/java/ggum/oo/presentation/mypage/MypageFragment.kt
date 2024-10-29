package ggum.oo.presentation.mypage

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
        initView() // UI 초기화
    }

    override fun initObserver() {
        viewModel.userData.observe(viewLifecycleOwner) { userData ->
            userData?.let {
                binding.tvMypageNickname.text = it.nickName // UserData의 nickName
                binding.tvMypageEmail.text = it.email // UserData의 email
                val currentClubName = it.currentClubName ?: "동아리 없음"
                setupMyClubRecyclerView(it.allClubList, it.myClubList, currentClubName, it.role) // allClubList와 currentClubName, role 전달
            }
        }
    }

    private fun setupMyClubRecyclerView(
        allClubList: List<MypageResponseModel.AllClubListElementModel>,
        myClubList: List<MypageResponseModel.MyClubListElementModel>, // myClubList 추가
        currentClubName: String,
        userRole: String
    ) {
        // myClubList에서 clubName을 가져와 allClubList와 비교하여 필터링
        val myClubNames = myClubList.map { it.clubName } // myClubList에서 clubName 리스트 생성

        // allClubList에서 myClubNames에 포함된 클럽만 필터링
        val displayClubs = allClubList.filter { it.clubName == currentClubName }

        // 어댑터 초기화
        myClubAdapter = MypageMyClubRVA(displayClubs, currentClubName, userRole)
        binding.rvMypageMyClubList.adapter = myClubAdapter
        binding.rvMypageMyClubList.layoutManager = LinearLayoutManager(requireContext())
    }


    override fun initView() {
        // 동아리 목록 어댑터 초기화
        clubAdapter = MypageClubRVA(ClubList.clubName) { clubName ->
            selectedClubName = clubName
            binding.etMypageClubName.setText(clubName) // 선택된 동아리 이름을 EditText에 설정
            checkInputFields() // 동아리 선택 후 입력 필드 체크
        }

        binding.rvMypageClubList.adapter = clubAdapter
        binding.rvMypageClubList.layoutManager = LinearLayoutManager(requireContext())

        // 토글 버튼 클릭 리스너
        binding.ivMypageToggleButton.setOnClickListener {
            binding.rvMypageClubList.visibility =
                if (binding.rvMypageClubList.visibility == View.VISIBLE) View.GONE else View.VISIBLE
        }

        // 입력 필드에 텍스트 변경 리스너 추가
        binding.etMypageName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkInputFields() // 입력할 때마다 체크
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.etMypageNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkInputFields() // 입력할 때마다 체크
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        // 동아리 인증 요청 버튼 클릭 리스너
        binding.tvJoinButton.setOnSingleClickListener {
            handleJoinButtonClick()
        }

        binding.layoutMypageManager.setOnSingleClickListener {
            val email = binding.etMypageName.text.toString()
            val clubName = binding.etMypageClubName.text.toString()
            viewModel.ClubManager(email, clubName, requireContext())
        }

        binding.layoutMypageClubMember.setOnClickListener {
            showClubDialog()
        }
    }

    private fun checkInputFields() {
        val name = binding.etMypageName.text.toString()
        val studentId = binding.etMypageNumber.text.toString()

        // 모든 필드가 입력되었는지 확인
        if (name.isNotEmpty() && studentId.isNotEmpty() && selectedClubName != "동아리 없음") {
            binding.tvJoinButton.isEnabled = true
            binding.tvJoinButton.setBackgroundResource(R.drawable.shape_rect_8_yellow_fill) // 클릭 가능한 색상 리소스
        } else {
            binding.tvJoinButton.isEnabled = false
            binding.tvJoinButton.setBackgroundResource(R.drawable.shape_rect_8_gray_fill30) // 클릭 불가능 색상 리소스
        }
    }

    private fun handleJoinButtonClick() {
        if (selectedClubName != "동아리 없음") {
            val name = binding.etMypageName.text.toString()
            val studentId = binding.etMypageNumber.text.toString()
            // 클럽 요청 메서드 호출
            viewModel.ClubRequest(selectedClubName, studentId, name)
            Toast.makeText(requireContext(), "동아리 인증 요청이 전송되었습니다.", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(requireContext(), "동아리를 선택해 주세요.", Toast.LENGTH_SHORT).show()
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
