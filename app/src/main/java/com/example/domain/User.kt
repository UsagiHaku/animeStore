package com.example.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_table")
data class User(
    @PrimaryKey var email: String,
    var username: String,
    var password: String
)

