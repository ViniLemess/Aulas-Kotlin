package br.com.dionataferraz.vendas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.dionataferraz.vendas.databinding.ActivityTransactionRegistryBinding
import br.com.dionataferraz.vendas.viewmodel.TransactionRegistryViewModel
import br.com.dionataferraz.vendas.viewmodel.TransactionState

class TransactionRegistry : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionRegistryBinding
    private lateinit var viewModel: TransactionRegistryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTransactionRegistryBinding.inflate(layoutInflater)
        viewModel = TransactionRegistryViewModel()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.submitButton.setOnClickListener {
            viewModel.saveTransaction(
                binding.rdDeposit.isChecked,
                binding.etAmount.text.toString()
            )

        }

        viewModel.viewState.observe(this) { state ->
            when(state) {
                TransactionState.ValidTransaction -> showToast(getString(R.string.successfully_registered_transaction_msg))
                TransactionState.InsufficientBalance -> showToast(getString(R.string.insufficient_balance_msg))
                TransactionState.EmptyTransaction -> showToast(getString(R.string.empty_transaction_msg))
            }
        }

        binding.backArrow.setOnClickListener {
            finish()
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(
            this,
            msg,
            Toast.LENGTH_SHORT
        ).show()
    }
}