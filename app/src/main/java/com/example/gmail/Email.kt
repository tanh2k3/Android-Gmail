package com.example.gmail

data class Email(
    val senderName: String,
    val title: String,
    val content: String,
    val time: String,
    var isFavorite: Boolean = false
)
