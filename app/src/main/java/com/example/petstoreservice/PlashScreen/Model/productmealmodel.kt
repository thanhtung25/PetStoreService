package com.example.petstoreservice.PlashScreen.Model

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class ProductMeal(
    val name: String,
    val weight: Int, // Đơn vị: gram
    val calor: Int, // Đơn vị: calo
    val idware: String
)

class ProductMealModel : ViewModel() {
    private val _breakfastList = mutableStateListOf<ProductMeal>()
    val breakfastList: List<ProductMeal> get() = _breakfastList
    // Hàm thêm sản phẩm vào danh sách
    fun addbreakfastList( name: String, weight: Int, calor: Int, idware: String) {
        val newProduct = ProductMeal( name, weight, calor,idware)
        _breakfastList.add(newProduct)
        println("Danh sách sản phẩm sau khi thêm: $_breakfastList")  // Kiểm tra log để xác nhận
    }
    // Hàm xóa sản phẩm theo ID
    fun removebreakfastList(idware: String) {
        _breakfastList.removeAll { it.idware == idware }
    }

    private val _lunchList = mutableStateListOf<ProductMeal>()
    val lunchList: List<ProductMeal> get() = _lunchList
    // Hàm thêm sản phẩm vào danh sách
    fun addlunchList( name: String, weight: Int, calor: Int, idware: String) {
        val newProduct = ProductMeal( name, weight, calor,idware)
        _lunchList.add(newProduct)
        println("Danh sách sản phẩm sau khi thêm: $_lunchList")  // Kiểm tra log để xác nhận
    }
    // Hàm xóa sản phẩm theo ID
    fun removelunchList(idware: String) {
        _lunchList.removeAll { it.idware == idware }
    }

    private val _dinnerList = mutableStateListOf<ProductMeal>()
    val dinnerList: List<ProductMeal> get() = _dinnerList
    // Hàm thêm sản phẩm vào danh sách
    fun adddinnerList( name: String, weight: Int, calor: Int, idware: String) {
        val newProduct = ProductMeal( name, weight, calor,idware)
        _dinnerList.add(newProduct)
        println("Danh sách sản phẩm sau khi thêm: $_dinnerList")  // Kiểm tra log để xác nhận
    }
    // Hàm xóa sản phẩm theo ID
    fun removedinnerList(idware: String) {
        _dinnerList.removeAll { it.idware == idware }
    }
}
