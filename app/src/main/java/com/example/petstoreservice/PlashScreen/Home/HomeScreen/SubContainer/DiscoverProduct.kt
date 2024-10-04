package com.example.petstoreservice.PlashScreen.Home.HomeScreen.SubContainer

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import coil.compose.rememberAsyncImagePainter
import com.example.petstoreservice.PlashScreen.API.Products
import com.example.petstoreservice.R

@Composable
fun DiscoverProduct(product: Products) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(30.dp,10.dp)
            .background(
                Color.White,
                shape = RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 10.dp,
                    bottomEnd = 10.dp,
                    bottomStart = 10.dp
                )
            ).border(1.dp, Color.Gray.copy(alpha = 0.5f), shape = RoundedCornerShape(
                topStart = 10.dp,
                topEnd = 10.dp,
                bottomEnd = 10.dp,
                bottomStart = 10.dp
            )
            )
    ){
        Column (
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Box(
                modifier = Modifier
                    .size(220.dp)
                , // Set the size for the Box
                contentAlignment = Alignment.BottomCenter // Center the content within the Box
            ) {
                // Drawing the semi-circular arc
                Canvas(
                    modifier = Modifier.fillMaxSize()
                ) {
                    drawArc(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color("#FDBB9A".toColorInt()), // Light pink on the left
                                Color("#FB86A0".toColorInt())  // Darker pink on the right
                            ),
                            start = Offset(0f, 0f), // Start from the left
                            end = Offset(Float.POSITIVE_INFINITY, 0f) // End at the right side
                        ), // Arc color
                        startAngle = -220f, // Start drawing from the left side
                        sweepAngle = 260f, // Sweep angle to create a half-circle
                        useCenter = false, // Do not use center, making it a line arc
                        style = Stroke(
                            width = 10.dp.toPx(), // Width of the arc
                            cap = StrokeCap.Round // Make the arc have rounded ends
                        ),
                        size = Size(size.width, size.height) // Size of the arc
                    )
                }


                // Add the image in the center of the arc
                Image(
                    painter = rememberAsyncImagePainter(model = product.image_url),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp).offset(y=(-50).dp) // Adjust the size as needed
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_tim),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp).offset(x= 78.dp,y=(-20.dp)).graphicsLayer(
                            rotationZ = 15f // Rotate the image by 15 degrees (adjust as needed)
                        ) // Adjust the size as needed
                )
                Text(
                    modifier = Modifier.padding(0.dp,10.dp).offset(y= 10.dp ) ,
                    text = "Любимые продукты месяца «Октябрь»",
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color("#FB86A0".toColorInt()) ,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                )
            }

            Text(
                modifier = Modifier.padding(0.dp,5.dp),
                text = product.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black,
                maxLines = 1,
                textAlign = TextAlign.Center,
            )
            Text(
                text = product.description,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = Color.Gray ,
                maxLines = 3,
                textAlign = TextAlign.Center,
            )
        }
    }

}
