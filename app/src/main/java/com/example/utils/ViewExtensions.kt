package com.example.utils

import android.content.Intent

fun Intent.resetStack(): Intent {
    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    return this
}
