package com.example.presentation.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animestore.R
import com.example.domain.OrderItem
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class ListCartAdapter(
    private val context: Context, private val orderItems: ArrayList<OrderItem>,
    private val listener: OnOrderItemClickListener
) : RecyclerView.Adapter<ListCartAdapter.ListCartItemHolder>() {

    interface OnOrderItemClickListener {
        fun onOrderItemClick(orderItem: OrderItem)
        fun onDeleteItemClick(orderItem: OrderItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCartItemHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.order_item_row, parent, false)

        return ListCartItemHolder(view)
    }

    override fun onBindViewHolder(holder: ListCartItemHolder, position: Int) {
        holder.setData(orderItems[position], listener)
    }

    override fun getItemCount(): Int {
        return orderItems.size
    }

    fun addItem(orderItem: OrderItem) {
        orderItems.add(orderItem)
        notifyDataSetChanged()
    }

    fun addItems(items: List<OrderItem>) {
        orderItems.addAll(items)
        notifyDataSetChanged()
    }

    fun replaceItems(items: List<OrderItem>) {
        orderItems.clear()
        orderItems.addAll(items)
        notifyDataSetChanged()
    }

    inner class ListCartItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val name: TextView = view.findViewById(R.id.orderItemName)
        private val price: TextView = view.findViewById(R.id.orderItemPrice)
        private val image: ImageView = view.findViewById(R.id.orderItemImage)
        private val removeOrderItem: Button = view.findViewById(R.id.remoteOrderItemButton)

        fun setData(item: OrderItem, listener: OnOrderItemClickListener) {
            name.text = item.name ?: context.getString(R.string.no_title)
            price.text = item.price.toString()
            Picasso.with(context)
                .load("https://images-na.ssl-images-amazon.com/images/I/81CVIiw%2BHgL._SX342_.jpg")
                .into(image)
            itemView.setOnClickListener {
                listener.onOrderItemClick(item)
            }
            removeOrderItem.setOnClickListener {
                listener.onDeleteItemClick(item)
            }
        }


    }


}
