package com.example.domain

class Serie(
    var id: Int = 1,
    var name: String? = null,
    var description: String? = null,
    var rating: Float? = null,
    var image: String? = null,
    var comments: List<Comment> = arrayListOf()
)
