package com.example.data

import com.example.domain.Product
import com.example.domain.Serie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by u.h. on 3/26/19.
 */

interface RestClient {

    @get:GET("products")
    val listProducts: Call<List<Product>>

    @get:GET("products/:id")
    val productDetail: Call<Product>

    @GET("products/{product_id}/series")
    fun getSeriesByPackage(@Path(value = "product_id", encoded = true) productId: String): Call<List<Serie>>

    @GET("series/{serie_id}")
    fun getSerie(@Path(value = "serie_id", encoded = true) serieId: String): Call<Serie>
}
