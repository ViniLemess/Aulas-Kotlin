package br.com.dionataferraz.vendas.login.usecase

import br.com.dionataferraz.vendas.login.data.remote.ErrorModel
import br.com.dionataferraz.vendas.login.data.remote.Result
import br.com.dionataferraz.vendas.login.data.response.UserResponse
import br.com.dionataferraz.vendas.repository.LoginRepository

class GetLoginUsecase {
    private val repository by lazy {
        LoginRepository()
    }

    suspend fun login(email:String, password:String): Result<UserResponse, ErrorModel> {

        return repository.login(email = email, password = password)
    }
}