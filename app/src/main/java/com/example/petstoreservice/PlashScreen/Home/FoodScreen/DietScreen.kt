package com.example.petstoreservice.PlashScreen.Home.FoodScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun FoodScreen (){
    Column {
        Text(
            text = "Food Screen"
        )
    }
}
@Composable
fun FoodAppBar() {
    TopAppBar(
        title = { Text("Home", textAlign = TextAlign.Center, fontSize = 20.sp) },
        backgroundColor = Color(0xFF469E67)
    )
}