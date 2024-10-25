package ggum.oo.presentation.login

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.databinding.FragmentSignupBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.presentation.mypage.MypageViewModel
import ggum.oo.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class SignupFragment : BaseFragment<FragmentSignupBinding>(R.layout.fragment_signup) {

    private val viewModel: SignupViewModel by activityViewModels()
    private val navigator by lazy { findNavController() }

    override fun initObserver() {
        observeInputFields()
        viewModel.navigateToSignupEmail.observe(viewLifecycleOwner, { shouldNavigate ->
            if (shouldNavigate) {
                // SignupEmailFragment로 이동
                navigator.navigate(R.id.signupEmailFragment) // 적절한 ID로 변경
            }
        })
    }

    override fun initView() {
        binding.tvSignupButton.setOnSingleClickListener {
            saveSignUpInform() // 정보 저장
        }
        binding.tvSignupEmailCheckButton.setOnSingleClickListener { // 주소 확인 버튼 클릭 시
            validateEmail()
        }
        binding.tvSignupNicknameCheckButton.setOnSingleClickListener {
            validateNickname()
        }

        // 비밀번호 확인 필드에 TextWatcher 추가
        binding.etSignupPassword.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkPasswordValidity(s.toString())
                updateSignupButtonState() // 비밀번호가 변경될 때 버튼 상태 업데이트
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        binding.etSignupPasswordCheck.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                checkPasswordMatch() // 비밀번호 확인이 변경될 때마다 확인
                updateSignupButtonState() // 비밀번호 확인이 변경될 때 버튼 상태 업데이트
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun observeInputFields() {
        val emailField = binding.etSignupEmail
        val passwordField = binding.etSignupPassword
        val passwordCheckField = binding.etSignupPasswordCheck
        val phoneField = binding.etSignupPhone
        val nicknameField = binding.etSignupNickname

        // 각 EditText의 텍스트 변화에 따라 버튼 색상 변경
        val fields = arrayOf(emailField, passwordField, passwordCheckField, phoneField, nicknameField)

        fields.forEach { field ->
            field.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    updateSignupButtonState() // 텍스트가 바뀔 때 버튼 상태 업데이트
                }
                override fun afterTextChanged(s: Editable?) {}
            })
        }
    }

    private fun validateEmail() {
        val email = binding.etSignupEmail.text.toString()
        viewModel.updateEmail(email)

        viewModel.validEmail(email)

        viewModel.validEmailStatus.observe(viewLifecycleOwner, { isValid ->
            if (isValid) {
                binding.tvSignupEmailWarning.visibility = View.GONE
                binding.tvSignupEmailValidSuccess.visibility = View.VISIBLE
            } else {
                binding.tvSignupEmailWarning.visibility = View.VISIBLE
                binding.tvSignupEmailValidSuccess.visibility = View.GONE
            }
        })
    }

    private fun validateNickname() {
        val nickname = binding.etSignupNickname.text.toString()

        // 닉네임 길이 체크
        if (nickname.length < 2 || nickname.length > 8) {
            // 2자~8자 이내 조건 실패
            binding.tvSignupNicknameWarning.visibility = View.VISIBLE
            binding.tvSignupNicknameValidSuccess.visibility = View.GONE
            binding.tvSignupNicknameValidWarning.visibility = View.GONE
            return
        } else {
            binding.tvSignupNicknameWarning.visibility = View.GONE
        }

        viewModel.validNickname(nickname)

        // validNicknameStatus를 관찰하여 UI 업데이트
        viewModel.validNicknameStatus.observe(viewLifecycleOwner, { isValid ->
            if (isValid) {
                // 닉네임 사용 가능
                binding.tvSignupNicknameValidSuccess.visibility = View.VISIBLE
                binding.tvSignupNicknameValidWarning.visibility = View.GONE
            } else {
                // 닉네임 중복
                binding.tvSignupNicknameValidWarning.visibility = View.VISIBLE
                binding.tvSignupNicknameValidSuccess.visibility = View.GONE
            }
        })
    }


    private fun isValidEmail(email: String): Boolean {
        return email.endsWith("@catholic.ac.kr")
    }

    private fun checkPasswordMatch() {
        val password = binding.etSignupPassword.text.toString()
        val passwordCheck = binding.etSignupPasswordCheck.text.toString()

        // 비밀번호가 일치하지 않으면 경고 메시지 표시
        if (password != passwordCheck) {
            binding.tvSignupPasswordCheckWarning.visibility = View.VISIBLE
        } else {
            binding.tvSignupPasswordCheckWarning.visibility = View.GONE
        }
    }

    private fun updateSignupButtonState() {
        val email = binding.etSignupEmail.text.toString()
        val password = binding.etSignupPassword.text.toString()
        val passwordCheck = binding.etSignupPasswordCheck.text.toString()
        val phone = binding.etSignupPhone.text.toString()
        val nickname = binding.etSignupNickname.text.toString()

        // 모든 필드가 채워지고 이메일이 유효하며 비밀번호가 일치할 때 버튼 활성화
        val isAllFieldsFilled = email.isNotEmpty() &&
                password.isNotEmpty() &&
                passwordCheck.isNotEmpty() &&
                phone.isNotEmpty() &&
                nickname.isNotEmpty()

        val isEmailValid = isValidEmail(email)
        val isPasswordMatch = password == passwordCheck

        // 버튼의 색상 및 클릭 가능 여부 변경
        binding.tvSignupButton.isEnabled = isAllFieldsFilled && isEmailValid && isPasswordMatch

        if (binding.tvSignupButton.isEnabled) {
            binding.tvSignupButton.setBackgroundResource(R.drawable.shape_rect_8_yellow_fill)
        } else {
            binding.tvSignupButton.setBackgroundResource(R.drawable.shape_rect_8_yellow_fill30)
        }
    }

    private fun saveSignUpInform() {
        val email = binding.etSignupEmail.text.toString()
        val password = binding.etSignupPassword.text.toString()
        val  passwordCheck = binding.etSignupPasswordCheck.text.toString()
        val phone = binding.etSignupPhone.text.toString()
        val nickname = binding.etSignupNickname.text.toString()

        // 데이터 유효성 검사
        if (email.isEmpty() || password.isEmpty() || phone.isEmpty() || nickname.isEmpty()) {
            Toast.makeText(requireContext(), "모든 필드를 입력해 주세요.", Toast.LENGTH_SHORT).show()
            return
        }


        // ViewModel에 사용자 입력 저장
        viewModel.updateEmail(email)
        viewModel.updatePassword(password)
        viewModel.updatePhone(phone)
        viewModel.updateNickname(nickname)

        // 회원가입 시도
        viewModel.signUp()

        navigator.navigate(R.id.action_SignupFragment_to_SignupEmailFragment)
    }

    private fun checkPasswordValidity(password: String) {
        // 비밀번호 조건: 8자~12자, 대문자, 소문자, 숫자 포함
        val passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,12}$"
        val isValid = password.matches(passwordPattern.toRegex())

        // 경고 메시지 표시 여부 결정
        binding.tvSignupPasswordWarning.visibility = if (isValid) View.GONE else View.VISIBLE
    }
}
