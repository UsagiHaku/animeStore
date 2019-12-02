package com.example.presentation.productDetail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.animestore.R
import com.example.data.AnimeStoreDatabase
import com.example.data.OrderItemRepository
import com.example.domain.OrderItem
import com.example.domain.Product
import com.example.presentation.ShoppingCartActivity
import com.example.presentation.cart.CartActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_detail.*
import java.util.concurrent.Executors

class ProductDetailActivity : AppCompatActivity(), ProductDetailContract.View {

    private var productName: TextView? = null
    private var productImage: ImageView? = null

    private lateinit var product: Product

    private lateinit var orderItemsRepository: OrderItemRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_detail)

        productName = findViewById(R.id.productName)
        productImage = findViewById(R.id.productImage)
        productName?.setOnClickListener { }

        product = Product(
            id = intent.getIntExtra("PRODUCT_ID", 0),
            name = intent.getStringExtra("PRODUCT_NAME"),
            price = intent.getDoubleExtra("PRODUCT_PRICE", 0.0),
            image = intent.getStringExtra("PRODUCT_IMAGE")
        )

        showProductDetail(product)

        val orderItemDao = AnimeStoreDatabase.getDatabase(application).orderItemDao()

        orderItemsRepository =
            OrderItemRepository(orderItemDao, Executors.newSingleThreadExecutor())

        addToCar?.setOnClickListener {
            val orderItem = OrderItem(product.id, product.price, name = product.name)
            orderItemsRepository.apply {
                addOrderItem(orderItem) {
                    val intent = Intent(applicationContext, CartActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        buyNow?.setOnClickListener {
            val orderItem = OrderItem(product.id, product.price, name = product.name)
            orderItemsRepository.apply {
                addOrderItem(orderItem) {
                    val intent = Intent(applicationContext, CartActivity::class.java)
                    startActivity(intent)
                }
            }
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
