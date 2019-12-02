package com.example.domain

data class Order(
    var orderItems: ArrayList<OrderItem>,
    var total: Double
)
