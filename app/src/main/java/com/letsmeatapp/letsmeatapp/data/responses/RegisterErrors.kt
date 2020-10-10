package com.letsmeatapp.letsmeatapp.data.responses

data class RegisterErrors(
    val email: List<String>,
    val password: List<String>
)