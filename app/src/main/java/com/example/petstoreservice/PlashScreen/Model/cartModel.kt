package com.example.petstoreservice.PlashScreen.Model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

data class ApiResponseCart(
    val success: Boolean,
    val message: String,
    val result: List<cart>
)

data class cart(
    val idcart: Int,
    val iduser: Int,
    val idproduct: Int,
    val quantity: Int,
)

class CartViewModel: ViewModel() {
    private val _cart = MutableLiveData<List<cart>>()
    val cart: LiveData<List<cart>> get() = _cart

    fun fetchCart() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.fetchCart()
                if (response.success) {
                    _cart.value = response.result
                    Log.d("PetsViewModel", "cart fetched successfully: ${response.result}")
                } else {
                    _cart.value = emptyList()
                    Log.d("PetsViewModel", "Failed to fetch cart: ${response.message ?: "No response"}")
                }
            } catch (e: Exception) {
                Log.e("PetsViewModel", "Error fetching cart: ${e.message}")
            }
        }
    }

}