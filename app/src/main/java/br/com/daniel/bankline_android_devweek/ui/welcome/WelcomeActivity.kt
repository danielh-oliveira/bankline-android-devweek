package br.com.daniel.bankline_android_devweek.ui.welcome

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.daniel.bankline_android_devweek.databinding.ActivityWelcomeBinding
import br.com.daniel.bankline_android_devweek.domain.Correntista
import br.com.daniel.bankline_android_devweek.ui.statement.BankStatementActivity

class WelcomeActivity : AppCompatActivity() {

    //by lazy: quando alguem precisar dessa variavel executar esse trecho de codigo.
    private val binding by lazy {
        ActivityWelcomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btContinue.setOnClickListener {
            val accontHolderId = binding.editTextAccontHolderId.text.toString().toInt()

            val intent = Intent(this, BankStatementActivity::class.java).apply {
                putExtra(BankStatementActivity.EXTRA_ACCOUNT_HOLDER, Correntista(accontHolderId))
            }
            startActivity(intent)
        }
    }
}