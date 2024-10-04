package com.example.petstoreservice.PlashScreen.Model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.petstoreservice.PlashScreen.API.Products
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {
    private val _products = MutableLiveData<List<Products>>() // `Products` là `data class` chứa dữ liệu sản phẩm
    val products: LiveData<List<Products>> get() = _products

    fun fetchProducts() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.fetchProducts()
                if (response.success) {
                    _products.value = response.result
                    Log.d("ProductsViewModel", "Products fetched successfully: ${response.result}")
                } else {
                    Log.d("ProductsViewModel", "Failed to fetch products: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("ProductsViewModel", "Error fetching products: ${e.message}")
            }
        }
    }
}