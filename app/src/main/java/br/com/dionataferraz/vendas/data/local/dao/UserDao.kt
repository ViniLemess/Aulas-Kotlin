package br.com.dionataferraz.vendas.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.dionataferraz.vendas.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Insert
    fun insertUser(user: UserEntity)

    @Query("SELECT * from userTable")
    fun findUsers(): List<UserEntity>

    @Query("SELECT * from userTable WHERE id=:id")
    fun findUserById(id:Int): List<UserEntity>
}