package br.com.dionataferraz.vendas.usecase

import br.com.dionataferraz.vendas.data.remote.ErrorModel
import br.com.dionataferraz.vendas.data.remote.Result
import br.com.dionataferraz.vendas.data.response.UserResponse
import br.com.dionataferraz.vendas.repository.LoginRepository

class GetLoginUsecase {
    private val repository by lazy {
        LoginRepository()
    }

    suspend fun login(email:String, password:String): Result<UserResponse, ErrorModel> {

        return repository.login(email = email, password = password)
    }
}