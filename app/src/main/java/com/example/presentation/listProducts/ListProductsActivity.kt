package com.example.presentation.listProducts

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animestore.R
import com.example.data.SessionManager
import com.example.domain.Product
import com.example.mocks.LogInActivity
import com.example.presentation.cart.CartActivity
import com.example.presentation.packageDetail.ProductDetailActivity
import com.example.toolbar.ToolbarBuilder
import com.example.utils.resetStack


class ListProductsActivity : AppCompatActivity(), ListProductsContract.View {
    private var recyclerView: RecyclerView? = null
    private var adapter: ListProductsAdapter? = null
    private var presenter: ListProductsContract.Presenter? = null

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_products)

        presenter = ListProductsPresenter(this)

        sessionManager = SessionManager(baseContext)

        ToolbarBuilder(this)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        adapter = ListProductsAdapter(
            this,
            ArrayList(),
            object : ListProductsAdapter.OnProductClickListener {
                override fun onProductClick(product: Product) {
                    val intent =
                        Intent(this@ListProductsActivity, ProductDetailActivity::class.java)
                    intent.putExtra("PRODUCT_ID", product.id)
                    intent.putExtra("PRODUCT_NAME", product.name)
                    intent.putExtra("PRODUCT_IMAGE", product.image)
                    intent.putExtra("PRODUCT_PRICE", product.price)
                    intent.putExtra("PRODUCT_DESCRIPTION", product.description)

                    startActivity(intent)
                }
            })
        recyclerView?.adapter = adapter

        presenter?.loadProducts()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        // Inflate the menu; this adds items to the action bar if it is present.
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

    override fun showProducts(products: List<Product>) {
        adapter?.addProducts(products)
    }
}
