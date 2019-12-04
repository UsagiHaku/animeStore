package com.example.presentation.serieDetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.animestore.R
import com.example.data.RestClient
import com.example.domain.Serie
import com.example.presentation.commentsList.CommentsListAdapter
import com.example.toolbar.ToolbarBuilder
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.serie_detail.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SerieDetailActivity: AppCompatActivity() {

    private var serieId: Int? = null
    private var commentAdapter: CommentsListAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.serie_detail)

        ToolbarBuilder(this)

        serieId = intent?.extras?.getInt("serieId")

        packageComments?.layoutManager = LinearLayoutManager(this)
        commentAdapter = CommentsListAdapter(this, arrayListOf())
        packageComments?.adapter = commentAdapter

        loadSerie(serieId)
    }

    private fun loadSerie(serieId: Int?) {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://sleepy-refuge-38259.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val restClient = retrofit.create<RestClient>(RestClient::class.java)

        val call = restClient.getSerie(serieId.toString())

        call.enqueue(object : Callback<Serie> {
            override fun onFailure(call: Call<Serie>, t: Throwable) {

            }

            override fun onResponse(call: Call<Serie>, response: Response<Serie>) {
                when (response.code()) {
                    200 -> response.body()?.let {
                        updateView(it)
                    }
                    401 -> {
                    }
                    else -> {
                    }
                }
            }

        })
    }

    fun updateView(serie: Serie) {
        Picasso.with(applicationContext)
            .load(serie.image)
            .into(serieImage)

        productName.text = serie.name
        serieRating.rating = serie.rating ?: 0f
        descriptionText.text = serie.description

        commentAdapter?.replaceItems(serie.comments)
    }
}
