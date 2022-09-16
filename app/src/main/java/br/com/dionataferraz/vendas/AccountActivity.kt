package br.com.dionataferraz.vendas

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.com.dionataferraz.vendas.databinding.ActivityAccountBinding
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class AccountActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.submitButton.setOnClickListener {
            val description = binding.etDescription.text.toString()
            val responsible = binding.etResponsible.text.toString()
            val balance = binding.etValue.text.toString().trim().toDouble()
            val credit = binding.rdCredit.isChecked
            val debit = binding.rdDebit.isChecked
            saveAccount(description, responsible, balance, debit, credit)
            showToast("Successfully Saved", this)
        }

        binding.backArrow.setOnClickListener {
            finish()
        }
    }

    fun saveAccount(description:String, accountResponsible: String, accountBalance: Double, debit: Boolean, credit:Boolean) {
        val preferences = getSharedPreferences("preferences", MODE_PRIVATE)
        val account = Account(description = description, accountBalance = accountBalance, accountResponsible = accountResponsible, debit = debit, credit = credit)
        preferences.edit().apply {
            this.putString("account", accountToJson(account))
        }.apply()
    }
}

fun showToast(message:String, context: Context) {
    Toast.makeText(
        context,
        message,
        Toast.LENGTH_LONG
    ).show()
}

fun accountToJson(account: Account): String {
    val moshi = Moshi
        .Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    val adapter = moshi.adapter(Account::class.java)
    Log.i("====> Account:", adapter.toJson(account))
    return adapter.toJson(account)
}

data class Account(
    val description: String,
    val accountBalance: Double,
    val accountResponsible: String,
    val debit: Boolean,
    val credit:Boolean
)