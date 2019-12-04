package com.example.presentation.packageDetail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.animestore.R
import com.example.data.AnimeStoreDatabase
import com.example.data.OrderItemRepository
import com.example.data.SessionManager
import com.example.domain.OrderItem
import com.example.domain.Product
import com.example.mocks.LogInActivity
import com.example.presentation.cart.CartActivity
import com.example.presentation.listProducts.ListProductsActivity
import com.example.presentation.seriesList.SeriesListActivity
import com.example.toolbar.ToolbarBuilder
import com.example.utils.resetStack
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.product_detail.*
import java.util.concurrent.Executors

class ProductDetailActivity : AppCompatActivity(), ProductDetailContract.View {

    private var productName: TextView? = null
    private var productImage: ImageView? = null
    private var productDescription: TextView? = null

    private lateinit var product: Product

    private lateinit var sessionManager: SessionManager
    private lateinit var orderItemsRepository: OrderItemRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_detail)

        ToolbarBuilder(this)

        sessionManager = SessionManager(this)

        productName = findViewById(R.id.productName)
        productImage = findViewById(R.id.serieImage)
        productDescription = findViewById(R.id.descriptionText)

        product = Product(
            id = intent.getIntExtra("PRODUCT_ID", 0),
            name = intent.getStringExtra("PRODUCT_NAME"),
            price = intent.getDoubleExtra("PRODUCT_PRICE", 0.0),
            image = intent.getStringExtra("PRODUCT_IMAGE"),
            description = intent.getStringExtra("PRODUCT_DESCRIPTION")
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
            val orderItem = OrderItem(
                product.id,
                product.price,
                name = product.name,
                image = product.image
            )
            orderItemsRepository.apply {
                addOrderItem(orderItem) {
                    val intent = Intent(applicationContext, CartActivity::class.java)
                    startActivity(intent)
                }
            }
        }

        seriesListButton?.setOnClickListener {
            val intent = Intent(applicationContext, SeriesListActivity::class.java)
                .putExtra("packageId", product.id)
            startActivity(intent)
        }

    }

    override fun showProductDetail(product: Product) {
        val name = product.name
        val image = product.image

        productName?.text = name
        price?.text = product.price.toString()
        descriptionText?.text = product.description

        Picasso.with(applicationContext)
            .load(image)
            .into(productImage)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        if(sessionManager.isLogged()) {
            menuInflater.inflate(R.menu.menu_with_logout, menu)
        } else {
            menuInflater.inflate(R.menu.menu_main, menu)
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.shopping_cart) {
            startActivity(Intent(applicationContext, CartActivity::class.java))
            return true
        }

        if(id == R.id.logout) {
            sessionManager.logout()

            val intent = Intent(applicationContext, ListProductsActivity::class.java)
                .resetStack()
            startActivity(intent)

            return true
        }

        if(id == R.id.login) {
            val intent = Intent(applicationContext, LogInActivity::class.java)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
}