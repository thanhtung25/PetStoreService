package com.example.petstoreservice.PlashScreen.Model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


data class ApiResponseProduct(
    val success: Boolean,
    val message: String,
    val result: List<Products>
)

data class Products(
    val idproduct: Int,
    val name: String,
    val type: String,
    val brand: String,
    val weight: Int,
    val calor: Int,
    val price: Int,
    var description: String,
    val expiry_date : String,
    val image_url: String
)

class ProductsViewModel : ViewModel() {
    private val _products = MutableLiveData<List<Products>>() // `Products` là `data class` chứa dữ liệu sản phẩm
    val products: LiveData<List<Products>> get() = _products
    // Lấy danh sách sản phẩm từ API
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

    private val _filteredProducts = MutableLiveData<List<Products>>()
    val filteredProducts: LiveData<List<Products>> get() = _filteredProducts
    // Lọc sản phẩm theo từ khóa tìm kiếm
    fun searchProducts(query: String) {
        // Lọc sản phẩm theo từ khóa tìm kiếm
        val filteredList = _products.value?.filter { it.name.contains(query, ignoreCase = true) }
        _filteredProducts.value = filteredList!!
    }
}