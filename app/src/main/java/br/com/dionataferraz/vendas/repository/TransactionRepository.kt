package br.com.dionataferraz.vendas.repository

import br.com.dionataferraz.vendas.data.remote.TransactionDataSource
import br.com.dionataferraz.vendas.toModel
import br.com.dionataferraz.vendas.toResponse
import br.com.dionataferraz.vendas.viewmodel.Transaction

class TransactionRepository {
    private val dataSource: TransactionDataSource by lazy {
        TransactionDataSource()
    }

    suspend fun saveTransaction(transaction: Transaction) {
        dataSource.registerTransaction(transaction.toResponse())
    }

    suspend fun findTransactions(): List<Transaction> {
        return dataSource.findTransactions().map { it.toModel() }
    }
}