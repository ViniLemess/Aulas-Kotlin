package br.com.dionataferraz.vendas.login.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {

    @Insert
    fun insertUser(user:UserEntity)

    @Query("SELECT * from userTable")
    fun findUsers(): List<UserEntity>

    @Query("SELECT * from userTable WHERE id=:id")
    fun findUserById(id:Int): List<UserEntity>
}