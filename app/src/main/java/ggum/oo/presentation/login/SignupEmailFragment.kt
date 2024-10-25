package ggum.oo.presentation.login

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.databinding.ActivityLoginBinding
import ggum.oo.databinding.FragmentSignupBinding
import ggum.oo.databinding.FragmentSignupEmailBinding
import ggum.oo.presentation.MainActivity
import ggum.oo.presentation.base.BaseActivity
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class SignupEmailFragment : BaseFragment<FragmentSignupEmailBinding>(R.layout.fragment_signup_email) {

    private val navigator by lazy { findNavController() }
    private val viewModel: SignupViewModel by activityViewModels()

    override fun initObserver() {
        // 필요한 Observer 초기화
    }

    override fun initView() {
        binding.tvSignupEmailButton.setOnSingleClickListener {
            validateEmailAndAuthenticate()
        }

        // TextWatcher를 명시적으로 구현
        binding.etSignupEmailAuthenticationNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // 필요 시 구현
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val authCode = s.toString()
                updateSignupButtonState(authCode)
            }

            override fun afterTextChanged(s: Editable?) {
                // 필요 시 구현
            }
        })

        binding.tvSignupEmailRetransmitButton.setOnSingleClickListener {
            val email = viewModel.email.value
            if (!email.isNullOrEmpty()) { // 이메일이 null이 아니고 비어있지 않을 때
                viewModel.authCode(email)
                // 인증 코드 재전송 알림
                Toast.makeText(requireContext(), "인증 코드가 재전송되었습니다.", Toast.LENGTH_SHORT).show()
            } else {
                // 이메일이 비어있거나 null인 경우 사용자에게 알림
                Toast.makeText(requireContext(), "유효한 이메일 주소를 입력하세요.", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun validateEmailAndAuthenticate() {
        val email = viewModel.email.value ?: return
        val authCode = binding.etSignupEmailAuthenticationNumber.text.toString()

        viewModel.authenticateEmail(email, authCode)
        viewModel.authenticationStatus.observe(viewLifecycleOwner, { isAuthenticated: Boolean ->  // 타입 명시
            if (isAuthenticated) {
                val intent = Intent(requireContext(), MainActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                binding.tvSignupEmailAuthenticationWarning.visibility = View.VISIBLE
            }
        })
    }


    private fun updateSignupButtonState(authCode: String) {
        val isAuthCodeValid = authCode.isNotEmpty() // 인증 코드가 비어있지 않은지 확인

        // 버튼의 활성화 상태 및 색상 변경
        binding.tvSignupEmailButton.isEnabled = isAuthCodeValid
        if (isAuthCodeValid) {
            binding.tvSignupEmailButton.setBackgroundResource(R.drawable.shape_rect_8_yellow_fill) // 버튼 활성화 색상
        } else {
            binding.tvSignupEmailButton.setBackgroundResource(R.drawable.shape_rect_8_yellow_fill30) // 버튼 비활성화 색상
        }
    }
}
