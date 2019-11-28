package com.example.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.animestore.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.shopping_car.*
import android.text.Editable
import android.text.TextWatcher

class ShoppingCartActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shopping_car)

        setSupportActionBar(toolbar)

        Picasso.with(applicationContext)
            .load("https://images-na.ssl-images-amazon.com/images/I/81CVIiw%2BHgL._SX342_.jpg")
            .into(imageView3)

        subtotal.text = (productQuantity.text.toString().toInt() * price.text.toString().toFloat()).toString()
        totalNumber.text = subtotal.text

        productQuantity.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if(productQuantity.text.isNotEmpty()) {
                    subtotal.text = (productQuantity.text.toString().toInt() * price.text.toString().toFloat()).toString()
                    totalNumber.text = subtotal.text
                }
            }
        })
    }
}