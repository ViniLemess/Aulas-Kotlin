package br.com.dionataferraz.vendas.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.dionataferraz.vendas.data.local.dao.TransactionDao
import br.com.dionataferraz.vendas.data.local.dao.UserDao
import br.com.dionataferraz.vendas.data.local.entity.TransactionEntity
import br.com.dionataferraz.vendas.data.local.entity.UserEntity

@Database(entities = [UserEntity::class, TransactionEntity::class], version = 1)
abstract class SalesDatabase : RoomDatabase() {

    abstract fun userDAO(): UserDao

    abstract fun transactionDAO(): TransactionDao

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