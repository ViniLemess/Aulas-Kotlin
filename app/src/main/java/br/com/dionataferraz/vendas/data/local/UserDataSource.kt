package br.com.dionataferraz.vendas.data.local

import br.com.dionataferraz.vendas.App
import br.com.dionataferraz.vendas.toEntity
import br.com.dionataferraz.vendas.toModel
import br.com.dionataferraz.vendas.viewmodel.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserDataSource {
    private val database: SalesDatabase by lazy {
        SalesDatabase.getInstance(App.context)
    }

    suspend fun saveUser(user: User) {
        withContext(Dispatchers.IO) {
            database.userDAO().insertUser(user.toEntity())
        }
    }

    suspend fun getUsers(): List<User> {
        return withContext(Dispatchers.IO) {
            database.userDAO()
                .findUsers()
                .map { it.toModel() }
        }
    }
}