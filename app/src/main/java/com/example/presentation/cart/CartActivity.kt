package com.example.presentation.cart

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animestore.R
import com.example.data.AnimeStoreDatabase
import com.example.data.OrderItemRepository
import com.example.domain.OrderItem
import com.example.presentation.cart.ListCartAdapter.OnOrderItemClickListener
import com.example.toolbar.ToolbarBuilder
import kotlinx.android.synthetic.main.cart_layout.*
import java.util.concurrent.Executors

class CartActivity : AppCompatActivity() {

    private var recyclerView: androidx.recyclerview.widget.RecyclerView? = null
    private var adapter: ListCartAdapter? = null

    private lateinit var orderItemsRepository: OrderItemRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_layout)

        recyclerView = findViewById(R.id.recyclerView)

        ToolbarBuilder(this)

        recyclerView?.layoutManager = LinearLayoutManager(this)

        adapter = ListCartAdapter(this, arrayListOf(), adapterListener)

        recyclerView?.adapter = adapter

        val orderItemDao = AnimeStoreDatabase.getDatabase(application).orderItemDao()

        orderItemsRepository =
            OrderItemRepository(orderItemDao, Executors.newSingleThreadExecutor())

        orderItemsRepository.getAllOrderItems {
            updateCartState(it)
        }

        buyButton.setOnClickListener {
            val orderItem = OrderItem(1, 120.0, name = "Producto 1")

            orderItemsRepository.apply {
                addOrderItem(orderItem) {
                    getAllOrderItems {
                        runOnUiThread {
                            updateCartState(it)
                        }
                    }
                }
            }
        }
    }

    private fun updateCartState(orderItems: List<OrderItem>) {
        if(orderItems.isEmpty()) {
            cartContent.visibility = View.GONE
            cartEmpty.visibility = View.VISIBLE
        } else {
            cartContent.visibility = View.VISIBLE
            cartEmpty.visibility = View.GONE
            adapter?.replaceItems(orderItems)
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
                            updateCartState(it)
                        }
                    }
                }
            }
        }

        override fun onOrderItemClick(orderItem: OrderItem) {

        }
    }
}
