package com.example.petstoreservice.PlashScreen.Home.PayScreen.SubContainer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt

@Composable
fun bill(
    totalBill : Int,
) {
    var tax = 89
    var ship = 100
    var promode = 0
    var Subtotal = totalBill + tax + ship - promode
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp,10.dp)
            .background(color = Color.White ,  shape = RoundedCornerShape(20.dp))
            .border(1.dp, Color.Gray.copy(alpha = 0.5f), shape = RoundedCornerShape(20.dp)),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Row (
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Всего в корзине :"
            )
            Text(
                text = "$totalBill ₽"
            )
        }

        Row (
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Налог :"
            )
            Text(
                text = "$tax ₽"
            )
        }
        Row (
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Доставка :"
            )
            Text(
                text = "$ship ₽"
            )
        }
        Row (
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Промо-скидка :"
            )
            Text(
                text = "- $promode ₽"
            )
        }
        Divider(// Use semi-transparent color
            thickness = 3.dp,
            modifier = Modifier.padding(20.dp) // Optional padding
        )
        Row (
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Промежуточный итог:"
            )
            Text(
                text = "$Subtotal ₽"
            )
        }
    }
}

@Preview
@Composable
fun billScreen() {
    var totalBill = 200
    bill(totalBill)
}