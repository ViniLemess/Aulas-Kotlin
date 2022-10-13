package br.com.dionataferraz.vendas

import br.com.dionataferraz.vendas.data.local.entity.UserEntity
import br.com.dionataferraz.vendas.data.response.TransactionResponse
import br.com.dionataferraz.vendas.data.response.UserResponse
import br.com.dionataferraz.vendas.viewmodel.Transaction
import br.com.dionataferraz.vendas.viewmodel.TransactionType
import br.com.dionataferraz.vendas.viewmodel.User
import java.text.DecimalFormat

fun Double.toCurrency(): String {
    if (this == 0.0) {
        return "R$ 0,00"
    }
    val format = DecimalFormat("R$ #,###.##")
    format.isDecimalSeparatorAlwaysShown = false
    return format.format(this).toString()
}

fun TransactionResponse.toModel(): Transaction {
    return Transaction(
        description = description,
        amount = value,
        type = TransactionType.valueOf(transactionType)
    )
}

fun Transaction.toResponse(): TransactionResponse {
    return TransactionResponse(
        description = description,
        value = amount,
        transactionType = type.toString()
    )
}

fun User.toResponse(): UserResponse {
    return UserResponse(
        name = name,
        email = email,
        password = password
    )
}

fun UserResponse.toModel(): User {
    return User(
        name = name,
        email = email,
        password = password
    )
}

fun User.toEntity(): UserEntity {
    return UserEntity(
        name = name,
        email = email,
        password = password
    )
}

fun UserEntity.toModel(): User {
    return User(
        name = name,
        email = email,
        password = password
    )
}