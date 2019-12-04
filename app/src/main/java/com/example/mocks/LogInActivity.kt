package com.example.mocks

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.animestore.R
import com.example.data.AnimeStoreDatabase
import com.example.data.SessionManager
import com.example.data.UserRepository
import com.example.presentation.listProducts.ListProductsActivity
import com.example.presentation.paymentMethod.PaymentMethodActivity
import kotlinx.android.synthetic.main.activity_log_in.*
import java.util.concurrent.Executors

class LogInActivity : AppCompatActivity() {

    var redirectToScreen: String? = null

    private lateinit var sessionManager: SessionManager
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        sessionManager = SessionManager(this)

        redirectToScreen = intent.getStringExtra("redirectToScreen")

        userRepository = UserRepository(
            AnimeStoreDatabase.getDatabase(application).userDao(),
            Executors.newSingleThreadExecutor()
        )

        signUp_message.setOnClickListener {
            val intent = Intent(applicationContext, SignUpActivity::class.java)
                .putExtra("redirectToScreen", "payment")

            startActivity(intent)
        }

        logInButtom.setOnClickListener {
            val email = emailField?.editText?.text.toString()
            val password = passwordField.editText?.text.toString()

            userRepository.validateCredentials(email, password) { existUser ->
                if(existUser) {
                    sessionManager.saveSession()
                    val intent = buildIntent()
                    startActivity(intent)
                } else {
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Credenciales invalidas", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

    private fun buildIntent(): Intent {
        return if (redirectToScreen == "payment") {
            Intent(applicationContext, PaymentMethodActivity::class.java)
        } else {
            Intent(applicationContext, ListProductsActivity::class.java)
        }
    }

}
