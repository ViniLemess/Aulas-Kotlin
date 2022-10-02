package br.com.dionataferraz.vendas.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dionataferraz.vendas.usecase.TransactionUsecase
import kotlinx.coroutines.launch
import java.util.*

class TransactionRegistryViewModel: ViewModel() {
    private val usecase by lazy {
        TransactionUsecase()
    }
    private val transaction: MutableLiveData<Transaction> = MutableLiveData()
    private val error: MutableLiveData<Boolean> = MutableLiveData()
    val listTransaction: LiveData<Transaction> = transaction
    val isError: LiveData<Boolean> = error

    fun saveTransaction(isDeposit: Boolean, amount: String) {
        viewModelScope.launch {
            if (amount.isBlank()) return@launch
            val newTransaction = Transaction(
                Date().toString(),
                amount.trim().toDouble(),
                if (isDeposit) {
                    TransactionType.DEPOSIT
                } else {
                    TransactionType.WITHDRAW
                }
            )
            Log.i("objeto ====>", newTransaction.toString())
            if (newTransaction.type == TransactionType.DEPOSIT) {
                usecase.saveTransaction(newTransaction)
                error.value = false
            } else if (validateTransaction(usecase.findBalance(), newTransaction.amount)) {
                usecase.saveTransaction(newTransaction)
                error.value = false
            } else error.value = true
        }
    }

    private fun validateTransaction(currentBalance: Double, amount:Double): Boolean {
        return amount <= currentBalance
    }
}