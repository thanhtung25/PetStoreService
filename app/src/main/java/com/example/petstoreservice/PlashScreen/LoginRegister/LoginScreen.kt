package com.example.petstoreservice.PlashScreen.LoginRegister

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.example.petstoreservice.PlashScreen.Dimens.mediumPadding1
import com.example.petstoreservice.PlashScreen.common.NewsTextField
import com.example.petstoreservice.PlashScreen.onBoarding.components.onBoardingPage
import com.example.petstoreservice.PlashScreen.onBoarding.pages
import com.example.petstoreservice.R
import com.example.petstoreservice.ui.theme.PetStoreServiceTheme


@Composable
fun LoginScreen(){
    var userName by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.SpaceEvenly,
    ){
        Spacer(modifier = Modifier.height(mediumPadding1))
        Text(
            text = "Welcome to PetSS",
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Center,
            color = Color("#4E9F6B".toColorInt()),
        )
        Text(
            text = "Login to continue using PetSS",
            textAlign = TextAlign.Center,
            color = Color("#4E9F6B".toColorInt()),
        )
        Spacer(modifier = Modifier.height(mediumPadding1))
        Image(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(fraction = 0.5f),
            painter = painterResource(id = R.drawable.img_cat_study),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        NewsTextField(
            label = "User Name",
            placeholder = "Nhập User Name",
            text = userName,
            onTextChange = {newText ->
                userName = newText
            },
            lendingIcon = Icons.Default.Person
        )
        Spacer(modifier = Modifier.height(mediumPadding1))
        NewsTextField(
            label = "Password",
            placeholder = "Nhập Password",
            text = pass,
            onTextChange = {newText ->
                pass = newText
            },
            lendingIcon = Icons.Default.Lock,
            isPassword = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OnLoginScreen(){
    PetStoreServiceTheme{
        LoginScreen()
    }
}