package com.example.presentation.commentsList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.animestore.R
import com.example.domain.Comment

class CommentsListAdapter(
    private val context: Context,
    private val comments: ArrayList<Comment>
) : RecyclerView.Adapter<CommentsListAdapter.ListCommentsItemHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCommentsItemHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.comment_row, parent, false)

        return ListCommentsItemHolder(view)
    }

    override fun onBindViewHolder(holder: ListCommentsItemHolder, position: Int) {
        holder.setData(comments[position])
    }

    override fun getItemCount(): Int {
        return comments.size
    }

    fun replaceItems(items: List<Comment>) {
        comments.clear()
        comments.addAll(items)
        notifyDataSetChanged()
    }

    inner class ListCommentsItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val username: TextView = view.findViewById(R.id.username)
        private val description: TextView = view.findViewById(R.id.description)

        fun setData(item: Comment) {
            username.text = item.username ?: context.getString(R.string.no_title)
            description.text = item.description
        }

    }


}
