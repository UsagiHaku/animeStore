package com.example.domain

import com.google.gson.annotations.SerializedName
import java.util.*

class Product(
    var id: Int = 1,
    @SerializedName("sku") var sku: String? = null,
    var image: String? = null,
    var name: String? = null,
    var price: Double
)
