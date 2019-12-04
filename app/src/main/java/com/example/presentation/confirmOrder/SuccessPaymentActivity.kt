package com.example.presentation.confirmOrder

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.animestore.R
import com.example.presentation.listProducts.ListProductsActivity
import com.example.toolbar.ToolbarBuilder
import com.example.utils.resetStack
import kotlinx.android.synthetic.main.success_payment_layout.*


class SuccessPaymentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.success_payment_layout)

        ToolbarBuilder(this)

        goHomeButton?.setOnClickListener {
            val intent = Intent(applicationContext, ListProductsActivity::class.java)
                .resetStack()
            startActivity(intent)
        }
    }
}
