package com.example.domain

import com.google.gson.annotations.SerializedName

class Product(
    var id: Int = 1,
    @SerializedName("sku") var sku: String? = null,
    var image: String? = null,
    var name: String? = null,
    var price: Double,
    var description: String? = null,
    var comments: List<Comment>? = arrayListOf()
)
