package br.com.dionataferraz.vendas.data.remote

import br.com.dionataferraz.vendas.data.response.TransactionResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface TransactionsApi {

    @POST("api/transaction/1")
    suspend fun registerTransaction(@Body transaction: TransactionResponse)

    @GET("api/transaction/1")
    suspend fun findTransactions(): List<TransactionResponse>

    @DELETE("api/transaction/{id}")
    suspend fun deleteTransaction(@Path("id") id:Int)
}