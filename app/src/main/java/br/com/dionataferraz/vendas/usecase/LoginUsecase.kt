package br.com.dionataferraz.vendas.usecase

import br.com.dionataferraz.vendas.data.remote.ErrorModel
import br.com.dionataferraz.vendas.data.remote.Result
import br.com.dionataferraz.vendas.data.response.UserResponse
import br.com.dionataferraz.vendas.repository.LoginRepository
import br.com.dionataferraz.vendas.toModel
import br.com.dionataferraz.vendas.viewmodel.User

class LoginUsecase {
    private val repository by lazy {
        LoginRepository()
    }

    suspend fun login(email:String, password:String): Result<UserResponse, ErrorModel> {
        val user = repository.login(email = email, password = password)
        user.get().also { userResponse ->
            if (userResponse != null) {
                saveUserLocally(userResponse.toModel())
            }
        }
        return user
    }

    suspend fun registerUser(user:User) {
        repository.registerUser(user)
    }

    suspend fun saveUserLocally(user: User) {
        repository.saveUserLocally(user)
    }

    suspend fun getLocalUsers(): List<User> {
        return repository.getUsers()
    }

}