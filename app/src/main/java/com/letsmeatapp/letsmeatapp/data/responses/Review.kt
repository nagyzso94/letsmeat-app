package com.letsmeatapp.letsmeatapp.data.responses

data class Review(
    val cleanness: Double,
    val other_aspect: String,
    val prices: Double,
    val restaurantId: Int,
    val restaurantName: String,
    val savouriness: Double,
    val service: Double,
    val userId: Int,
    val userName: String
)