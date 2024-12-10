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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.petstoreservice.R

@Composable
fun PetStatus (){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp,20.dp)
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
        Column (
            modifier = Modifier.padding(10.dp)
        ){
            Row (
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
            ){
                Column(
                    modifier = Modifier.fillMaxWidth(0.5f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = "Статус питомца",
                        fontWeight = FontWeight.Bold
                    )
                    Image(
                        modifier = Modifier.size(120.dp),
                        painter = painterResource(R.drawable.img_cat),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "Статус питомца",
                        fontWeight = FontWeight.Normal,
                        color = Color.Red
                    )
                }
                Box(
                    modifier = Modifier
                        .size(130.dp)
                        .border(
                            5.dp,
                            Color("#469E67".toColorInt()).copy(alpha = 0.5f),
                            CircleShape
                        ).fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ){
                    Column (
                        modifier = Modifier,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ){
                        Text(
                            text = "0 g",
                            fontWeight = FontWeight.Bold,
                            fontSize = 40.sp
                        )
                    }
                }
            }

            Divider(
                color = Color.Gray.copy(alpha = 0.3f), // Use semi-transparent color
                thickness = 1.dp,
                modifier = Modifier.padding(10.dp) // Optional padding
            )

            Row (
                modifier = Modifier.fillMaxWidth(),
            ){
                Column (
                    modifier = Modifier.fillMaxWidth(0.3333f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "Мясо",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Divider(
                        color = Color("#FCB15B".toColorInt()), // Use semi-transparent color
                        thickness = 2.dp,
                        modifier = Modifier.padding(10.dp) // Optional padding
                    )
                    Text(
                        text = "Pыба",
                        color = Color.Gray.copy(alpha = 0.5f),
                    )
                }
                Column (
                    modifier = Modifier.fillMaxWidth(0.5f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "Oвощ",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Divider(
                        color = Color("#FCB15B".toColorInt()), // Use semi-transparent color
                        thickness = 2.dp,
                        modifier = Modifier.padding(10.dp) // Optional padding
                    )
                    Text(
                        text = "Мясо",
                        color = Color.Gray.copy(alpha = 0.5f),
                    )
                }
                Column (
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text(
                        text = "Pыба",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Divider(
                        color = Color("#FCB15B".toColorInt()), // Use semi-transparent color
                        thickness = 2.dp,
                        modifier = Modifier.padding(10.dp) // Optional padding
                    )
                    Text(
                        text = "Oвощ",
                        color = Color.Gray.copy(alpha = 0.5f),
                    )
                }
            }
        }
    }
}