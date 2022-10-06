package br.com.dionataferraz.vendas.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import br.com.dionataferraz.vendas.viewmodel.TransactionType

@Entity(tableName = "transactionTable")
class TransactionEntity (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val date: String,
    val amount: Double,
    val type: TransactionType
)
