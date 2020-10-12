package com.letsmeatapp.letsmeatapp.data.responses

data class GenericResponse (
    val message: String,
    val errors: List<Any>
)

data class RegisterErrors (
    val email: List<String>,
    val password: List<String>
)
