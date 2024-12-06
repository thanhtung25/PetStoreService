package com.example.petstoreservice.PlashScreen.Home.HomeScreen

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.petstoreservice.PlashScreen.Home.HomeScreen.SubContainer.DiscoverProduct
import com.example.petstoreservice.PlashScreen.Home.HomeScreen.SubContainer.FoodNotice
import com.example.petstoreservice.PlashScreen.Home.HomeScreen.SubContainer.MenuManagement
import com.example.petstoreservice.PlashScreen.Home.HomeScreen.SubContainer.PetStatus
import com.example.petstoreservice.PlashScreen.LoginRegister.getUserId
import com.example.petstoreservice.PlashScreen.Model.PetsViewModel
import com.example.petstoreservice.PlashScreen.Model.ProductsViewModel
import com.example.petstoreservice.PlashScreen.Navigation.NavigationIteam
import com.example.petstoreservice.PlashScreen.common.avatarPet
import com.example.petstoreservice.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen (viewModel: ProductsViewModel = viewModel(),viewModel1: PetsViewModel = viewModel() ,navController: NavHostController){
    val scrollState = rememberScrollState()
    val context = navController.context
    val iduser = getUserId(context)
    println(iduser)
    // Lấy danh sách sản phẩm từ `ViewModel`
    val products by viewModel.products.observeAsState(emptyList())
    // Gọi API khi `Composable` được tạo
    LaunchedEffect(Unit) {
        viewModel.fetchProducts()
    }
    // Lấy sản phẩm đầu tiên từ danh sách nếu tồn tại
    val firstProduct = products.getOrNull(0)

    val pets by viewModel1.pets.observeAsState(emptyList())
    LaunchedEffect(Unit) {
        viewModel1.fetchPets()
    }
    // Lấy thú cưng đầu tiên có `iduser` trùng khớp với `iduser` đăng nhập
    val firstPets = pets.firstOrNull { it.iduser == iduser }

// Kiểm tra xem có giá trị ngày sinh hay không
    val petBirthDateString = firstPets?.petbirthdate?.toString()

// Kiểm tra ngày sinh không null và đúng định dạng
    val petAge = if (petBirthDateString != null && petBirthDateString.isNotEmpty()) {
        try {
            // Định dạng chuỗi ngày sinh
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

            // Chuyển chuỗi thành LocalDate
            val birthDate = LocalDate.parse(petBirthDateString, formatter)

            // Lấy ngày hiện tại
            val currentDate = LocalDate.now()

            // Tính số năm giữa ngày sinh và ngày hiện tại
            ChronoUnit.YEARS.between(birthDate, currentDate).toInt()
        } catch (e: Exception) {
            // Xử lý lỗi nếu định dạng chuỗi ngày không đúng
            println("Định dạng ngày sinh không hợp lệ: $e")
            null // Trả về null nếu có lỗi
        }
    } else {
        null // Trả về null nếu ngày sinh không tồn tại
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color("#A0DEFF".toColorInt()), // Màu xanh trên cùng
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
            onClick = {
                navController.navigate(NavigationIteam.addifprofile.route
                )},
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
        if (firstPets != null) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = firstPets.petname,
                color = Color("#469E67".toColorInt()),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
        }
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,

        ){

            if (firstPets != null) {
                Text(
                    text = firstPets.petgender,
                    color = Color("#469E67".toColorInt()),
                )
            }
            if (firstPets != null) {
                Text(
                    text = "$petAge год",
                    color = Color("#469E67".toColorInt()),
                )
            }
            if (firstPets != null) {
                Text(
                    text = firstPets.petweight,
                    color = Color("#469E67".toColorInt()),
                )
            }
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
                textTitle = "Обед",
                textTime = "12:00",
                text = "На обед приходится 45% калорий в день.",
                colorTransform1 = Color("#FDB456".toColorInt()),
                colorTransform2 = Color("#FEDA7C".toColorInt()),
            )
            MenuManagement(
                image = R.drawable.img_salat,
                textTitle = "Ужин",
                textTime = "18:00",
                text = "На ужин приходится 30% калорий в день.",
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
//@Preview
//@Composable
//fun HoneScreenPreview(){
//    val navController = rememberNavController()
//    HomeScreen(navController )
//}