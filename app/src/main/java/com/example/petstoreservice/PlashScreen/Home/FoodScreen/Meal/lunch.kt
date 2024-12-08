package com.example.petstoreservice.PlashScreen.Home.FoodScreen.Meal

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.petstoreservice.PlashScreen.Model.ProductMealModel
import com.example.petstoreservice.PlashScreen.Navigation.NavigationIteam
import com.example.petstoreservice.R

@Composable
fun lunch (
    navController: NavController,
    productMealModel: ProductMealModel,
    onNavigateToCart: () -> Unit,
){
    // Lấy ViewModel để truy cập vào productmealmodel
    val productList = productMealModel.lunchList
    var inputValue by remember { mutableStateOf("0.02") }

    var showDialog by remember { mutableStateOf(false) }
    val totalCalories = productList.sumOf { it.calor }
    Column (
        modifier = Modifier.fillMaxSize().padding(0.dp, 20.dp,0.dp,0.dp).background(color = Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if(productList.isEmpty()){
            var progress = inputValue.toFloatOrNull()?.coerceIn(0f, 1f) ?: 0f
            Column (
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.3f)
            ){
                Row (
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(
                        modifier = Modifier.fillMaxWidth(0.08f),
                        onClick = { navController.popBackStack()
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
                        text = "Обед",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                Text(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    text = "Обед составляет 50% калорий в день.\n Рекомендации по питанию: 350-400 Kcal.",
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
                Text(
                    modifier = Modifier.fillMaxWidth().padding(10.dp,0.dp,10.dp,0.dp),
                    text = "0 kcal",
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp,10.dp)
                        .height(6.dp)
                        .border(1.dp, color = Color.Transparent, shape = RoundedCornerShape(20.dp)),
                    color = Color("#5AB2FF".toColorInt()),
                    backgroundColor = Color("#CAF4FF".toColorInt())
                )
            }

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
                    .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)) // Viền chỉ ở trên
                    .padding(top = 1.dp), // Thêm một chút padding nếu muốn viền rõ hơn
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Text(
                    modifier = Modifier.fillMaxWidth().padding(20.dp,10.dp),
                    text = "Еда и витамины",
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .background(
                            color = Color.White, shape = RoundedCornerShape(20.dp,80.dp,20.dp,20.dp)
                        )
                        .border(1.dp, color = Color.Gray.copy(0.5f), shape = RoundedCornerShape(20.dp,80.dp,20.dp,20.dp))
                    ,
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Column (
                        modifier = Modifier.fillMaxWidth(0.6f).padding(10.dp, 20.dp,0.dp, 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ){
                        // Thêm chữ
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = "Упс!!! Меню на завтрак\n пока нет",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            textAlign = TextAlign.Center,

                            )
                        TextButton(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            onClick = { onNavigateToCart()},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color("#5AB2FF".toColorInt()),
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(size = 10.dp)
                        ) {
                            androidx.compose.material3.Text(
                                text = "Добавить",
                                fontSize = 16.sp,
                            )
                        }
                    }
                    // Thêm ảnh
                    Image(
                        painter = painterResource(id = R.drawable.img_box_cat), // Đổi URL hình ảnh theo ý muốn
                        contentDescription = "Giỏ hàng trống",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                }

            }
        }
        else{
            var progress = totalCalories.toFloat() / 400f
            Column (
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.3f)
            ){
                Row (
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(
                        modifier = Modifier.fillMaxWidth(0.08f),
                        onClick = { navController.popBackStack()
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
                        text = "Обед",
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
                Text(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    text = "Обед еду приходится 50% ежедневных калорий\nРекомендации по питанию: 350-400 калорий.",
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                )
                Text(
                    modifier = Modifier.fillMaxWidth().padding(10.dp,0.dp,10.dp,0.dp),
                    text = "${totalCalories} kcal",
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp,10.dp)
                        .height(6.dp)
                        .border(1.dp, color = Color.Transparent, shape = RoundedCornerShape(20.dp)),
                    color = Color("#5AB2FF".toColorInt()),
                    backgroundColor = Color("#CAF4FF".toColorInt())
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(0.dp, 10.dp, 0.dp, 0.dp)
                    .border(1.dp, color = Color.Gray, shape = RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)) // Viền chỉ ở trên
                    .padding(top = 1.dp), // Thêm một chút padding nếu muốn viền rõ hơn
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Text(
                    modifier = Modifier.fillMaxWidth().padding(20.dp,10.dp),
                    text = "Еда и витамины",
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
                LazyColumn {
                    items(productList) { product ->
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(10.dp)
                        ) {
                            Row (
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ){
                                Column (
                                    modifier = Modifier.padding(10.dp,0.dp)
                                ){
                                    Text(
                                        text = product.name,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    )
                                    Text(
                                        text = "${product.weight}g - ${product.calor}kcal",
                                        color = Color.Gray
                                    )
                                }
                                IconButton(
                                    onClick = {
                                        showDialog = true
                                    },
                                    modifier = Modifier.padding(0.dp,0.dp,7.dp,0.dp),
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = "Remove Product",
                                        tint = Color.Black
                                    )
                                }

                                // Hộp thoại xác nhận
                                if (showDialog) {
                                    AlertDialog(
                                        onDismissRequest = { showDialog = false },
                                        title = { Text(text = "Подтвердить удаление") },
                                        text = { Text(text = "Вы уверены, что хотите удалить этот товар ") },
                                        confirmButton = {
                                            Button(onClick = {
                                                productMealModel.removelunchList(product.idware)
                                                showDialog =false
                                            }) {
                                                Text("Удалить")
                                            }
                                        },
                                        dismissButton = {
                                            Button(onClick = { showDialog = false }) {
                                                Text("Отмена")
                                            }
                                        }
                                    )
                                }
                            }

                            Divider(// Use semi-transparent color
                                thickness = 3.dp,
                                modifier = Modifier.padding(20.dp) // Optional padding
                            )
                        }

                    }
                }
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ){
                    TextButton(
                        modifier = Modifier
                            .padding(10.dp),
                        onClick = {
                            onNavigateToCart()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color("#5AB2FF".toColorInt()),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(size = 10.dp)
                    ) {
                        androidx.compose.material3.Text(
                            text = "Добавить",
                            fontSize = 16.sp,
                        )
                    }
                    TextButton(
                        modifier = Modifier
                            .padding(10.dp),
                        onClick = {
                            navController.navigate(NavigationIteam.diet1.route)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color("#5AB2FF".toColorInt()),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(size = 10.dp)
                    ) {
                        androidx.compose.material3.Text(
                            text = "Сохранить меню",
                            fontSize = 16.sp,
                        )
                    }
                }

            }
        }


    }
}