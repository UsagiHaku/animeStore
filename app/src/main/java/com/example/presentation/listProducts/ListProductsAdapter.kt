package com.example.presentation.listProducts

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.animestore.R
import com.example.domain.Product
import com.squareup.picasso.Picasso
import java.util.*

/**
 * Created by u.h. on 3/23/19.
 */
class ListProductsAdapter(private val context: Context, private val products: ArrayList<Product>,
                          private val listener: OnProductClickListener
) : RecyclerView.Adapter<ListProductsAdapter.ListProductsHolder>() {

    interface OnProductClickListener {
        fun onProductClick(product: Product)
        fun onAddProductClick(product: Product)
        fun onProductImageClick(product: Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListProductsHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product_row, parent, false)

        return ListProductsHolder(view)
    }

    override fun onBindViewHolder(holder: ListProductsHolder, position: Int) {
        val product = products[position]

        product.apply {
            when(position) {
                0 -> {
                    name = "Dragon Ball Temporada 1"
                    price = 50f
                }
                1 -> {
                    name = "Dragon Ball Temporada 2"
                    price = 60f
                }
            }
        }
        holder.setDetails(product, listener)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    fun addProducts(products: List<Product>) {
        this.products.addAll(products)
        notifyDataSetChanged()

    }

    inner class ListProductsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val productName: TextView = itemView.findViewById(R.id.productName)
        private val productPrice: TextView = itemView.findViewById(R.id.productPrice)
        private val productImage: ImageView = itemView.findViewById(R.id.productImage)

        fun setDetails(product: Product, listener: OnProductClickListener) {
            productName.text = product.name
            productPrice.text = product.price.toString()
            Picasso.with(context).load("https://images-na.ssl-images-amazon.com/images/I/81CVIiw%2BHgL._SX342_.jpg").into(productImage)
            itemView.setOnClickListener {
                listener.onProductClick(product)
            }
            productImage.setOnClickListener { listener.onProductImageClick(product) }
        }


    }


}
