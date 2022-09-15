package br.com.dionataferraz.vendas.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.dionataferraz.vendas.HomeActivity
import br.com.dionataferraz.vendas.databinding.ActivityLoginBinding
import br.com.dionataferraz.vendas.login.data.local.UserEntity
import br.com.dionataferraz.vendas.login.data.local.VendasDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    private val database: VendasDatabase by lazy {
        VendasDatabase.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        viewModel = LoginViewModel()

        val view = binding.root
        setContentView(view)

        binding.btLogin.setOnClickListener {
            viewModel.login(
                email = binding.etEmail.text.toString(),
                password = binding.etPassword.text.toString()
            )

        }

        viewModel.shouldShowHome.observe(this) { shouldOpen ->
            if (shouldOpen) {
                val intent  = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }

        viewModel.shouldShowError.observe(this) { shouldShow ->
            if (shouldShow){
                Toast.makeText(
                    this,
                    "Login Invalido",
                    Toast.LENGTH_LONG
                ).show()
            }
        }


        CoroutineScope(Dispatchers.IO).launch {
            database.DAO().insertUser(UserEntity(
                id = 3,
                name = "Kauan",
                email = "Kauanzito@outlook.com",
                password = "kauanzera123"
            ))
            val users = database.DAO().findUsers()
            Log.e("DAO", users.toString())
        }
    }
}