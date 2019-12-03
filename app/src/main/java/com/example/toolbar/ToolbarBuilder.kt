package com.example.toolbar


import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.animestore.R

class ToolbarBuilder(activity: AppCompatActivity) {
    var toolbar: Toolbar? = null

    init {
        activity.apply {
            toolbar = findViewById(R.id.toolbar)
            setSupportActionBar(toolbar)

            supportActionBar?.setDisplayShowTitleEnabled(false)

            if(!isTaskRoot) {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
                supportActionBar?.setDisplayShowHomeEnabled(true)
                toolbar?.setNavigationOnClickListener {
                    finish()
                }
            }
        }
    }
}
