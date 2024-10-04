package com.example.petstoreservice.PlashScreen.Home.BagScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun StorageScreen() {
    Column {
        Text(
            text = "Bag Screen"
        )
    }
}
@Composable
fun BagAppBar() {
    TopAppBar(
        title = { Text("Home", textAlign = TextAlign.Center, fontSize = 20.sp) },
        backgroundColor = Color(0xFF469E67)
    )
}