package com.example.SingIn

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.animestore.R
import com.example.presentation.listProducts.ListProductsActivity
import kotlinx.android.synthetic.main.activity_buttoms.*
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
