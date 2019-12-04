package com.example.presentation.listProducts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animestore.R
import com.example.domain.Product
import com.example.utils.formatToMxn
import com.squareup.picasso.Picasso
import java.util.*

/**
 * Created by u.h. on 3/23/19.
 */
class ListProductsAdapter(
    private val context: Context,
    private val products: ArrayList<Product>,
    private val listener: OnProductClickListener
) : RecyclerView.Adapter<ListProductsAdapter.ListProductsHolder>() {

    interface OnProductClickListener {
        fun onProductClick(product: Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListProductsHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product_row, parent, false)

        return ListProductsHolder(view)
    }

    override fun onBindViewHolder(holder: ListProductsHolder, position: Int) {
        holder.setDetails(products[position], listener)
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
        private val productImage: ImageView = itemView.findViewById(R.id.serieImage)

        fun setDetails(product: Product, listener: OnProductClickListener) {
            productName.text = product.name
            productPrice.text = product.price.formatToMxn()
            Picasso.with(context)
                .load(product.image)
                .into(productImage)
            itemView.setOnClickListener {
                listener.onProductClick(product)
            }
        }
    }


}
