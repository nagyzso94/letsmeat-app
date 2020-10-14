package com.letsmeatapp.letsmeatapp.data.responses

data class ReviewCreateResponse(
    val message: String,
    val user: User,
    val restaurant: Restaurant
)