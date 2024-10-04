package com.example.petstoreservice.PlashScreen.Home.HomeScreen.SubContainer

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.petstoreservice.R

@Composable
fun MenuManagement(
    @DrawableRes image: Int = R.drawable.img_foodpet,
    textTitle : String = "Bua Sang",
    textTime: String = "6:00",
    text: String = "Bua sang chien 15% luong calo /1 ngay",
    colorTransform1: Color =  Color("#fb786d".toColorInt()),
    colorTransform2: Color =  Color("#FCBC8F".toColorInt())

){
    Box(
        modifier = Modifier.width(150.dp)
    ){
        Column (

        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp,20.dp,15.dp,0.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                colorTransform1, // Color at the top-left corner
                                colorTransform2 // Color at the bottom-right corner
                            ),
                            start = Offset(0f, 0f), // Top-left corner
                            end = Offset(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY) // Bottom-right corner
                        ),
                        shape = RoundedCornerShape(
                            topStart = 10.dp,
                            topEnd = 100.dp,
                            bottomEnd = 10.dp,
                            bottomStart = 10.dp
                        )
                    )
            ){
                Column (
                    modifier = Modifier.fillMaxWidth().padding(5.dp,45.dp,10.dp,10.dp),
                    horizontalAlignment = Alignment.Start
                ){
                    Text(
                        text = textTitle,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                    Text(
                        modifier = Modifier.padding(0.dp,5.dp),
                        text = textTime,
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.White
                    )
                    Text(
                        text = text,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Color.White,
                        maxLines = 2
                    )
                    Image(
                        modifier = Modifier
                            .size(50.dp) // Image size inside the border
                            .clip(CircleShape)
                            .padding(10.dp)
                            .align( Alignment.CenterHorizontally),
                        painter = painterResource(id = R.drawable.ic_add),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )

                }
            }

        }
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .align(Alignment.TopStart) // Align to top-center of the Box
                .offset(x= 5.dp,y = (-20).dp) // Offset upwards by half of its size (40.dp)
        )
    }


}

@Preview
@Composable
fun MenuManagementPreview(){
    MenuManagement()
}