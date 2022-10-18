package br.com.dionataferraz.vendas.data.remote

import android.util.Log
import br.com.dionataferraz.vendas.data.response.TransactionResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TransactionDataSource {
    private val client = RetrofitNetworkClient
        .createNetworkClient()
        .create(TransactionsApi::class.java)

    suspend fun registerTransaction(transaction: TransactionResponse) {
        withContext(Dispatchers.IO) {
            try {
                client.registerTransaction(transaction)
            } catch (exception:Exception) {
                Log.e("msg: {}, error is {}", exception.message, exception)
            }
        }
    }

    suspend fun findTransactions(): List<TransactionResponse> {
        return withContext(Dispatchers.IO) {
            try {
                client.findTransactions()
            } catch (exception:Exception) {
                Log.e("msg: {}, error is {}", exception.message, exception)
                emptyList()
            }
        }
    }

    suspend fun deleteTransaction(id:Int) {
        withContext(Dispatchers.IO) {
            try {
                client.deleteTransaction(id)
            } catch  (exception:Exception) {
                Log.e("msg: {}, error is {}", exception.message, exception)
            }
        }
    }
}