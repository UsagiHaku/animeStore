package com.example.presentation.listProducts

import com.example.data.RestClient
import com.example.domain.Product
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ListProductsPresenter(private val view: ListProductsContract.View) : ListProductsContract.Presenter {

    override fun loadProducts() {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://sleepy-refuge-38259.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val restClient = retrofit.create<RestClient>(RestClient::class.java!!)

        val call = restClient.listProducts
        call.enqueue(object : Callback<List<Product>> {
            override fun onResponse(call: Call<List<Product>>, response: Response<List<Product>>) {
                when (response.code()) {
                    200 -> response.body()?.let {
                        view.showProducts(it)
                    }
                    401 -> {
                    }
                    else -> {
                    }
                }
            }

            override fun onFailure(call: Call<List<Product>>, t: Throwable) {

            }
        })

    }
}
