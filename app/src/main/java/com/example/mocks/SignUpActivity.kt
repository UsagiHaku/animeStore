package com.example.mocks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.animestore.R
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        backButton?.setOnClickListener {
            onBackPressed()
        }
    }
}
