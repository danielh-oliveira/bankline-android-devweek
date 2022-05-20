package br.com.daniel.bankline_android_devweek.ui.statement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.daniel.bankline_android_devweek.data.State
import br.com.daniel.bankline_android_devweek.databinding.ActivityBankStatementBinding
import br.com.daniel.bankline_android_devweek.domain.Correntista
import br.com.daniel.bankline_android_devweek.ui.ID_ACCOUNT_HOLDER
import com.google.android.material.snackbar.Snackbar

class BankStatementActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityBankStatementBinding.inflate(layoutInflater)
    }

    private val accountHolder: Correntista by lazy {
        val accountHolderId = intent.getIntExtra(ID_ACCOUNT_HOLDER, 0)
        Correntista(accountHolderId)
    }

    private val viewModel by viewModels<BankStatementViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setLayoutManager()

        checkIfAccountExists()
        binding.srlBankStatement.setOnRefreshListener { findBankStatement() }
        findBankStatement()
    }

    private fun checkIfAccountExists() {
        if (accountHolder.id == 0) finish().also {
            Toast.makeText(this, "This account does not exist", Toast.LENGTH_LONG).show()
        }
    }

    private fun setLayoutManager() {
        binding.rvBankStatement.layoutManager = LinearLayoutManager(this)
    }

    private fun findBankStatement() {
        viewModel.findBankStatement(accountHolder.id).observe(this) { state ->
            when (state) {
                is State.Success -> {
                    binding.rvBankStatement.adapter = state.data?.let { BankStatementAdapter(it) }
                    binding.srlBankStatement.isRefreshing = false
                }
                is State.Error -> {
                    state.message?.let {
                        Snackbar.make(
                            binding.rvBankStatement,
                            it,
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    binding.srlBankStatement.isRefreshing = false
                }
                State.Wait -> {
                    binding.srlBankStatement.isRefreshing = true
                }
            }
        }
    }
}