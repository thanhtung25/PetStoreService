package com.example.petstoreservice.PlashScreen.Home.BagScreen.SubContainer

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.petstoreservice.PlashScreen.Model.Products
import com.example.petstoreservice.PlashScreen.Model.Warehouse

@Composable
fun WareHouseProducts(product: Products, warehouse: Warehouse,onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable {onClick()}
            .fillMaxWidth()
            .padding(10.dp)
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
    ){
        Row  (
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Column (
                modifier = Modifier.fillMaxWidth(0.333f),
            ){
                Image(
                painter = rememberAsyncImagePainter(model = product.image_url),
                contentDescription = null,// Adjust the size as needed
                modifier = Modifier.size(100.dp)
            )
            }

            Column (
                modifier = Modifier.fillMaxWidth(0.6f)
            ){
                Text(
                    modifier = Modifier.padding(0.dp,5.dp),
                    text = product.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    color = Color.Black,
                    maxLines = 1,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "Количество: ${warehouse.quantity} коробок",
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color.Gray ,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${product.weight} - 1 коробка",
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color.Gray ,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${product.calor} - 100 kcal",
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color.Gray ,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = product.expiry_date,
                    fontWeight = FontWeight.Normal,
                    fontSize = 12.sp,
                    color = Color.Red ,
                    maxLines = 2,
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis
                )
                TextButton(
                    onClick = {
                        onClick()
                    },
                    modifier = Modifier.size(40.dp),
                    border = BorderStroke(1.dp, color = Color.Black),
                    shape = RoundedCornerShape(50.dp),
                ) {
                    Text(
                        text = "+",
                        color = Color.Black,
                    )
                }
            }

        }
    }
}