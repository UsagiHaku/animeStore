package com.example.presentation.listProducts

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.animestore.R
import com.example.domain.Product
import com.example.presentation.productDetail.ProductDetailActivity


class ListProductsActivity : AppCompatActivity(), ListProductsContract.View {
    private var toolbar: Toolbar? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: ListProductsAdapter? = null
    private var presenter: ListProductsContract.Presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.list_products)

        presenter = ListProductsPresenter(this)

        toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView!!.layoutManager = LinearLayoutManager(this)


        adapter = ListProductsAdapter(this, ArrayList(), object : ListProductsAdapter.OnProductClickListener {
            override fun onProductClick(product: Product) {
                //Toast.makeText(getApplicationContext(), product.getName(), Toast.LENGTH_SHORT).show();
                val intent = Intent(this@ListProductsActivity, ProductDetailActivity::class.java)
                intent.putExtra("PRODUCT_NAME", product.name)
                intent.putExtra("PRODUCT_IMAGE", product.image)
                intent.putExtra("PRODUCT_PRICE", product.price)

                startActivity(intent)
            }

            override fun onAddProductClick(product: Product) {
                Toast.makeText(applicationContext, product.price.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onProductImageClick(product: Product) {
                Toast.makeText(applicationContext, product.sku, Toast.LENGTH_SHORT).show()
            }
        })
        recyclerView!!.adapter = adapter

        presenter!!.loadProducts()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.shopping_cart) {
            Toast.makeText(this@ListProductsActivity, "Action clicked", Toast.LENGTH_LONG).show()
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showProducts(products: List<Product>) {
        adapter?.addProducts(products)
    }
}
