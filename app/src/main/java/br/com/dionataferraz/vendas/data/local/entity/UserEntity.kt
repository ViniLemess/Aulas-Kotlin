package br.com.dionataferraz.vendas.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userTable")
class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name:String,
    val email:String,
    val password:String
)