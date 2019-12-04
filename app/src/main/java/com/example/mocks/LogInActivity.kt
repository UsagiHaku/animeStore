package com.example.mocks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.animestore.R
import com.example.presentation.listProducts.ListProductsActivity
import com.example.presentation.paymentMethod.PaymentMethodActivity
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {

    var redirectToScreen: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        redirectToScreen = intent.getStringExtra("redirectToScreen")

        signUp_message.setOnClickListener {
            val intent = Intent(applicationContext, SignUpActivity::class.java)
            startActivity(intent)
        }

        logInButtom.setOnClickListener {
            val intent = if(redirectToScreen == "payment") {
                Intent(applicationContext, PaymentMethodActivity::class.java)
            } else {
                Intent(applicationContext, ListProductsActivity::class.java)
            }

            startActivity(intent)
        }

    }
}
