package com.example.petstoreservice.PlashScreen.Model

data class ApiResponseWarehouse(
    val success: Boolean,
    val message: String,
    val result: List<Warehouse>
)

data class Warehouse(
    val idware: Int,
    val iduser: Int,
    val idproduct: Int,
    val quantity: Int,
)
