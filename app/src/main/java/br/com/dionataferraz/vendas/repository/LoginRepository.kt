package br.com.dionataferraz.vendas.repository

import br.com.dionataferraz.vendas.data.remote.ErrorModel
import br.com.dionataferraz.vendas.data.remote.LoginDataSource
import br.com.dionataferraz.vendas.data.remote.Result
import br.com.dionataferraz.vendas.data.response.UserResponse

class LoginRepository {

    private val remoteDataSource by lazy {
        LoginDataSource()
    }

    suspend fun login(email:String, password:String): Result<UserResponse, ErrorModel> {

        return remoteDataSource.login(email = email, password = password)
    }
}