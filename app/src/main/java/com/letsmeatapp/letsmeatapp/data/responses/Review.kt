package com.letsmeatapp.letsmeatapp.data.responses

data class Review(
    val cleanness: Int,
    val created_at: String,
    val id: Int,
    val other_aspect: String,
    val prices: Int,
    val restaurant_id: Int,
    val savouriness: Int,
    val service: Int,
    val updated_at: String,
    val user_id: Int
)