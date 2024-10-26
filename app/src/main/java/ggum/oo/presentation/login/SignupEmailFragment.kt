package ggum.oo.presentation.login

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.databinding.FragmentSignupEmailBinding
import ggum.oo.presentation.MainActivity
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class SignupEmailFragment : BaseFragment<FragmentSignupEmailBinding>(R.layout.fragment_signup_email) {

    private val viewModel: SignupViewModel by activityViewModels()

    override fun initObserver() {
        viewModel.authenticationStatus.observe(viewLifecycleOwner, { isAuthenticated ->
            if (isAuthenticated) {
                // 인증 성공 시 메인 화면으로 이동
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            } else {
                // 인증 실패 시 경고 메시지 표시
                binding.tvSignupEmailAuthenticationWarning.visibility = View.VISIBLE
            }
        })
    }

    override fun initView() {
        binding.tvSignupEmailButton.setOnClickListener {
            val authCode = binding.etSignupEmailAuthenticationNumber.text.toString()
            viewModel.authentication(authCode)
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
            if (!email.isNullOrEmpty()) { viewModel.authCode(email) } }

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
