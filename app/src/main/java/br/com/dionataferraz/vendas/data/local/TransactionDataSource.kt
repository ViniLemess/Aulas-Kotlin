package br.com.dionataferraz.vendas.data.local

import android.util.Log
import br.com.dionataferraz.vendas.App
import br.com.dionataferraz.vendas.data.local.entity.TransactionEntity
import br.com.dionataferraz.vendas.viewmodel.Transaction
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TransactionDataSource {

    private val database: SalesDatabase by lazy {
        SalesDatabase.getInstance(App.context)
    }

    suspend fun saveTransaction(transaction: Transaction) {
        withContext(Dispatchers.IO) {
            database.transactionDAO().insertTransaction(transaction.toEntity())
        }
    }

    suspend fun findTransactions(): List<Transaction> {
        return withContext(Dispatchers.IO) {
            database.transactionDAO().findTransactions().map {
                Log.e("Entities ===>", it.id.toString())
                it.toModel()
            }
        }
    }

    suspend fun findBalance(): Double {
        return withContext(Dispatchers.IO) {
            database.transactionDAO().findBalance()
        }
    }

    private fun TransactionEntity.toModel(): Transaction {
        return Transaction(
            date = date,
            amount = amount,
            type = type
        )
    }

    private fun Transaction.toEntity(): TransactionEntity {
        return TransactionEntity(
            date = date,
            amount = amount,
            type = type
        )
    }
}