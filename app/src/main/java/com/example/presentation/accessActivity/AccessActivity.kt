package com.example.presentation.accessActivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.animestore.R
import com.example.mocks.LogInActivity
import com.example.mocks.SignUpActivity
import com.example.toolbar.ToolbarBuilder
import kotlinx.android.synthetic.main.access_layout.*

class AccessActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.access_layout)

        ToolbarBuilder(this)

        logInButtom?.setOnClickListener {
            val intent = Intent(applicationContext, LogInActivity::class.java)
                .putExtra("redirectToScreen", intent.getStringExtra("redirectToScreen"))
            startActivity(intent)
        }

        signUpButton?.setOnClickListener {
            val intent = Intent(applicationContext, SignUpActivity::class.java)
                .putExtra("redirectToScreen", intent.getStringExtra("redirectToScreen"))
            startActivity(intent)
        }
    }
}
