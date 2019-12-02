package com.example.presentation.productDetail


import com.example.data.RestClient
import com.example.domain.Product
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductDetailPresenter : ProductDetailContract.Presenter {
    private val view: ProductDetailContract.View? = null

    override fun loadProduct() {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://sleepy-refuge-38259.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val restClient = retrofit.create<RestClient>(RestClient::class.java!!)

        val call = restClient.productDetail
        call.enqueue(object : Callback<Product> {
            override fun onResponse(call: Call<Product>, response: Response<Product>) {
                when (response.code()) {
                    200 -> response.body()?.let {
                        view?.showProductDetail(it)
                    }
                    401 -> {
                    }
                    else -> {
                    }
                }
            }

            override fun onFailure(call: Call<Product>, t: Throwable) {

            }


        })


    }
}
