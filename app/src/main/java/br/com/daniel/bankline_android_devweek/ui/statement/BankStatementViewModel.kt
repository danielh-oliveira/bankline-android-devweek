package br.com.daniel.bankline_android_devweek.ui.statement

import androidx.lifecycle.ViewModel
import br.com.daniel.bankline_android_devweek.data.BanklineRepository

class BankStatementViewModel : ViewModel() {
    fun findBankStatement(accontHolderId: Int) =
        BanklineRepository.findBankStatement(accontHolderId)
}