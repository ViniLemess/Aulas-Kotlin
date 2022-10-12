package br.com.dionataferraz.vendas.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.dionataferraz.vendas.databinding.ActivityProfileBinding
import br.com.dionataferraz.vendas.viewmodel.ProfileViewModel

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityProfileBinding.inflate(layoutInflater).run {
            binding = this
            viewModel = ProfileViewModel()
            setContentView(root)
        }

        binding.btSave.setOnClickListener {
            viewModel.saveUser(
                binding.etName.text.toString(),
                binding.etEmail.text.toString(),
                binding.etPassword.text.toString()
            )
        }
    }
}