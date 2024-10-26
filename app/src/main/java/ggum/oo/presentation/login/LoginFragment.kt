package ggum.oo.presentation.login

import android.content.Intent
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.databinding.ActivityLoginBinding
import ggum.oo.databinding.FragmentLoginBinding
import ggum.oo.presentation.MainActivity
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val navigator by lazy { findNavController() }

    override fun initObserver() {

    }

    override fun initView() {

        binding.tvLoginSignup.setOnSingleClickListener {
            navigator.navigate(R.id.action_LoginFragment_to_SignupFragment)
        }
        binding.tvLoginButton.setOnSingleClickListener {
            Intent(requireContext(), MainActivity::class.java).apply {
                startActivity(this)
            }
            requireActivity().finish()
        }
    }
}