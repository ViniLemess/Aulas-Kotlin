package br.com.dionataferraz.vendas.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dionataferraz.vendas.toCurrency
import br.com.dionataferraz.vendas.usecase.TransactionUsecase
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel(){
    private val transactionUsecase by lazy {
        TransactionUsecase()
    }

    private val balance: MutableLiveData<String> = MutableLiveData()
    val showBill: LiveData<String> = balance

    fun getTotalBill() {
        viewModelScope.launch {
            balance.value = transactionUsecase.getTotalBill().toCurrency()
        }
    }
}