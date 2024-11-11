package com.example.petstoreservice.PlashScreen.Model

data class ApiResponse(
    val success: Boolean,
    val message: String,
    val result: List<User>?
)

data class User(
    val iduser: Int,
    val username: String,
    val telephone: String,
    val address: String,
    val email : String,
    val password: String
)
