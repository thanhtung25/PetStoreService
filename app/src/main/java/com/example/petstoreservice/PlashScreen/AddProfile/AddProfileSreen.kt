package com.example.petstoreservice.PlashScreen.AddProfile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.petstoreservice.PlashScreen.LoginRegister.RegisterScreen
import com.example.petstoreservice.PlashScreen.common.NewsIconsButton
import com.example.petstoreservice.R

@Composable
fun AddProfileSreen(navController: NavHostController) {
    Column (
        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(0.dp,0.dp,0.dp,80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ){
        Image(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(fraction = 0.5f),
            painter = painterResource(id = R.drawable.img_cat_study),
            contentDescription = null,
            contentScale =  ContentScale.Fit
        )
        Column (
            modifier = Modifier
                .fillMaxWidth().padding(30.dp,0.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(
                text = "Thêm Profile thú cưng",
                style = MaterialTheme.typography.displaySmall
                    .copy(fontWeight = FontWeight.Bold),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                color = Color.Black,
            )
            Text(
                text = "Bắt đầu một cuộc hành trình mới \ncủa bạn cùng thú cưng trên PetSS",
                textAlign = TextAlign.Center,
                color = Color.Gray,
            )
        }
        Row (
            modifier =  Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment =  Alignment.CenterVertically,
        ){
            Column (
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                NewsIconsButton(
                    modifier = Modifier,
                    onClick = {}
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Thêm profile thú cưng",
                    textAlign = TextAlign.Center,
                    color = Color.Gray,

                )

            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            )    {
                NewsIconsButton(
                    colorButton =  Color("#4E9F6B".toColorInt()),
                    modifier = Modifier,
                    icon = R.drawable.ic_photo,
                    onClick = {}
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Scan Body/ Scan QR",
                    textAlign = TextAlign.Center,
                    color = Color("#4E9F6B".toColorInt()),
                )

            }
        }


    }
}


@Preview
@Composable
fun AddProfileSreenPreview() {
    val navController = rememberNavController()
    AddProfileSreen(navController)
}