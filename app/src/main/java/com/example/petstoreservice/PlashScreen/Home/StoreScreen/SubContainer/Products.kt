package com.example.petstoreservice.PlashScreen.Home.StoreScreen.SubContainer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.petstoreservice.PlashScreen.API.Products

@Composable
fun Products(product: Products,onClick: () -> Unit){
    Box(
        modifier = Modifier
            .width(160.dp)
            .padding(0.dp,0.dp,10.dp,0.dp)
            .background(
                Color.White,
                shape = RoundedCornerShape(
                    topStart = 10.dp,
                    topEnd = 10.dp,
                    bottomEnd = 10.dp,
                    bottomStart = 10.dp
                )
            )
            .border(1.dp, Color.Gray.copy(alpha = 0.5f), shape = RoundedCornerShape(
                topStart = 10.dp,
                topEnd = 10.dp,
                bottomEnd = 10.dp,
                bottomStart = 10.dp
                )
            )
            .clickable { onClick() }
    ){
        Column (
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Image(
                painter = rememberAsyncImagePainter(model = product.image_url),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp)// Adjust the size as needed
            )
            Text(
                modifier = Modifier.padding(0.dp,5.dp),
                text = product.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = Color.Black,
                maxLines = 1,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = product.description
                ,
                fontWeight = FontWeight.Normal,
                fontSize = 12.sp,
                color = Color.Gray ,
                maxLines = 2,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
//@Preview
//@Composable
//fun ProductsPreview(){
//    Products()
//}