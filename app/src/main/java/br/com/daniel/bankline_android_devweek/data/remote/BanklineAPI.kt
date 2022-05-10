package br.com.daniel.bankline_android_devweek.data.remote

import br.com.daniel.bankline_android_devweek.domain.Movimentacao
import retrofit2.http.GET
import retrofit2.http.Path

interface BanklineAPI {

    @GET("movimentacoes/{id}")
    suspend fun findBankStatement(@Path("id") accountHolderId: Int) : List<Movimentacao>
}