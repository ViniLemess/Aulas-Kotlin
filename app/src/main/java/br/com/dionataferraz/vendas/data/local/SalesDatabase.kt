package br.com.dionataferraz.vendas.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.dionataferraz.vendas.data.local.dao.UserDao
import br.com.dionataferraz.vendas.data.local.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class SalesDatabase : RoomDatabase() {

    abstract fun userDAO(): UserDao

    companion object {
        fun getInstance(context:Context) : SalesDatabase {
            return Room.databaseBuilder(
                context,
                SalesDatabase::class.java,
                "sales.db"
            ).build()
        }
    }
}