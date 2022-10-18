package br.com.dionataferraz.vendas.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.com.dionataferraz.vendas.databinding.ActivityTransactionsBinding
import br.com.dionataferraz.vendas.viewmodel.TransactionAdapter
import br.com.dionataferraz.vendas.viewmodel.TransactionsViewModel

class TransactionsActivity : AppCompatActivity(), TransactionAdapter.Listener {
    private lateinit var binding: ActivityTransactionsBinding
    private val adapter: TransactionAdapter by lazy {
        TransactionAdapter(this)
    }
    private lateinit var viewModel: TransactionsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = TransactionsViewModel()

        Log.e("====> Tela listagem", "passei aqui")

        viewModel.listTransactions.observe(this) {
                binding.rcList.adapter = adapter
                if (it != null) {
                    adapter.addNewList(it)
                }
                Log.e("====> objeto buscado", it.toString())
        }

        binding.plusButton.setOnClickListener {
            val intent  = Intent(this, TransactionRegistryActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onItemClick(text: String) {
        Toast.makeText(
            this,
            text,
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onStart() {
        super.onStart()
        viewModel.findTransactions()
    }
}

