package br.com.daniel.bankline_android_devweek.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.daniel.bankline_android_devweek.databinding.ActivityWelcomeBinding
import br.com.daniel.bankline_android_devweek.ui.ID_ACCOUNT_HOLDER
import br.com.daniel.bankline_android_devweek.ui.statement.BankStatementActivity

class WelcomeActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityWelcomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btContinue.setOnClickListener {
            try {
                val accontHolderId = getIdAccount()
                intentWith(accontHolderId)
            } catch (e: IllegalArgumentException) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun intentWith(accountHolderId: Int) {
        val intent = Intent(this, BankStatementActivity::class.java).apply {
            putExtra(ID_ACCOUNT_HOLDER, accountHolderId)
        }
        startActivity(intent)
    }

    private fun getIdAccount(): Int {
        val accountId = binding.editTextAccontHolderId.text.toString().toInt()
        if (accountId == 0) throw IllegalArgumentException("ZERO can not be an account")
        return accountId
    }
}