package com.example.petstoreservice.PlashScreen.Home.FoodScreen.DietScreen.SubContainer

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Shapes
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.petstoreservice.PlashScreen.common.NewsTextButton

@Composable
fun Meal(
    meal : String = "Завтрак",
    caloin: Int = 0,
    caloout: Int = 120,
    onclick:()->Unit,
    subtitle: String = "Завтрак составляет 15% калорий в день.\n Рекомендации по питанию: 100-120 Kcal."
) {
    var inputValue by remember { mutableStateOf("0.02") }
    val progress = inputValue.toFloatOrNull()?.coerceIn(0f, 1f) ?: 0f
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(8.dp, shape = Shapes().small)
            .border(1.dp, color = Color.Gray.copy(0.5f), shape = Shapes().small),
        shape = Shapes().small,
        elevation = 4.dp
    ){
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Row (
                modifier = Modifier.fillMaxWidth().padding(10.dp,5.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    text = meal,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                )
                Text(
                    text = "${caloin}/${caloout} kcal ",
                    fontSize = 12.sp
                )
            }
            LinearProgressIndicator(
                progress = progress,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp,0.dp)
                    .height(6.dp)
                    .border(1.dp, color = Color.Transparent, shape = RoundedCornerShape(20.dp)),
                color = Color("#5AB2FF".toColorInt()),
                backgroundColor = Color("#CAF4FF".toColorInt())
            )
            Text(
                modifier = Modifier.fillMaxWidth().padding(10.dp,10.dp),
                text = subtitle,
                fontSize = 12.sp,
                color = Color.Gray
            )

            TextButton(
                onClick = onclick,
                modifier = Modifier.fillMaxWidth().padding(40.dp,0.dp,40.dp,5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color("#D9D9D9".toColorInt()),
                    contentColor = Color.Black
                )
            ) {
                Text(
                    text = "Сохранить меню",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
        }
    }

}

@Preview
@Composable
fun MealPreview(){
    Meal(onclick = {})
}