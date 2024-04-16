package dev.ptit.ui.screen.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import dagger.hilt.android.AndroidEntryPoint
import dev.ptit.R
import dev.ptit.databinding.ActivityLoginBinding


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcvLoginContainer) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            binding.tvScreenDescription.text = this.getString(
                when (destination.id) {
                    R.id.signInFragment -> {
                        R.string.sign_in_description
                    }

                    R.id.createAccountFragment -> {
                        R.string.create_account_description
                    }

                    R.id.createNewPasswordFragment -> {
                        R.string.create_new_password
                    }

                    R.id.forgotPasswordFragment -> {
                        R.string.forgot_password
                    }

                    else -> {
                        R.string.verify
                    }
                }
            )
        }
    }
}