package br.com.dionataferraz.vendas.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import br.com.dionataferraz.vendas.data.local.entity.TransactionEntity

@Dao
interface TransactionDao {

    @Insert
    fun insertTransaction(transaction: TransactionEntity)

    @Query("SELECT * from transactionTable GROUP BY id, date, amount, type ORDER BY id desc")
    fun findTransactions(): List<TransactionEntity>

    @Query("SELECT SUM(t.amount) AS balance FROM transactionTable t")
    fun findBalance(): Double
}