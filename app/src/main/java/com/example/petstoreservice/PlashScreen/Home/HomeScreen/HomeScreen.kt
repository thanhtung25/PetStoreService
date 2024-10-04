package com.example.petstoreservice.PlashScreen.Home.HomeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.petstoreservice.PlashScreen.Home.HomeScreen.SubContainer.DiscoverProduct
import com.example.petstoreservice.PlashScreen.Home.HomeScreen.SubContainer.FoodNotice
import com.example.petstoreservice.PlashScreen.Home.HomeScreen.SubContainer.MenuManagement
import com.example.petstoreservice.PlashScreen.Home.HomeScreen.SubContainer.PetStatus
import com.example.petstoreservice.PlashScreen.Model.ProductsViewModel
import com.example.petstoreservice.PlashScreen.common.avatarPet
import com.example.petstoreservice.R

@Composable
fun HomeScreen (viewModel: ProductsViewModel = viewModel()){
    val scrollState = rememberScrollState()

    // Lấy danh sách sản phẩm từ `ViewModel`
    val products by viewModel.products.observeAsState(emptyList())
    // Gọi API khi `Composable` được tạo
    LaunchedEffect(Unit) {
        viewModel.fetchProducts()
    }
    // Lấy sản phẩm đầu tiên từ danh sách nếu tồn tại
    val firstProduct = products.getOrNull(0)

    // Kiểm tra xem sản phẩm có tồn tại không và truyền nó vào `Composable` khác



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFB2FF59), // Màu xanh trên cùng
                        Color.White // Màu trắng phía dưới
                    ),
                    startY = 0f,
                    endY = 1000f // Điều chỉnh độ dài của gradient
                )
            )
            .padding(10.dp)

    ){

    }
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TextButton(
            modifier = Modifier
                .align(Alignment.End)
                .padding(0.dp,5.dp,10.dp,0.dp),
            onClick = {  },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color("#469E67".toColorInt()).copy(alpha = 0.5f),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(size = 6.dp)
        ) {
            Text(
                text = "Добавить профиль",
                fontSize = 16.sp,
                color = Color.White
            )
        }
        avatarPet()
        Text(
            modifier = Modifier.padding(10.dp),
            text = "Cun pho mai que",
            color = Color("#469E67".toColorInt()),
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
        )
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,

        ){

            Text(
                text = "мальчик",
                color = Color("#469E67".toColorInt()),
            )
            Text(
                text = "2 года",
                color = Color("#469E67".toColorInt()),
            )
            Text(
                text = "5 кг",
                color = Color("#469E67".toColorInt()),
            )
        }
        PetStatus()
        Row (
            modifier = Modifier
                .padding(30.dp, 20.dp)
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ){
            MenuManagement()
            MenuManagement(
                image = R.drawable.img_fastfood,
                textTitle = "Bua Trua",
                textTime = "12:00",
                text = "Bua trua chiem 45% luong calo / 1 ngay",
                colorTransform1 = Color("#FDB456".toColorInt()),
                colorTransform2 = Color("#FEDA7C".toColorInt()),
            )
            MenuManagement(
                image = R.drawable.img_salat,
                textTitle = "Bua toi",
                textTime = "18:00",
                text = "Bua toi chiem 30% luong calo / 1 ngay",
                colorTransform1 = Color("#968BFF".toColorInt()),
                colorTransform2 = Color("#D2A9FF".toColorInt())
            )
        }
        FoodNotice()
        firstProduct?.let { product ->
            DiscoverProduct(product = product)
        }
    }
}
@Preview
@Composable
fun HoneScreenPreview(){
    HomeScreen( )
}