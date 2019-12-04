package com.example.mocks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.animestore.R
import com.example.data.AnimeStoreDatabase
import com.example.data.SessionManager
import com.example.data.UserRepository
import com.example.domain.User
import com.example.presentation.listProducts.ListProductsActivity
import com.example.presentation.paymentMethod.PaymentMethodActivity
import com.example.utils.resetStack
import kotlinx.android.synthetic.main.activity_sign_up.*
import java.util.concurrent.Executors

class SignUpActivity : AppCompatActivity() {

    private lateinit var userRepository: UserRepository
    private lateinit var sessionManager: SessionManager

    private var redirectToScreen: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        sessionManager = SessionManager(this)

        redirectToScreen = intent.getStringExtra("redirectToScreen")

        userRepository = UserRepository(
            AnimeStoreDatabase.getDatabase(application).userDao(),
            Executors.newSingleThreadExecutor()
        )

        button?.setOnClickListener {
            emailField.isErrorEnabled = false

            val user = User(
                email = emailField.editText?.text.toString(),
                username = nicknameField.editText?.text.toString(),
                password = passwordField.editText?.text.toString()
            )

            val onSuccessCreatedUser = {
                sessionManager.saveSession()
                startActivity(buildIntent())
            }

            val onErrorCreatedUser = {
                runOnUiThread {
                    emailField.error = "El email ya se encuentra registrado"
                }
            }

            userRepository.addUser(user, onSuccessCreatedUser, onErrorCreatedUser)
        }

        backButton?.setOnClickListener {
            onBackPressed()
        }
    }

    private fun buildIntent(): Intent {
        return if (redirectToScreen == "payment") {
            Intent(applicationContext, PaymentMethodActivity::class.java)
                .resetStack()
        } else {
            Intent(applicationContext, ListProductsActivity::class.java)
                .resetStack()
        }
    }
}
