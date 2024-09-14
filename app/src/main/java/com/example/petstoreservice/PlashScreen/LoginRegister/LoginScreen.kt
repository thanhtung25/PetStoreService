package com.example.petstoreservice.PlashScreen.LoginRegister

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.ClickableText
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.petstoreservice.PlashScreen.Dimens.mediumPadding1
import com.example.petstoreservice.PlashScreen.Navigation.NavigationIteam
import com.example.petstoreservice.PlashScreen.common.NewsTextButton
import com.example.petstoreservice.PlashScreen.common.NewsTextField
import com.example.petstoreservice.PlashScreen.onBoarding.components.onBoardingPage
import com.example.petstoreservice.PlashScreen.onBoarding.pages
import com.example.petstoreservice.R
import com.example.petstoreservice.ui.theme.PetStoreServiceTheme


@Composable
fun LoginScreen(navController: NavHostController){
    var userName by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(20.dp, 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
        }
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
        Column {
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
            val annotatedText = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.Gray)) {
                    append("Forget Password")
                }
            }
            ClickableText(
                modifier = Modifier.fillMaxWidth().wrapContentWidth(Alignment.End).padding(30.dp,10.dp),
                text = annotatedText,
                onClick = {
                },
                style = MaterialTheme.typography.bodySmall // Định dạng kiểu chữ
            )
        }

        NewsTextButton(
            modifier = Modifier.fillMaxWidth().padding(20.dp, 0.dp,20.dp, 0.dp),
            text = "Login",
            onClick = {}
        )
        val annotatedString = buildAnnotatedString {
            append("New user? ")
            // Thêm phần chữ có thể nhấp vào
            pushStringAnnotation(tag = "SIGN_UP", annotation = "sign_up")
            withStyle(style = SpanStyle(color = Color.Blue)) {
                append("Sign up for a new account")
            }
            pop()
        }
        // Sử dụng ClickableText để tạo văn bản có thể nhấp
        ClickableText(
            text = annotatedString,
            onClick = { offset ->
                annotatedString.getStringAnnotations(tag = "SIGN_UP", start = offset, end = offset)
                    .firstOrNull()?.let {
                        navController.navigate(NavigationIteam.signup.route)
                    }
            },
            style = MaterialTheme.typography.bodySmall // Định dạng kiểu chữ
        )
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    val navController = rememberNavController()
    LoginScreen(navController)
}
