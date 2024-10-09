package com.example.petstoreservice.PlashScreen.API

import org.hamcrest.Description

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

data class ApiResponsePet(
    val success: Boolean,
    val message: String,
    val result: List<Pet>?
)
data class Pet(
    val petname: String,
    val petbreed: String,
    val petbirthdate: String,
    val petweight: String,
    val petgender: String,
    val petnutrition: String,
    val iduser: Int
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