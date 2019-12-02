package com.example.presentation.cart

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animestore.R
import com.example.data.AnimeStoreDatabase
import com.example.data.OrderItemRepository
import com.example.domain.OrderItem
import com.example.presentation.cart.ListCartAdapter.OnOrderItemClickListener
import kotlinx.android.synthetic.main.cart_layout.*
import java.util.concurrent.Executors

class CartActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null
    private var recyclerView: androidx.recyclerview.widget.RecyclerView? = null
    private var adapter: ListCartAdapter? = null

    private lateinit var orderItemsRepository: OrderItemRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_layout)

        toolbar = findViewById(R.id.toolbar)
        recyclerView = findViewById(R.id.recyclerView)

        setSupportActionBar(toolbar)

        recyclerView?.layoutManager = LinearLayoutManager(this)

        adapter = ListCartAdapter(this, arrayListOf(), adapterListener)

        recyclerView?.adapter = adapter

        val orderItemDao = AnimeStoreDatabase.getDatabase(application).orderItemDao()

        orderItemsRepository =
            OrderItemRepository(orderItemDao, Executors.newSingleThreadExecutor())

        orderItemsRepository.getAllOrderItems {
            adapter?.addItems(it)
        }

        buyButton.setOnClickListener {
            val orderItem = OrderItem(1, 120.0, name = "Producto 1")

            orderItemsRepository.apply {
                addOrderItem(orderItem) {
                    getAllOrderItems {
                        runOnUiThread {
                            updateCart(it)
                        }
                    }
                }
            }
        }
    }

    private fun updateCart(orderItems: List<OrderItem>) {
        adapter?.replaceItems(orderItems)
    }

    private val adapterListener = object : OnOrderItemClickListener {
        override fun onDeleteItemClick(orderItem: OrderItem) {
            orderItemsRepository.apply {
                removeOrderItem(orderItem) {
                    getAllOrderItems {
                        runOnUiThread {
                            updateCart(it)
                        }
                    }
                }
            }
        }

        override fun onOrderItemClick(orderItem: OrderItem) {

        }
    }
}
