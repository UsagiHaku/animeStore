package com.example.SingIn

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import com.example.animestore.R
import com.example.presentation.listProducts.ListProductsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var signUpButtom: Button
    private lateinit var logInButtom: Button
    private lateinit var mainButtom: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buttoms)

        signUpButtom = findViewById(R.id.signUp)

        signUpButtom.setOnClickListener {
            val intent = Intent(applicationContext, SignUpActivity::class.java)
            startActivity(intent)
        }

        logInButtom =  findViewById(R.id.logIng)

        logInButtom.setOnClickListener {
            val intent = Intent(applicationContext,LogInActivity::class.java)
            startActivity(intent)
        }

        mainButtom = findViewById(R.id.mainButtom)

        mainButtom.setOnClickListener {
            val intent =  Intent(applicationContext,ListProductsActivity::class.java )
            startActivity(intent)
        }



    }

}
