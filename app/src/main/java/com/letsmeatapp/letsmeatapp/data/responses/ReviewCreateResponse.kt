package com.letsmeatapp.letsmeatapp.data.responses

data class ReviewCreateResponse(
    val message: String,
    val user: UserX,
    val étterem: Étterem
)