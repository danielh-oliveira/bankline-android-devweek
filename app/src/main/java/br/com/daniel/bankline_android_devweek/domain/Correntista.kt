package br.com.daniel.bankline_android_devweek.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Correntista(
    val id: Int
) : Parcelable
