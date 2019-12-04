package com.example.utils

import android.content.Context
import android.content.Intent
import android.widget.ImageView
import com.squareup.picasso.Picasso

fun Intent.resetStack(): Intent {
    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    return this
}

fun ImageView.loadImage(context: Context, imageSrc: String?) {
    Picasso.with(context)
        .load(imageSrc)
        .resize(720/4, 1280/4)
        .centerCrop()
        .into(this)
}
