package com.example.presentation.paymentMethod

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.animestore.R
import com.example.presentation.confirmOrder.ConfirmOrderActivity
import com.example.presentation.listProducts.ListProductsActivity
import com.example.toolbar.ToolbarBuilder
import com.example.utils.resetStack
import kotlinx.android.synthetic.main.payment_layout.*

class PaymentMethodActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment_layout)

        ToolbarBuilder(this, false)

        finishProcess?.setOnClickListener {
            startActivity(Intent(applicationContext, ConfirmOrderActivity::class.java))
        }

        cancelProcess?.setOnClickListener {
            val intent = Intent(applicationContext, ListProductsActivity::class.java)
                .resetStack()

            startActivity(intent)
        }
    }
}
