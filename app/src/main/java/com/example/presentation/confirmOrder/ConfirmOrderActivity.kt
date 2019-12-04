package com.example.presentation.confirmOrder

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animestore.R
import com.example.data.AnimeStoreDatabase
import com.example.data.OrderItemRepository
import com.example.domain.OrderItem
import com.example.domain.total
import com.example.presentation.listProducts.ListProductsActivity
import com.example.toolbar.ToolbarBuilder
import com.example.utils.formatToMxn
import com.example.utils.resetStack
import kotlinx.android.synthetic.main.confirm_order_layout.*
import kotlinx.android.synthetic.main.confirm_order_layout.cancelProcess
import kotlinx.android.synthetic.main.confirm_order_layout.finishProcess
import kotlinx.android.synthetic.main.payment_layout.*
import java.util.concurrent.Executors

class ConfirmOrderActivity: AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var adapter: ListProductsReviewAdapter? = null

    private lateinit var orderItemsRepository: OrderItemRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.confirm_order_layout)

        ToolbarBuilder(this)

        recyclerView = findViewById(R.id.productList)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        adapter = ListProductsReviewAdapter(this)
        recyclerView?.adapter = adapter

        val orderItemDao = AnimeStoreDatabase.getDatabase(application).orderItemDao()

        orderItemsRepository =
            OrderItemRepository(orderItemDao, Executors.newSingleThreadExecutor())

        orderItemsRepository.getAllOrderItems {
            updateProductReviewList(it)
        }

        finishProcess?.setOnClickListener {
            orderItemsRepository.removeAll {
                val intent = Intent(applicationContext, SuccessPaymentActivity::class.java)
                    .resetStack()
                startActivity(intent)
            }
        }

        cancelProcess?.setOnClickListener {
            val intent = Intent(applicationContext, ListProductsActivity::class.java)
                .resetStack()
            startActivity(intent)
        }
    }

    private fun updateProductReviewList(orderItems: List<OrderItem>) {
        adapter?.replaceItems(orderItems)

        totalText?.text = orderItems.total().formatToMxn()
    }
}
