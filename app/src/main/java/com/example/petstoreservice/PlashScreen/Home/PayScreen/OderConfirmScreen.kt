package com.example.petstoreservice.PlashScreen.Home.PayScreen

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
import androidx.compose.runtime.getValue
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
import com.example.petstoreservice.PlashScreen.Navigation.NavigationIteam
import com.example.petstoreservice.PlashScreen.common.NewsTextButton
import kotlinx.coroutines.launch

@Composable
fun OderConfirmScreen(
    navController : NavHostController,
) {
    val scrollState = rememberScrollState()
    val context = navController.context
    val (totalAmount, selectedProductIds) = getOrderData(context)
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
    Log.d("OrderDataCheck", "Total amount: $totalAmount, Selected products: $selectedProductIds")
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
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    modifier = Modifier.fillMaxWidth(0.08f),
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
                Text(text = "Поддельный код скидки")
                Text(
                    text ="Посмотреть больше",
                    color = Color("#5AB2FF".toColorInt())
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
                modifier = Modifier.fillMaxWidth(),
                text ="Посмотреть больше",
                color = Color("#5AB2FF".toColorInt()),
                textAlign = TextAlign.Center
            )
        }

        NewsTextButton(
            modifier = Modifier.fillMaxWidth().padding(40.dp, 10.dp),
            onClick = {
                //add to warehouse
                coroutineScope.launch {
                    isLoading = true

                    try {
                        if (selectedProductIds.isEmpty()) {
                            Log.e("UpdateCart", "No products selected")
                        } else {
                            selectedProductIds.forEach { (productId, quantity) ->
                                val response = RetrofitClient.instance.add_warehouse(iduser, productId, quantity)
                                Log.d("UpdateCart", "Processing productId: $productId with quantity: $quantity")

                                if (response.success) {
                                    Log.d("UpdateCart", "Success: ${response.message}")
                                } else {
                                    Log.e("UpdateCart", "Failed: ${response.message}")
                                }
                            }
                        }
                    } catch (e: Exception) {
                        Log.e("UpdateCart", "Error: ${e.message}")
                    } finally {
                        isLoading = false
                    }
                }
            },
            text = "Платить"
        )
    }
}

@Preview
@Composable
fun OderConfirmScreenPreview(){
    var navController = rememberNavController()
    OderConfirmScreen(navController)
}