package com.example.petstoreservice.PlashScreen.Home.FoodScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.petstoreservice.PlashScreen.Home.FoodScreen.SubContainer.MenuItemCard
import com.example.petstoreservice.PlashScreen.Navigation.NavigationIteam
import com.example.petstoreservice.R

@Composable
fun FoodScreen (navController: NavHostController){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = "Меню для экспорта",
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        // Danh sách các mục
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            items(getMenuItems()) { menuItem ->
                MenuItemCard(menuItem,
                    onClick = {route ->
                        navController.navigate(route)
                    }
                )
            }
        }
    }
}

data class MenuItem(val title: String, val description: String, val imageRes: Int, val color : String, val route: String)

fun getMenuItems(): List<MenuItem> {
    return listOf(
        MenuItem("Диета по предпочтениям", "Рекомендуемое меню основано на физическом состоянии вашего питомца и диетических предпочтениях.", R.drawable.ic_diet_1,"#FDDCDC", NavigationIteam.diet1.route),
        MenuItem("Диета доктора", "Рекомендуемое меню основано на физическом состоянии вашего питомца и диетических предпочтениях.", R.drawable.ic_diet_2,"#E0EFF9", NavigationIteam.diet2.route),
        MenuItem("Диета увеличивает вес", "Рекомендуемое меню основано на физическом состоянии вашего питомца и диетических предпочтениях.", R.drawable.ic_diet_3,"#FAEDC9", NavigationIteam.diet3.route),
        MenuItem("Диета для похудения", "Рекомендуемое меню основано на физическом состоянии вашего питомца и диетических предпочтениях.", R.drawable.ic_diet_4,"#F0EDFB", NavigationIteam.diet4.route),
        MenuItem("Здоровое питание", "Рекомендуемое меню основано на физическом состоянии вашего питомца и диетических предпочтениях.", R.drawable.ic_diet_5,"#E3FCDA", NavigationIteam.diet5.route)
    )
}