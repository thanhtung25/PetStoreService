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
                    Log.d("PetsViewModel", "Failed to fetch cart: ${response.message ?: "No response"}")
                }
            } catch (e: Exception) {
                Log.e("PetsViewModel", "Error fetching cart: ${e.message}")
            }
        }
    }
//    fun updateCartQuantity(iduser: Int,productId: Int, newQuantity: Int) {
//        viewModelScope.launch {
//            try {
//                val response = RetrofitClient.instance.updateQuantity(iduser, productId, newQuantity)
//                if (response.success) {
//                    Log.d("CartViewModel", "Quantity updated successfully for productId: $productId")
//                    // Sau khi cập nhật thành công, gọi lại fetchCart để làm mới dữ liệu
//                    fetchCart()
//                } else {
//                    Log.e("CartViewModel", "Failed to update quantity: ${response.message}")
//                }
//            } catch (e: Exception) {
//                Log.e("CartViewModel", "Error updating quantity: ${e.message}")
//            }
//        }
//    }
}