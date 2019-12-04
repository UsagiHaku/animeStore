package com.example.presentation.seriesList

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.domain.Serie
import android.view.LayoutInflater
import com.example.animestore.R
import com.example.utils.loadImage
import com.squareup.picasso.Picasso


class SeriesListAdapter(
    private val context: Context,
    private val series: ArrayList<Serie>
) : BaseAdapter() {

    override fun getCount(): Int {
        return series.size
    }

    override fun getItem(position: Int): Serie? {
        return series[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    fun replaceItems(items: List<Serie>) {
        series.clear()
        series.addAll(items)
        notifyDataSetChanged()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

        var gridView: View

        if (convertView == null) {
            gridView = inflater.inflate(R.layout.series_row, null)

            val serieImage = gridView.findViewById<View>(R.id.serieImage) as ImageView

            serieImage.loadImage(context, series[position].image)

        } else {
            gridView = convertView
        }

        return gridView

    }

}
