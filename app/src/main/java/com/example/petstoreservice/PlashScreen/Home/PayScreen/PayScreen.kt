package com.example.petstoreservice.PlashScreen.Home.PayScreen

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.petstoreservice.PlashScreen.Home.PayScreen.SubContainer.ProductItem
import com.example.petstoreservice.PlashScreen.Home.PayScreen.SubContainer.bill
import com.example.petstoreservice.PlashScreen.Home.PayScreen.SubContainer.discount
import com.example.petstoreservice.PlashScreen.LoginRegister.getUserId
import com.example.petstoreservice.PlashScreen.Model.CartViewModel
import com.example.petstoreservice.PlashScreen.Model.Products
import com.example.petstoreservice.PlashScreen.Model.ProductsViewModel
import com.example.petstoreservice.PlashScreen.Navigation.NavigationIteam
import com.example.petstoreservice.PlashScreen.common.NewsTextButton
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import androidx.compose.runtime.remember as remember

@Composable
fun PayScreen (
    cartViewModel: CartViewModel = viewModel(),
    productsViewModel: ProductsViewModel = viewModel(),
    navController: NavHostController,
){
    val scrollState = rememberScrollState()
    val cartItems by cartViewModel.cart.observeAsState(emptyList())
    val products by productsViewModel.products.observeAsState(emptyList())
    // lay iduser
    val context = navController.context
    val iduser = getUserId(context)
    println(iduser)
    // Gọi API khi `Composable` được tạo
    LaunchedEffect(Unit) {
        cartViewModel.fetchCart()
        productsViewModel.fetchProducts()
    }
    val userCartItems = cartItems.filter { it.iduser == iduser }


    // Tính tổng tiền
    var totalAmount by remember { mutableStateOf(0) }
    val selectedProductIds by remember { mutableStateOf(mutableListOf<Pair<Int, Int>>()) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(color = Color("#CAF4FF".toColorInt()))
            .padding(0.dp,10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Ваша корзина покупок",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(10.dp)
        )
        userCartItems.forEach { userCartItem ->
            // Lấy chi tiết sản phẩm dựa trên idproduct trong cart
            val product = products.find { it.idproduct == userCartItem.idproduct }
            if (product != null) {
                ProductItem(
                    userCartItem = userCartItem,
                    product = product,
                    loadCartItems = { cartViewModel.fetchCart() },
                    onUpdateTotal = {amount ->
                        totalAmount += amount
                        selectedProductIds.add(Pair(userCartItem.idproduct, userCartItem.quantity))
                        Log.d("CartUpdate", "Added product ID: ${userCartItem.idproduct}, Quantity: ${userCartItem.quantity}")
                    }
                )

            }
        }
        discount()

        bill(totalAmount)

        NewsTextButton(
            modifier = Modifier,
            onClick = {
                saveOrderData(context = navController.context, totalAmount = totalAmount, selectedProductIds = selectedProductIds)
                navController.navigate(NavigationIteam.oderconfirmscreen.route)
                println(selectedProductIds)
            },
            text = "Платить"
        )
    }
}


fun saveOrderData(context: Context, totalAmount: Int, selectedProductIds: List<Pair<Int, Int>>) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("OrderPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putInt("totalAmount", totalAmount)

    // Convert list to JSON
    try {
        val jsonSelectedProductIds = Gson().toJson(selectedProductIds)
        Log.d("OrderDataSave", "Saving product IDs and quantities: $jsonSelectedProductIds")
        editor.putString("selectedProductIds", jsonSelectedProductIds)

        val success = editor.commit() // Sử dụng commit() để lưu đồng bộ
        Log.d("OrderDataSave", "Commit success: $success")
    } catch (e: Exception) {
        Log.e("OrderDataSave", "Error saving data: ${e.message}")
    }
}

fun getOrderData(context: Context): Pair<Int, List<Pair<Int, Int>>> {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("OrderPrefs", Context.MODE_PRIVATE)
    val totalAmount = sharedPreferences.getInt("totalAmount", 0)

    // Retrieve JSON and convert back to List<Pair<Int, Int>>
    return try {
        val jsonSelectedProductIds = sharedPreferences.getString("selectedProductIds", "[]")
        val selectedProductIds: List<Pair<Int, Int>> = Gson().fromJson(jsonSelectedProductIds, object : TypeToken<List<Pair<Int, Int>>>() {}.type)
        Log.d("OrderDataRetrieve", "Retrieved data: totalAmount = $totalAmount, selectedProductIds = $selectedProductIds")
        Pair(totalAmount, selectedProductIds)
    } catch (e: Exception) {
        Log.e("OrderDataRetrieve", "Error retrieving data: ${e.message}")
        Pair(totalAmount, emptyList())
    }
}
