package com.example.petstoreservice.PlashScreen.LoginRegister

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import com.example.petstoreservice.R
import kotlinx.coroutines.launch

@Composable
fun RegisterScreen(navController : NavHostController){
    var mail by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var registerMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.img_cat),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth().fillMaxHeight().alpha(0.4f),
            contentScale = ContentScale.Fit // Tùy chỉnh hiển thị hình ảnh
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(20.dp, 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
        ){
            Column (
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ){
                Text(
                    text = "Welcome to PetSS",
                    style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
                    textAlign = TextAlign.Center,
                    color = Color("#1387DC".toColorInt()),
                )
                Text(
                    text = "SignUp with PetSS in simple steps",
                    textAlign = TextAlign.Center,
                    color = Color("#1387DC".toColorInt()),
                )
            }
            NewsTextField(
                label = "User Name",
                placeholder = "Nhập User Name",
                text = userName,
                onTextChange = {newText ->
                    userName = newText
                },
                lendingIcon = Icons.Default.Person
            )
            NewsTextField(
                label = "Telehone",
                placeholder = "Nhập Phone Number",
                text = phone,
                onTextChange = {newText ->
                    phone = newText
                },
                lendingIcon = Icons.Default.Phone
            )
            NewsTextField(
                label = "Address",
                placeholder = "Nhập Address",
                text = address,
                onTextChange = {newText ->
                    address = newText
                },
                lendingIcon = Icons.Default.Place
            )
            NewsTextField(
                label = "Email",
                placeholder = "Nhập Email",
                text = mail,
                onTextChange = {newText ->
                    mail = newText
                },
                lendingIcon = Icons.Default.Email
            )
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
            NewsTextButton(
                modifier = Modifier.fillMaxWidth().padding(20.dp, 0.dp,20.dp, 0.dp),
                text = "Sign Up",
                onClick = {
                    isLoading = true
                    coroutineScope.launch {
                        try {
                            // Gọi API đăng nhập
                            val response = RetrofitClient.instance.register(userName, phone,address,mail,pass)
                            if (response.success) {
                                registerMessage = "Register Successful: ${response.message}"
                                //response.success == true;
                            } else {
                                registerMessage = "Register Failed: ${response.message}"
                            }
                        } catch (e: Exception) {
                            registerMessage = "Error: ${e.message}"
                        } finally {
                            isLoading = false
                        }
                    }
                },
                colorButton =  Color("#1387DC".toColorInt())
            )

            if (isLoading) {
                CircularProgressIndicator()
            }
            registerMessage?.let {
                Text(text = it)
            }
            val annotatedString = buildAnnotatedString {
                append("Already user? ")
                // Thêm phần chữ có thể nhấp vào
                pushStringAnnotation(tag = "LOGIN", annotation = "login")
                withStyle(style = SpanStyle(color = Color("#4E9F6B".toColorInt()))) {
                    append("Login Now")
                }
                pop()
            }
            // Sử dụng ClickableText để tạo văn bản có thể nhấp
            ClickableText(
                text = annotatedString,
                onClick = { offset ->
                    annotatedString.getStringAnnotations(tag = "LOGIN", start = offset, end = offset)
                        .firstOrNull()?.let {
                            navController.navigate(NavigationIteam.login.route)
                        }
                },
                style = MaterialTheme.typography.bodySmall // Định dạng kiểu chữ
            )
        }

    }


}

@Preview
@Composable
fun SignUpScreenPreview() {
    val navController = rememberNavController()
    RegisterScreen(navController)
}