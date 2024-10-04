package com.example.petstoreservice.PlashScreen.API

import org.hamcrest.Description

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
data class ApiResponseProduct(
    val success: Boolean,
    val message: String,
    val result: List<Products>
)

data class Products(
    val id: Int,
    val name: String,
    val type: String,
    val brand: String,
    val price: Int,
    var description: String,
    val expiry_date : String,
    val image_url: String
)