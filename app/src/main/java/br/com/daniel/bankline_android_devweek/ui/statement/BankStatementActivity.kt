package br.com.daniel.bankline_android_devweek.ui.statement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.daniel.bankline_android_devweek.data.State
import br.com.daniel.bankline_android_devweek.databinding.ActivityBankStatementBinding
import br.com.daniel.bankline_android_devweek.domain.Correntista
import br.com.daniel.bankline_android_devweek.domain.Movimentacao
import br.com.daniel.bankline_android_devweek.domain.TipoMovimentacao
import com.google.android.material.snackbar.Snackbar
import java.lang.IllegalArgumentException

class BankStatementActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_ACCOUNT_HOLDER = "br.com.daniel.bankline_android_devweek.ui.satement.EXTRA_ACCOUNT_HOLDER"
    }

    private val binding by lazy {
        ActivityBankStatementBinding.inflate(layoutInflater)
    }

    private val accontHolder by lazy {
        intent.getParcelableExtra<Correntista>(EXTRA_ACCOUNT_HOLDER) ?: throw IllegalArgumentException()
    }

    private val viewModel by viewModels<BankStatementViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.rvBankStatement.layoutManager = LinearLayoutManager(this)

        binding.srlBankStatement.setOnRefreshListener { findBankStatement() }

        findBankStatement()
    }

    private fun findBankStatement() {
        viewModel.findBankStatement(accontHolder.id).observe(this){ state ->
            when(state) {
                is State.Success -> {
                    binding.rvBankStatement.adapter = state.data?.let { BankStatementAdapter(it) }
                    binding.srlBankStatement.isRefreshing = false
                }
                is State.Error -> {
                    state.message?.let { Snackbar.make(binding.rvBankStatement, it, Snackbar.LENGTH_LONG).show() }
                    binding.srlBankStatement.isRefreshing = false
                }
                State.Wait -> {
                    binding.srlBankStatement.isRefreshing = true
                }
            }
        }
    }
}