package com.example.presentation.confirmOrder

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animestore.R
import com.example.domain.OrderItem
import com.example.utils.formatToMxn

/**`
 * Created by u.h. on 3/23/19.
 */
class ListProductsReviewAdapter(
    private val context: Context,
    private val orderItems: ArrayList<OrderItem> = arrayListOf()
) : RecyclerView.Adapter<ListProductsReviewAdapter.ListProductsReviewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListProductsReviewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.product_review_row, parent, false)

        return ListProductsReviewHolder(view)
    }

    override fun onBindViewHolder(holder: ListProductsReviewHolder, position: Int) {
        holder.setDetails(orderItems[position])
    }

    override fun getItemCount(): Int {
        return orderItems.size
    }

    fun replaceItems(items: List<OrderItem>) {
        orderItems.clear()
        orderItems.addAll(items)
        notifyDataSetChanged()
    }

    inner class ListProductsReviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val packageName: TextView = itemView.findViewById(R.id.packageName)
        private val packagePrice: TextView = itemView.findViewById(R.id.packagePrice)

        fun setDetails(orderItem: OrderItem) {
            packageName.text = orderItem.name
            packagePrice.text = orderItem.price.formatToMxn()
        }
    }


}
