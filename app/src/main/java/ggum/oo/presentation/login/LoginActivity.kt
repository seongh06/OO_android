package ggum.oo.presentation.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import ggum.oo.R
import ggum.oo.databinding.ActivityLoginBinding
import ggum.oo.presentation.base.BaseActivity

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Setting up Navigation
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_login) as? NavHostFragment
        val navController = navHostFragment?.navController
    }
}