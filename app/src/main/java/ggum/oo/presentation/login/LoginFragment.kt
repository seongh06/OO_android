package ggum.oo.presentation.login

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.webkit.ValueCallback
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.data.dto.request.LoginRequestDto
import ggum.oo.databinding.ActivityLoginBinding
import ggum.oo.databinding.FragmentLoginBinding
import ggum.oo.domain.model.request.LoginRequestModel
import ggum.oo.presentation.MainActivity
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.presentation.mypage.MypageViewModel
import ggum.oo.util.extension.setOnSingleClickListener
import java.net.CookieManager

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val navigator by lazy { findNavController() }
    private val viewModel: SignupViewModel by activityViewModels()
    override fun initView() {
        // 로그인 버튼 클릭 리스너

        binding.tvLoginButton.setOnClickListener {
            val email = binding.etLoginEmail.text.toString()
            val password = binding.etLoginPassword.text.toString()

            // LoginRequestDto 객체 생성
            val loginRequest = LoginRequestModel(email, password)

            // viewModel의 login 메서드 호출
            viewModel.login(loginRequest)


            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }


        // 회원가입 버튼 클릭 리스너
        binding.tvLoginSignup.setOnSingleClickListener {
            navigator.navigate(R.id.action_LoginFragment_to_SignupFragment)
        }

        // 입력 필드에 TextWatcher 추가
        binding.etLoginEmail.addTextChangedListener(textWatcher)
        binding.etLoginPassword.addTextChangedListener(textWatcher)

        // 초기 로그인 버튼 상태 업데이트
        updateLoginButtonState()
    }

    private val textWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            updateLoginButtonState()
        }

        override fun afterTextChanged(s: Editable?) {}
    }

    private fun updateLoginButtonState() {
        val email = binding.etLoginEmail.text.toString().trim()
        val password = binding.etLoginPassword.text.toString().trim()

        // 이메일과 비밀번호가 모두 입력되었는지 확인
        if (email.isNotEmpty() && password.isNotEmpty()) {
            binding.tvLoginButton.setBackgroundResource(R.drawable.shape_rect_8_yellow_fill) // 활성화된 배경
            binding.tvLoginButton.isEnabled = true
        } else {
            binding.tvLoginButton.setBackgroundResource(R.drawable.shape_rect_8_yellow_fill30) // 비활성화된 배경
            binding.tvLoginButton.isEnabled = false
        }
    }

    override fun initObserver() {
        viewModel.loginStatus.observe(viewLifecycleOwner, { isSuccess ->
            if (isSuccess) {
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                Toast.makeText(requireContext(), "로그인 실패", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
