package com.example.utils

fun Double.formatToMxn(): String {
    return "$${"%.2f".format(this)} MXN"
}
