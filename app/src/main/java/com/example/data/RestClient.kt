package com.example.data

import com.example.domain.Product
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by u.h. on 3/26/19.
 */

interface RestClient {

    @get:GET("products")
    val listProducts: Call<List<Product>>

    @get:GET("products/:id")
    val productDetail: Call<Product>
}
