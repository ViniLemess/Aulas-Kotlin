package br.com.dionataferraz.vendas.repository

import br.com.dionataferraz.vendas.data.local.TransactionDataSource
import br.com.dionataferraz.vendas.viewmodel.Transaction

class TransactionRepository {
    private val dataSource: TransactionDataSource by lazy {
        TransactionDataSource()
    }

    suspend fun saveTransaction(transaction: Transaction) {
        dataSource.saveTransaction(transaction)
    }

    suspend fun findTransactions(): List<Transaction> {
        return dataSource.findTransactions()
    }

    suspend fun findBalance(): Double {
        return dataSource.findBalance()
    }
}