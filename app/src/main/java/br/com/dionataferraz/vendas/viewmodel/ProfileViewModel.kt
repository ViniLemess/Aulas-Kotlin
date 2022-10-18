package br.com.dionataferraz.vendas.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.dionataferraz.vendas.usecase.LoginUsecase
import kotlinx.coroutines.launch

class ProfileViewModel: ViewModel() {
    private val usecase: LoginUsecase by lazy {
        LoginUsecase()
    }

    fun saveUser(name:String, email:String, password:String) {
        viewModelScope.launch {
            usecase.registerUser(User(name, email, password))
        }
    }
}

data class User(val name:String, val email:String, val password:String)