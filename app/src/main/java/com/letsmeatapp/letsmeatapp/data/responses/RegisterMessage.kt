package com.letsmeatapp.letsmeatapp.data.responses

data class RegisterMessage(
    val errors: RegisterErrors,
    val message: String
)