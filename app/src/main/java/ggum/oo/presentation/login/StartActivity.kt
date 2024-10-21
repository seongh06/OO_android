package ggum.oo.presentation.login

import android.content.Intent
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.databinding.ActivityStartBinding
import ggum.oo.presentation.base.BaseActivity
import ggum.oo.util.extension.repeatOnStarted
import ggum.oo.util.extension.setOnSingleClickListener

@AndroidEntryPoint
class StartActivity : BaseActivity<ActivityStartBinding>(R.layout.activity_start) {

    override fun initView() {

        binding.tvStartNext.setOnSingleClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    override fun initObserver() {

    }
}
