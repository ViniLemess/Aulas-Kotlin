package br.com.dionataferraz.vendas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import br.com.dionataferraz.vendas.databinding.ActivityTransactionsBinding
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.*

class TransactionsActivity : AppCompatActivity(),TransactionAdapter.Listener {

    private lateinit var binding: ActivityTransactionsBinding
    private val adapter: TransactionAdapter by lazy {
        TransactionAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val preferences = getSharedPreferences("preferences", MODE_PRIVATE)
        val sdf = SimpleDateFormat("hh:mm")

        preferences.edit().apply {
            this.putString("transactions", transactionsToJson(listOf(
                Transaction("Super Mercado",sdf.format(Date(2022,9,1,10,2)), 55.69, TransactionType.MARKET),
                Transaction("Posto De Gasosa",sdf.format(Date(2022,9,1,15,45)), 15.29, TransactionType.GAS_STATION),
                Transaction("Bar dos guris",sdf.format(Date(2022,9,1,23, 11)), 25.99, TransactionType.PUB),
            )))
        }.apply()

        val transactions = preferences.getString("transactions", null)
            ?.let { jsonToTransactions(it) }

        binding.rcList.adapter = adapter

        if (transactions != null) {
            Log.i("====> JSON IF:", transactions.toString())
            adapter.addList(transactions)
        } else Toast.makeText(
            this,
            "DEU GURU!!!",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onItemClick(text: String) {
        Toast.makeText(
            this,
            text,
            Toast.LENGTH_LONG
        ).show()
    }
}

fun transactionsToJson(transactions: List<Transaction>): String {
    val moshi = Moshi
        .Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    val type: Type = Types.newParameterizedType(List::class.java, Transaction::class.java)
    val adapter: JsonAdapter<List<Transaction>> = moshi.adapter(type)
    Log.i("====> TRANSACTIONS:", adapter.toJson(transactions))
    return adapter.toJson(transactions)
}

fun jsonToTransactions(json: String): List<Transaction> {
    val moshi = Moshi
        .Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()
    val type: Type = Types.newParameterizedType(List::class.java, Transaction::class.java)
    val adapter: JsonAdapter<List<Transaction>> = moshi.adapter(type)
    Log.i("====> JSON:", adapter.fromJson(json).toString())

    return adapter.fromJson(json) ?: listOf()
}

@JsonClass(generateAdapter = true)
data class Transaction(val description:String, val date:String, val price:Double, val type: TransactionType)

enum class TransactionType {
    MARKET, GAS_STATION, PUB
}