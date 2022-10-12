package br.com.dionataferraz.vendas.data.response

data class UserResponse(
    val name:String,
    val email:String,
    val password:String)

data class TransactionResponse(
    val description:String,
    val amount:Double,
    val type:String)