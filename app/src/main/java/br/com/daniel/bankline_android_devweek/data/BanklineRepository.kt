package br.com.daniel.bankline_android_devweek.data

import android.util.Log
import androidx.lifecycle.liveData
import br.com.daniel.bankline_android_devweek.data.remote.BanklineAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BanklineRepository {
    private val TAG = javaClass.simpleName

    private val restApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://dio-bankline-devweek-daniel.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BanklineAPI::class.java)
    }

    fun findBankStatement(accountHolderId: Int) = liveData {
        emit(State.Wait)
        try {
            emit(State.Success(data = restApi.findBankStatement(accountHolderId)))
        } catch (e: Exception) {
            Log.e(TAG, e.message, e)
            emit(State.Error(e.message))
        }
    }
}