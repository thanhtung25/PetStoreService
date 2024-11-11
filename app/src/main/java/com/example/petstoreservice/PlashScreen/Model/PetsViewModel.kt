package com.example.petstoreservice.PlashScreen.Model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class PetsViewModel: ViewModel() {
    private val _pets = MutableLiveData<List<Pet>>()
    val pets: LiveData<List<Pet>> get() = _pets
    fun fetchPets() {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.instance.fetchPets()
                if (response.success) {
                    _pets.value = response.result
                    Log.d("PetsViewModel", "Pets fetched successfully: ${response.result}")
                } else {
                    Log.d("PetsViewModel", "Failed to fetch pets: ${response.message ?: "No response"}")
                }
            } catch (e: Exception) {
                Log.e("PetsViewModel", "Error fetching pets: ${e.message}")
            }
        }
    }

}

data class ApiResponsePet(
    val success: Boolean,
    val message: String,
    val result: List<Pet>
)
data class Pet(
    val idPet: Int,
    val petname: String,
    val petbreed: String,
    val petbirthdate: String,
    val petweight: String,
    val petgender: String,
    val petnutrition: String,
    val iduser: Int
)
