package com.example.mocks

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.animestore.R
import com.example.presentation.listProducts.ListProductsActivity
import kotlinx.android.synthetic.main.activity_log_in.*

class LogInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        signUp_message.setOnClickListener {
            val intent = Intent(applicationContext, SignUpActivity::class.java)
            startActivity(intent)
        }

        logInButtom.setOnClickListener {
            val intent = Intent(applicationContext, ListProductsActivity::class.java)
            startActivity(intent)
        }

    }
}
