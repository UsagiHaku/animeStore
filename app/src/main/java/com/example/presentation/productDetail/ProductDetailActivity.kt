package com.example.presentation.productDetail

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.example.SingIn.SignUpActivity
import com.example.animestore.R
import com.example.domain.Product
import com.example.presentation.ShoppingCartActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_detail.*

class ProductDetailActivity : AppCompatActivity(), ProductDetailContract.View {

    private var productName: TextView? = null
    private var productImage: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_detail)

        productName = findViewById(R.id.productName)
        productImage = findViewById(R.id.productImage)
        productName?.setOnClickListener { }

        showProductDetail(
            Product(
                name = intent.getStringExtra("PRODUCT_NAME"),
                price = intent.getFloatExtra("PRODUCT_PRICE", 0f),
                image = intent.getStringExtra("PRODUCT_IMAGE")
            )
        )

        addToCar?.setOnClickListener {
            val intent = Intent(applicationContext, ShoppingCartActivity::class.java)
            startActivity(intent)
        }

        buyNow?.setOnClickListener {
            val intent = Intent(applicationContext, ShoppingCartActivity::class.java)
            startActivity(intent)
        }

    }

    override fun showProductDetail(product: Product) {
        val name = product.name
        val image = product.image

        productName?.text = name
        price?.text = product.price.toString()
        Picasso.with(applicationContext)
            .load("https://images-na.ssl-images-amazon.com/images/I/81CVIiw%2BHgL._SX342_.jpg")
            .into(productImage)
    }
}
