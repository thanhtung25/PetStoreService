package com.example.petstoreservice.PlashScreen.Home.StoreScreen.SubContainer

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppbarStore (){
    var search by remember { mutableStateOf("") }
    Row (
        modifier = Modifier.fillMaxWidth()
            .shadow(
                elevation = 8.dp, // Độ cao của bóng mờ (càng cao thì bóng càng đậm)
            )
            .background(Color.White) // Màu nền và góc bo của `Row`
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
    ){
        Icon(
            modifier = Modifier.fillMaxWidth(fraction = 0.1f),
            imageVector =  Icons.Default.ArrowBack,
            contentDescription = null,
            tint = Color.Gray
        )
        BasicTextField(
            value = search,
            onValueChange = { newText ->
                search  = newText
            },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(40.dp)
                .padding(10.dp,0.dp)
                .border(
                    width = 1.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(size = 10.dp)
                ),
            textStyle = LocalTextStyle.current.copy( // Thêm `textStyle` để chỉnh cỡ chữ khi nhập văn bản
                fontSize = 14.sp,
                color = Color.Black // Đảm bảo văn bản có màu đen khi nhập
            ),

            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .background(Color.Transparent, RoundedCornerShape(size = 10.dp))
                        .padding(horizontal = 8.dp, vertical = 8.dp), // Đảm bảo không có padding bên trong
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(Icons.Default.Search, contentDescription = "")
                    Box(modifier = Modifier.weight(1f)) {
                        if (search.isEmpty()) { // Hiển thị placeholder khi không có giá trị
                            Text(text = "Search pet food", color = Color.Gray, fontSize = 14.sp)
                        }
                        innerTextField() // Hiển thị TextField thực tế
                    }
                }
            }
        )
        Icon(
            modifier = Modifier,
            imageVector =  Icons.Default.ShoppingCart,
            contentDescription = null,
            tint = Color.Gray
        )
    }
}
@Preview
@Composable
fun AppbarStorePreview(){
    AppbarStore()
}