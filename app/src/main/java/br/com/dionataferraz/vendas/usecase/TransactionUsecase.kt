package br.com.dionataferraz.vendas.usecase

import android.util.Log
import br.com.dionataferraz.vendas.repository.TransactionRepository
import br.com.dionataferraz.vendas.viewmodel.Transaction
import br.com.dionataferraz.vendas.viewmodel.TransactionType


class TransactionUsecase {
    private val repository: TransactionRepository by lazy {
        TransactionRepository()
    }

    suspend fun saveTransaction(transaction: Transaction) {
        if (transaction.type == TransactionType.WITHDRAW) {
            val price = transaction.amount * -1
            Log.e("withdraw price ====>", repository.findBalance().toString())
            repository.saveTransaction(Transaction(
                transaction.date,
                price,
                transaction.type
            ))
            return
        }
        repository.saveTransaction(transaction)
    }

    suspend fun findTransactions(): List<Transaction> {
        return repository.findTransactions()
    }

    suspend fun findBalance(): Double {
        return repository.findBalance()
    }
}