package com.example.presentation.cart

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animestore.R
import com.example.data.AnimeStoreDatabase
import com.example.data.OrderItemRepository
import com.example.data.SessionManager
import com.example.domain.OrderItem
import com.example.domain.total
import com.example.mocks.LogInActivity
import com.example.presentation.accessActivity.AccessActivity
import com.example.presentation.cart.ListCartAdapter.OnOrderItemClickListener
import com.example.presentation.paymentMethod.PaymentMethodActivity
import com.example.toolbar.ToolbarBuilder
import com.example.utils.formatToMxn
import kotlinx.android.synthetic.main.cart_layout.*
import java.util.concurrent.Executors

class CartActivity : AppCompatActivity() {

    private var recyclerView: androidx.recyclerview.widget.RecyclerView? = null
    private var adapter: ListCartAdapter? = null
    private var sessionManager: SessionManager? = null

    private lateinit var orderItemsRepository: OrderItemRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.cart_layout)

        ToolbarBuilder(this)

        sessionManager = SessionManager(this)

        recyclerView = findViewById(R.id.recyclerView)
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
            val intent = if(sessionManager?.isLogged() == true) {
                Intent(applicationContext, PaymentMethodActivity::class.java)
            } else {
                Intent(applicationContext, AccessActivity::class.java)
                    .putExtra("redirectToScreen", "payment")
            }
            startActivity(intent)
        }


    }

    private fun updateCartState(orderItems: List<OrderItem>) {
        if (orderItems.isEmpty()) {
            cartContent.visibility = View.GONE
            cartEmpty.visibility = View.VISIBLE
        } else {
            cartContent.visibility = View.VISIBLE
            cartEmpty.visibility = View.GONE
            adapter?.replaceItems(orderItems)

            totalText.text = orderItems.total().formatToMxn()
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
