package ggum.oo.presentation.login

import android.content.Intent
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.widget.TextView
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.databinding.ActivitiyTestBinding
import ggum.oo.databinding.ActivityLoginBinding
import ggum.oo.presentation.base.BaseActivity
import ggum.oo.util.extension.repeatOnStarted
import ggum.oo.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class LoginActivity : BaseActivity<ActivityLoginBinding>(R.layout.activity_login) {

    override fun initView() {

        /*binding.tvLoginSignup.setOnSingleClickListener {
            val intent = Intent(this, SignUpFragment::class.java)
            startActivity(intent) // LoginActivity 시작
            finish()
        }*/

    }

    override fun initObserver() {
    }
}