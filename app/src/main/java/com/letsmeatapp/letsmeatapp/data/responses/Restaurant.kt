package com.letsmeatapp.letsmeatapp.data.responses

data class Restaurant(
    val id: Int,
    val name: String,
    val address: String,
    val phone_number: String,
    val web_page: String,
    val type: Int,
    val created_at: String,
    val updated_at: String
)