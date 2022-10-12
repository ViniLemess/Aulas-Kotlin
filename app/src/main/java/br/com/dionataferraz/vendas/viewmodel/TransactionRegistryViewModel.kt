package br.com.dionataferraz.vendas.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dionataferraz.vendas.usecase.TransactionUsecase
import kotlinx.coroutines.launch

class TransactionRegistryViewModel: ViewModel() {
    private val usecase by lazy {
        TransactionUsecase()
    }
    private val transaction: MutableLiveData<Transaction> = MutableLiveData()
    private val state: MutableLiveData<TransactionState> = MutableLiveData()
    val listTransaction: LiveData<Transaction> = transaction
    val viewState: LiveData<TransactionState> = state

    fun saveTransaction(type: TransactionType, amount: String, description:String) {
        viewModelScope.launch {
            if (amount.isBlank()) {
                state.value = TransactionState.EmptyTransaction
                return@launch
            } else if (validateTransaction(type)) {
                state.value = TransactionState.UnknownType
                return@launch
            }
            val newTransaction = Transaction(
                description,
                amount.trim().toDouble(),
                type
            )
            Log.i("objeto ====>", newTransaction.toString())
            usecase.saveTransaction(newTransaction)
            state.value = TransactionState.ValidTransaction
        }
    }

    private fun validateTransaction(type: TransactionType): Boolean {
        return type == TransactionType.UNKNOWN
    }
}

sealed class TransactionState() {
    object EmptyTransaction: TransactionState()
    object UnknownType: TransactionState()
    object ValidTransaction: TransactionState()
}