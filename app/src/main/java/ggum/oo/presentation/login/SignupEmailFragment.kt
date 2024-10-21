package ggum.oo.presentation.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
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

    override fun initObserver() {

    }

    override fun initView() {

        binding.tvSignupEmailButton.setOnSingleClickListener {
            Intent(requireContext(), MainActivity::class.java).apply {
                startActivity(this)
            }
            requireActivity().finish()
        }
    }

}