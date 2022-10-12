package br.com.dionataferraz.vendas.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import br.com.dionataferraz.vendas.R
import br.com.dionataferraz.vendas.databinding.ActivitySplashBinding
import br.com.dionataferraz.vendas.viewmodel.LoginViewModel

class SplashActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = LoginViewModel()

        viewModel.existUsers()

        viewModel.shouldShowHome.observe(this) {
            if (it) {
                Handler().postDelayed({
                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 3000)
            } else {
                Handler().postDelayed({
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }, 3000)
            }
        }
    }

}