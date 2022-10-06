package br.com.dionataferraz.vendas.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dionataferraz.vendas.usecase.GetLoginUsecase
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val getLoginUsecase by lazy {
        GetLoginUsecase()
    }

    private val error: MutableLiveData<Boolean> = MutableLiveData(false)
    private val home: MutableLiveData<Boolean> = MutableLiveData(false)
    val shouldShowError: LiveData<Boolean> = error
    val shouldShowHome: LiveData<Boolean> = home

    fun login(email: String?, password: String?) {
        viewModelScope.launch {
            if (email != null && password != null) {
                val user = getLoginUsecase.login(email = email, password = password)
                Log.d("Login: ",user.get().toString())
                if (user.get() != null) {
                    home.value = true
                } else {
                    error.value = true
                }
            } else {
                error.value = true
            }
        }
    }
}