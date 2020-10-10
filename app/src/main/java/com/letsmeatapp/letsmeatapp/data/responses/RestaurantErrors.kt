package com.letsmeatapp.letsmeatapp.data.responses

data class RestaurantErrors(
    val address: List<String>,
    val name: List<String>,
    val phone_number: List<String>,
    val type: List<String>,
    val web_page: List<String>
)