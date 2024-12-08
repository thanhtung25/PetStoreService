package com.example.petstoreservice.PlashScreen.Home.FoodScreen.DietScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Shapes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import com.example.petstoreservice.PlashScreen.Home.FoodScreen.DietScreen.SubContainer.Mealbreakfast
import com.example.petstoreservice.PlashScreen.Home.FoodScreen.DietScreen.SubContainer.Meallunch
import com.example.petstoreservice.PlashScreen.Home.PayScreen.saveOrderData
import com.example.petstoreservice.PlashScreen.Model.ProductMealModel
import com.example.petstoreservice.PlashScreen.Navigation.NavigationIteam
import com.example.petstoreservice.PlashScreen.common.NewsTextButton
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Diet1(
    navController : NavHostController,
    productMealModel: ProductMealModel,
    onNavigateToCart: () -> Unit,
) {
    // Lấy ngày hiện tại
    val currentDate = LocalDate.now()

    // Tạo một formatter để lấy tên ngày, tháng
    val dayFormatter = DateTimeFormatter.ofPattern("EEE",  Locale("ru") )// Lấy tên ngày (Monday, Tuesday,...)
    val monthFormatter = DateTimeFormatter.ofPattern("MMM",  Locale("ru")) // Lấy tên tháng (January, February,...)

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp, 40.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp, 0.dp, 10.dp, 0.dp),
                text = "Диета по предпочтению",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp, 10.dp, 10.dp, 20.dp)
                .horizontalScroll(rememberScrollState()),
        ) {
            for (i in 0 until 8) {
                val date = currentDate.plusDays(i.toLong()) // Tính ngày tiếp theo
                val dayOfWeek = dayFormatter.format(date) // Lấy tên thứ viết tắt
                val month = monthFormatter.format(date) // Lấy tên tháng viết tắt
                val dayOfMonth = date.dayOfMonth
                // Tạo button cho mỗi ngày
                Card (
                    onClick = { /* Handle click event */ },
                    shape = RoundedCornerShape(15.dp),
                    modifier = Modifier
                        .width(100.dp)
                        .fillMaxWidth()
                        .padding(0.dp, 0.dp, 5.dp, 0.dp),
                    colors = CardColors(
                        containerColor = Color("#CAF4FF".toColorInt()),
                        contentColor = Color("#5AB2FF".toColorInt()),
                        disabledContainerColor = Color("#CAF4FF".toColorInt()),
                        disabledContentColor = Color("#5AB2FF".toColorInt()),)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp, 20.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "$dayOfMonth $month",
                        )
                        Text(
                            text = dayOfWeek,
                        )
                    }
                }
            }
        }
        Mealbreakfast(onclick = {navController.navigate(NavigationIteam.breakfast.route)}, productMealModel = productMealModel)


        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            text = "Больше блюд   -->",
            textAlign = TextAlign.End,
            color = Color("#5AB2FF".toColorInt()),
            fontSize = 11.sp
        )

        Meallunch(onclick = {navController.navigate(NavigationIteam.lunch.route)}, productMealModel = productMealModel)
//        Meal(
//            meal = "Обед",
//            onclick = {},
//            caloin = 0,
//            caloout = 400,
//            subtitle = "Обед составляет 50% калорий в день.\nРекомендации по питанию: 350-400 Kcal.",
//        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            text = "Больше блюд   -->",
            textAlign = TextAlign.End,
            color = Color("#5AB2FF".toColorInt()),
            fontSize = 11.sp
        )
//        Meal(
//            meal = "Ужин",
//            onclick = {},
//            caloin = 0,
//            caloout = 280,
//            subtitle = "Ужин составляет 35% калорий в день.\nРекомендации по питанию: 250-280 Kcal",
//        )
        NewsTextButton(
            modifier = Modifier.padding(10.dp),
            onClick = {
            },
            text = "Сохранить меню",
            fontSize = 16
        )

    }
}
//@RequiresApi(Build.VERSION_CODES.O)
//@Preview
//@Composable
//fun Diet1Preview(){
//    Diet1(onNavigateToCart = {})
//}
