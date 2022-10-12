package br.com.dionataferraz.vendas.repository

import br.com.dionataferraz.vendas.data.local.UserDataSource
import br.com.dionataferraz.vendas.data.remote.ErrorModel
import br.com.dionataferraz.vendas.data.remote.LoginDataSource
import br.com.dionataferraz.vendas.data.remote.Result
import br.com.dionataferraz.vendas.data.response.UserResponse
import br.com.dionataferraz.vendas.toResponse
import br.com.dionataferraz.vendas.viewmodel.User

class LoginRepository {

    private val remoteDataSource by lazy {
        LoginDataSource()
    }

    private val localDataSource by lazy {
        UserDataSource()
    }

    suspend fun login(email:String, password:String): Result<UserResponse, ErrorModel> {
        return remoteDataSource.login(email = email, password = password)
    }

    suspend fun registerUser(user:User) {
        remoteDataSource.registerUser(user.toResponse())
    }

    suspend fun saveUserLocally(user: User) {
        localDataSource.saveUser(user)
    }

    suspend fun getUsers(): List<User> {
        return localDataSource.getUsers()
    }
}