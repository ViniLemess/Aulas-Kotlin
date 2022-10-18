package br.com.dionataferraz.vendas.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.dionataferraz.vendas.R
import br.com.dionataferraz.vendas.databinding.ActivityTransactionRegistryBinding
import br.com.dionataferraz.vendas.viewmodel.TransactionRegistryViewModel
import br.com.dionataferraz.vendas.viewmodel.TransactionState
import br.com.dionataferraz.vendas.viewmodel.TransactionType

class TransactionRegistryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTransactionRegistryBinding
    private lateinit var viewModel: TransactionRegistryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityTransactionRegistryBinding.inflate(layoutInflater)
        viewModel = TransactionRegistryViewModel()
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.submitButton.setOnClickListener {
            viewModel.saveTransaction(
                getCheckedType(),
                binding.etAmount.text.toString(),
                binding.etDescription.text.toString()
            )
        }

        viewModel.viewState.observe(this) { state ->
            when(state) {
                TransactionState.ValidTransaction -> showToast(getString(R.string.successfully_registered_transaction_msg))
                TransactionState.UnknownType -> showToast(getString(R.string.unknown_type_msg))
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

    private fun getCheckedType(): TransactionType {
        if (binding.rdGasStation.isChecked) {
            return TransactionType.GAS_STATION
        } else if (binding.rdMarket.isChecked) {
            return TransactionType.MARKET
        } else if (binding.rdPub.isChecked) {
            return TransactionType.PUB
        }
        return TransactionType.UNKNOWN
    }
}