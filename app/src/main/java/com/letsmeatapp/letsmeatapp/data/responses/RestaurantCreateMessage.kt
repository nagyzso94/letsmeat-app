package com.letsmeatapp.letsmeatapp.data.responses

data class RestaurantCreateMessage(
    val errors: RestaurantErrors,
    val message: String
)