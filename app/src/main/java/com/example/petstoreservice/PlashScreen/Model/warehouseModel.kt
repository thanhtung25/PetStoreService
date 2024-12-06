package com.example.petstoreservice.PlashScreen.Model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

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

class WasehouseViewModel : ViewModel() {
    private val _warehouse = MutableLiveData<List<Warehouse>>() // `Products` là `data class` chứa dữ liệu sản phẩm
    val warehouse: LiveData<List<Warehouse>> get() = _warehouse
    // Lấy danh sách sản phẩm từ API
    fun fetchWasehouse() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.fetchwarehouse()

                if (response.success) {
                    _warehouse.value = response.result
                    Log.d("WarehouseViewModel", "Products fetched successfully: ${response.result}")
                } else {
                    _warehouse.value = emptyList()
                    Log.d("WarehouseViewModel", "Failed to fetch warehouse: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("WarehouseViewModel", "Error fetching warehouse: ${e.message}")
            }
        }
    }

    //Danh sách sản phẩm
    private val _products = MutableLiveData<List<Products>>() // Danh sách sản phẩm
    val products: LiveData<List<Products>> get() = _products

    // Lấy danh sách sản phẩm từ API
    fun fetchProducts() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.fetchProducts()
                if (response.success) {
                    _products.value = response.result
                    Log.d("WarehouseViewModel", "Products fetched successfully: ${response.result}")
                } else {
                    Log.d("WarehouseViewModel", "Failed to fetch products: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("WarehouseViewModel", "Error fetching products: ${e.message}")
            }
        }
    }

    private val _filteredWarehouse = MutableLiveData<List<Pair<Warehouse, Products>>>() // Kết quả tìm kiếm
    val filteredWarehouse: LiveData<List<Pair<Warehouse, Products>>> get() = _filteredWarehouse
    // Tìm kiếm kho dựa trên tên sản phẩm
    fun searchWarehouseByProductName(query: String) {
        val warehouses = _warehouse.value ?: emptyList()
        val products = _products.value ?: emptyList()

        val filteredList = warehouses.mapNotNull { warehouse ->
            val matchingProduct = products.find {
                it.idproduct == warehouse.idproduct && it.name.contains(query, ignoreCase = true)
            }
            matchingProduct?.let { Pair(warehouse, it) }
        }

        _filteredWarehouse.value = filteredList
    }
}