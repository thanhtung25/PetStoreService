package com.example.petstoreservice.PlashScreen.Home.HomeScreen.SubContainer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.petstoreservice.PlashScreen.common.NewsTextButton
import com.example.petstoreservice.R

@Composable
fun FoodNotice (){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp,10.dp)
            .background(
                Color.White,
                shape = RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 70.dp,
                    bottomEnd = 10.dp,
                    bottomStart = 10.dp
                )
            ).border(1.dp, Color.Gray.copy(alpha = 0.5f), shape = RoundedCornerShape(
                topStart = 10.dp,
                topEnd = 70.dp,
                bottomEnd = 10.dp,
                bottomStart = 10.dp
            )
            )

    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(120.dp).padding(10.dp),
                painter = painterResource(R.drawable.img_catdog),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Column (
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ){
                Text(
                    modifier = Modifier.padding(0.dp,10.dp),
                    text = "Oppa !! В архиве пока нет товаров",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color("#5AB2FF".toColorInt()),
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                )
                NewsTextButton(
                    modifier = Modifier.fillMaxWidth().padding(10.dp,0.dp),
                    onClick = {},
                    text = "Добавить",
                    fontSize = 16
                )
            }

        }
    }
}

@Preview
@Composable
fun FoodNoticePreview(){
    FoodNotice()
}