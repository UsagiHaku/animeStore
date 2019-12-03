package com.example.presentation.listProducts

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animestore.R
import com.example.data.AnimeStoreDatabase
import com.example.data.OrderItemRepository
import com.example.domain.Product
import com.example.presentation.cart.CartActivity
import com.example.presentation.productDetail.ProductDetailActivity
import com.example.toolbar.ToolbarBuilder
import java.util.concurrent.Executors


class ListProductsActivity : AppCompatActivity(), ListProductsContract.View {
    private var recyclerView: androidx.recyclerview.widget.RecyclerView? = null
    private var adapter: ListProductsAdapter? = null
    private var presenter: ListProductsContract.Presenter? = null

    private lateinit var orderItemsRepository: OrderItemRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_products)

        presenter = ListProductsPresenter(this)

        ToolbarBuilder(this)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        val orderItemDao = AnimeStoreDatabase.getDatabase(application).orderItemDao()

        orderItemsRepository =
            OrderItemRepository(orderItemDao, Executors.newSingleThreadExecutor())

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

                    startActivity(intent)
                }
            })
        recyclerView?.adapter = adapter

        presenter?.loadProducts()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId

        if (id == R.id.shopping_cart) {
            startActivity(Intent(applicationContext, CartActivity::class.java))
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showProducts(products: List<Product>) {
        adapter?.addProducts(products)
    }
}
