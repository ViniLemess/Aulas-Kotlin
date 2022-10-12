package br.com.dionataferraz.vendas.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dionataferraz.vendas.usecase.TransactionUsecase
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TransactionsViewModel: ViewModel() {
    private val transactionUsecase by lazy {
        TransactionUsecase()
    }

    private val transactions: MutableLiveData<List<Transaction>> = MutableLiveData()
    val listTransactions: LiveData<List<Transaction>> = transactions

    fun findTransactions() {
        viewModelScope.launch {
            val list = transactionUsecase.findTransactions()
            if (list.isNullOrEmpty()){
                Log.e("TransactionsViewModel", "vazio")
            } else {
                transactions.value = list
                list.forEach {
                    Log.e("TransactionList ===>", it.description)
                }
            }
        }
    }
}

data class Transaction(
    val description: String,
    val amount: Double,
    val type: TransactionType
)

enum class TransactionType() {
    MARKET, PUB, GAS_STATION, UNKNOWN
}