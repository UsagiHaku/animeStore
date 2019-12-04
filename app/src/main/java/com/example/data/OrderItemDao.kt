package com.example.data

import androidx.room.*
import com.example.domain.OrderItem
import androidx.room.Delete



@Dao
interface OrderItemDao {
    @Query("SELECT * from order_items_table")
    fun getOrderItems(): List<OrderItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(orderItem: OrderItem): Long

    @Query("DELETE FROM order_items_table")
    fun removeAll()

    @Delete
    fun removeOrderItems(vararg orderItems: OrderItem)
}
