package com.example.data

import com.example.domain.OrderItem
import java.util.concurrent.Executor

class OrderItemRepository(
    private val orderItemDao: OrderItemDao,
    private val executor: Executor
) {

    fun getAllOrderItems(onSuccess: (List<OrderItem>) -> Unit) {
        executor.execute {
            onSuccess(orderItemDao.getOrderItems())
        }
    }

    fun addOrderItem(orderItem: OrderItem, onSuccess: () -> Unit) {
        executor.execute {
            orderItemDao.insert(orderItem)
            onSuccess()
        }
    }

    fun removeOrderItem(orderItem: OrderItem, onSuccess: () -> Unit) {
        executor.execute {
            orderItemDao.removeOrderItems(orderItem)
            onSuccess()
        }
    }

    fun removeAll(onSuccess: () -> Unit) {
        executor.execute {
            orderItemDao.removeAll()
            onSuccess()
        }
    }
}
