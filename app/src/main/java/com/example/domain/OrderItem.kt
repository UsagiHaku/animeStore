package com.example.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_items_table")
data class OrderItem(
    @PrimaryKey var id: Int,
    var price: Double,
    var name: String? = null,
    var image: String? = null,
    var quantity: Int = 1
)


fun List<OrderItem>.total(): Double {
    var total = 0.0

    forEach {
        total += it.price
    }

    return total
}
