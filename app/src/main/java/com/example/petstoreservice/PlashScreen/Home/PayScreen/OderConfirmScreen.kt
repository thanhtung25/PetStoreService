package com.example.petstoreservice.PlashScreen.Home.PayScreen

import RetrofitClient
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.petstoreservice.PlashScreen.Home.PayScreen.SubContainer.bill
import com.example.petstoreservice.PlashScreen.LoginRegister.getUserId
import com.example.petstoreservice.PlashScreen.Model.CartViewModel
import com.example.petstoreservice.PlashScreen.Model.ProductsViewModel
import com.example.petstoreservice.PlashScreen.Navigation.NavigationIteam
import com.example.petstoreservice.PlashScreen.common.NewsTextButton
import kotlinx.coroutines.launch

@Composable
fun OderConfirmScreen(
    navController : NavHostController,
    cartViewModel: CartViewModel = viewModel(),
    productsViewModel: ProductsViewModel = viewModel(),
    onNavigateToCart: () -> Unit,
) {
    val scrollState = rememberScrollState()
    val context = navController.context
    val (totalAmount, selectedCartIds) = getOrderData(context)
    var name by remember { mutableStateOf("") }
    var telephone by remember { mutableStateOf(("")) }
    var address by remember { mutableStateOf(("")) }
    var InputNumber by remember { mutableStateOf(("")) }
    var RoomNumber by remember { mutableStateOf(("")) }

    // api
    val coroutineScope = rememberCoroutineScope()
    var isLoading by remember { mutableStateOf(false) }
    // lay iduser
    val iduser = getUserId(context)
    //
    val cartItems by cartViewModel.cart.observeAsState(emptyList())
    val products by productsViewModel.products.observeAsState(emptyList())
    val userCartItems = cartItems.filter { it.iduser == iduser }
    // Gọi API khi `Composable` được tạo
    LaunchedEffect(Unit) {
        cartViewModel.fetchCart()
        productsViewModel.fetchProducts()
    }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color("#CAF4FF".toColorInt()))
            .padding(0.dp,24.dp)
            .verticalScroll(scrollState)
    ){
        Row (
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton(
                modifier = Modifier.fillMaxWidth(0.08f),
                onClick = {onNavigateToCart()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = Color("#8C8C8C".toColorInt())
                )
            }

            Text(
                modifier = Modifier.fillMaxWidth().padding(0.dp,0.dp,10.dp,0.dp),
                text = "Подтверждение заказа",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
        Text(
            modifier = Modifier.fillMaxWidth().padding(20.dp,10.dp),
            text = "Информация о получателе",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp,10.dp)
                .border(
                width = 1.dp,
                color = Color.Black,
                shape = RoundedCornerShape(size = 10.dp)
            ),
            placeholder = { Text("Имя") },
            value = name,
            onValueChange = { newText ->
                name = newText
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor =  Color.White,
                focusedIndicatorColor = Color.Transparent, // Xóa gạch chân khi focus
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(size = 10.dp)
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp,0.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(size = 10.dp)
                ),
            placeholder = { Text("Номер телефона") },
            value = telephone,
            onValueChange = { newText ->
                telephone = newText
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor =  Color.White,
                focusedIndicatorColor = Color.Transparent, // Xóa gạch chân khi focus
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(size = 10.dp)
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp,10.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(size = 10.dp)
                ),
            placeholder = { Text("Адрес") },
            value = address,
            onValueChange = { newText ->
                address = newText
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor =  Color.White,
                focusedIndicatorColor = Color.Transparent, // Xóa gạch chân khi focus
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(size = 10.dp)
        )

        Row (
            modifier = Modifier.fillMaxWidth().padding(20.dp, 0.dp)
        ){
            TextField(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .padding(0.dp,0.dp,10.dp,0.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(size = 10.dp)
                    ),
                placeholder = { Text("Номер ввода") },
                value = InputNumber,
                onValueChange = { newText ->
                    InputNumber = newText
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor =  Color.White,
                    focusedIndicatorColor = Color.Transparent, // Xóa gạch chân khi focus
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(size = 10.dp)
            )
            TextField(
                modifier = Modifier
                    .padding(10.dp,0.dp,0.dp,0.dp)
                    .border(
                        width = 1.dp,
                        color = Color.Black,
                        shape = RoundedCornerShape(size = 10.dp)
                    ),
                placeholder = { Text("Номер комнаты") },
                value = RoomNumber,
                onValueChange = { newText ->
                    RoomNumber = newText
                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor =  Color.White,
                    focusedIndicatorColor = Color.Transparent, // Xóa gạch chân khi focus
                    unfocusedIndicatorColor = Color.Transparent
                ),
                shape = RoundedCornerShape(size = 10.dp)
            )
        }
        bill(totalAmount)

        Column  (
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp,10.dp)
                .background(color = Color.White, shape = RoundedCornerShape(10.dp))
                .border(1.dp, color = Color.Gray , shape = RoundedCornerShape(10.dp))
                ,
        ){
            Row (
                modifier = Modifier.fillMaxWidth().padding(10.dp,10.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                Text(text = "Поддельный код скидки", fontSize = 16.sp)
                Text(
                    text ="Посмотреть больше",
                    color = Color("#5AB2FF".toColorInt()),
                    fontSize = 16.sp
                )
            }
            Row (
                modifier = Modifier.fillMaxWidth().padding(10.dp,10.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                TextButton(
                    modifier = Modifier.fillMaxWidth(0.5f).padding(10.dp,10.dp),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    shape = RoundedCornerShape(size = 10.dp),
                    border = BorderStroke(1.dp , color = Color.Gray)
                ) {
                    Text(
                        text = "Онлайн",
                    )
                }
                TextButton(
                    modifier = Modifier.fillMaxWidth().padding(10.dp,10.dp),
                    onClick = {},
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color("#5AB2FF".toColorInt())
                    ),
                    shape = RoundedCornerShape(size = 10.dp),
                    border = BorderStroke(1.dp , color = Color("#5AB2FF".toColorInt()))
                ) {
                    Text(
                        text = "Наличные",
                    )
                }

            }
            Text(
                modifier = Modifier.fillMaxWidth().padding(0.dp,0.dp,0.dp,10.dp),
                text ="Посмотреть больше",
                color = Color("#5AB2FF".toColorInt()),
                textAlign = TextAlign.Center
            )
        }

        NewsTextButton(
            modifier = Modifier.fillMaxWidth().padding(40.dp, 10.dp),
            onClick = {
                //add to warehouse
//                coroutineScope.launch {
//                    isLoading = true
//                    try {
//                        if (selectedProductIds.isEmpty()) {
//                            Log.e("UpdateCart", "No products selected")
//                        } else {
//                            selectedProductIds.forEach { (productId, quantity) ->
//                                val response1 = RetrofitClient.instance.add_warehouse(iduser, productId, quantity)
//                                Log.d("UpdateCart", "Processing productId: $productId with quantity: $quantity")
//                                //val response2 = RetrofitClient.instance.deletecart()
//                                if (response1.success) {
//                                    Log.d("UpdateCart", "Success: ${response1.message}")
//                                } else {
//                                    Log.e("UpdateCart", "Failed: ${response1.message}")
//                                }
//                            }
//                        }
//                    } catch (e: Exception) {
//                        Log.e("UpdateCart", "Error: ${e.message}")
//                    } finally {
//                        isLoading = false
//                    }
//                }
                // Launch coroutine to update cart and call the API
                coroutineScope.launch {
                    isLoading = true
                    try {
                        if (selectedCartIds.isEmpty()) {
                            Log.e("UpdateCart", "No products selected")
                        } else {
                            // Loop through selected cart IDs and get product details
                            selectedCartIds.forEach { idcart ->
                                val cartItem = userCartItems.find { it.idcart == idcart }
                                if (cartItem != null) {
                                    val productId = cartItem.idproduct // Replace with actual property if different
                                    val quantity = cartItem.quantity   // Replace with actual property if different

                                    // Call the API
                                    val response1 = RetrofitClient.instance.add_warehouse(iduser, productId, quantity)
                                    Log.d("UpdateWarehouse", "Processing productId: $productId with quantity: $quantity")
                                    if (response1.success) {
                                        cartViewModel.fetchCart()
                                        Log.d("UpdateWarehouse", "Success: ${response1.message}")
                                    } else {
                                        Log.e("UpdateWarehouse", "Failed: ${response1.message}")
                                    }
                                } else {
                                    Log.e("UpdateWarehouse", "Cart item not found for idcart: $idcart")
                                }
                                // delete cart
                                val response2 = RetrofitClient.instance.deletecart(idcart)
                                if (response2.success) {
                                    cartViewModel.fetchCart()
                                    Log.d("Deletecart", "Success: ${response2.message}")
                                } else {
                                    Log.e("Deletecart", "Failed: ${response2.message}")
                                }
                            }
                        }
                    } catch (e: Exception) {
                        Log.e("UpdateWarehouse", "Error: ${e.message}")
                    } finally {
                        isLoading = false
                    }
                }
                onNavigateToCart()
            },
            text = "Оплатить"
        )
    }
}

//@Preview
//@Composable
//fun OderConfirmScreenPreview(){
//    var navController = rememberNavController()
//    OderConfirmScreen(navController)
//}