package br.com.dionataferraz.vendas.usecase

import br.com.dionataferraz.vendas.repository.TransactionRepository
import br.com.dionataferraz.vendas.viewmodel.Transaction


class TransactionUsecase {
    private val repository: TransactionRepository by lazy {
        TransactionRepository()
    }

    suspend fun saveTransaction(transaction: Transaction) {
        repository.saveTransaction(transaction)
    }

    suspend fun findTransactions(): List<Transaction> {
        return repository.findTransactions()
    }

    suspend fun getTotalBill(): Double {
        return repository.findTransactions().sumOf { it.amount }
    }
}