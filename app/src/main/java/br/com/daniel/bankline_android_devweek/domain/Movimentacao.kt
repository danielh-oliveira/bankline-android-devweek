package br.com.daniel.bankline_android_devweek.domain

import java.time.LocalDateTime

data class Movimentacao(
    val id: Int,
    val dataHora: String,
    val descricao: String,
    val valor: Double,
    val tipo: TipoMovimentacao,
    val idCorrentista: Int
)
