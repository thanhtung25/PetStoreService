package com.example.petstoreservice.PlashScreen.Home.FoodScreen.SubContainer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.example.petstoreservice.PlashScreen.Home.FoodScreen.MenuItem

@Composable
fun MenuItemCard(
    menuItem: MenuItem,
    onClick:(String)->Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable {onClick(menuItem.route)
            },
        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 80.dp, bottomEnd = 16.dp, bottomStart = 16.dp),
        elevation = 4.dp
    ) {
        Box(
            modifier = Modifier
                .background(color = Color(menuItem.color.toColorInt())) // Màu nền thẻ chính (hồng nhạt)
                .border(
                    width = 1.dp,
                    color = Color.Black, // Màu viền ngoài (hồng)
                    shape = RoundedCornerShape(topStart = 16.dp, topEnd = 80.dp, bottomEnd = 16.dp, bottomStart = 16.dp)
                )
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Nội dung text
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.8f) // Chiếm phần lớn không gian
                        .padding(end = 16.dp)
                        .background(
                            color = Color.White, // Nền trắng
                            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 80.dp, bottomEnd = 0.dp, bottomStart = 16.dp)
                        )
                        .border(
                            width = 1.dp,
                            color = Color.Transparent, // Viền nhẹ màu xám nhạt
                            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 80.dp, bottomEnd = 0.dp, bottomStart = 16.dp)
                        )
                        .padding(8.dp) // Padding bên trong Box
                ) {

                    Column(
                        modifier = Modifier.padding(10.dp,20.dp)
                    ) {
//                        Icon(
//                            imageVector = Icons.Default.Star,
//                            contentDescription = "Favorite",
//                            tint = Color("#5AB2FF".toColorInt()),
//                            modifier = Modifier // Đặt icon ở góc dưới bên phải
//                                .padding(0.dp,0.dp,0.dp,10.dp) // Padding 10dp
//                                .size(24.dp)
//                        )
                        // Tiêu đề
                        Text(
                            text = menuItem.title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        // Mô tả
                        Text(
                            text = menuItem.description,
                            fontSize = 14.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                }

                // Box chứa hình ảnh và trái tim
                Box(
                    modifier = Modifier
                        .fillMaxWidth().padding(end = 10.dp), // Kích thước Box
                    contentAlignment = Alignment.TopCenter // Căn hình ảnh ở trên
                ) {
                    // Hình ảnh
                    Image(
                        painter = painterResource(id = menuItem.imageRes),
                        contentDescription = null,
                        modifier = Modifier
                            .size(90.dp)
                    )

                    // Trái tim ở góc dưới bên phải
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = Color.Red,
                        modifier = Modifier
                            .align(Alignment.BottomEnd) // Đặt icon ở góc dưới bên phải
                            .padding(0.dp,0.dp,10.dp,10.dp) // Padding 10dp
                            .size(24.dp)
                            .offset(y = 15.dp, x= 10.dp)
                    )
                }
            }
        }
    }
}