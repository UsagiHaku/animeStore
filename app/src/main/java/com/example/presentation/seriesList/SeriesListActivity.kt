package com.example.presentation.seriesList

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.toolbar.ToolbarBuilder
import android.widget.Toast
import android.widget.GridView
import com.example.animestore.R
import com.example.data.RestClient
import com.example.domain.Serie
import com.example.presentation.serieDetail.SerieDetailActivity
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SeriesListActivity : AppCompatActivity() {

    private var seriesAdapter : SeriesListAdapter? = null
    private var packageId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        packageId = intent?.extras?.getInt("packageId")

        setContentView(R.layout.series_list)

        ToolbarBuilder(this)

        val gridView = findViewById<View>(R.id.seriesList) as GridView

        seriesAdapter = SeriesListAdapter(this, arrayListOf())

        // Instance of ImageAdapter Class
        gridView.adapter = seriesAdapter

        gridView.setOnItemClickListener { parent, view, position, id ->
            val serie = seriesAdapter?.getItem(position)

            val intent = Intent(applicationContext, SerieDetailActivity::class.java)
                .putExtra("serieId", serie?.id)

            startActivity(intent)
        }

        loadSeriesByPackageId(packageId)
    }

    private fun loadSeriesByPackageId(packageId: Int?) {
        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://sleepy-refuge-38259.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        val restClient = retrofit.create<RestClient>(RestClient::class.java)

        val call = restClient.getSeriesByPackage(packageId.toString())
        call.enqueue(object : Callback<List<Serie>> {
            override fun onFailure(call: Call<List<Serie>>, t: Throwable) {

            }

            override fun onResponse(call: Call<List<Serie>>, response: Response<List<Serie>>) {
                when (response.code()) {
                    200 -> response.body()?.let {
                        seriesAdapter?.replaceItems(it)
                    }
                    401 -> {
                    }
                    else -> {
                    }
                }
            }

        })
    }
}
