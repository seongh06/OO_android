package ggum.oo.presentation.login

import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.databinding.FragmentSignupBinding
import ggum.oo.presentation.base.BaseFragment
import ggum.oo.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class SignupFragment : BaseFragment<FragmentSignupBinding>(R.layout.fragment_signup) {

    private val navigator by lazy { findNavController() }

    override fun initObserver() {

    }

    override fun initView() {
        binding.tvSignupButton.setOnSingleClickListener {
            navigator.navigate(R.id.action_SignupFragment_to_SignupEmailFragment)
        }
    }

}