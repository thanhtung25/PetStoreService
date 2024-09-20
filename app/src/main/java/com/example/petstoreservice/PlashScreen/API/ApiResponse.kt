package com.example.petstoreservice.PlashScreen.API

data class ApiResponse(
    val success: Boolean,
    val message: String,
    val result: List<User>?
)

data class User(
    val id: Int,
    val username: String,
    val telephone: String,
    val address: String,
    val email : String,
    val password: String
)

