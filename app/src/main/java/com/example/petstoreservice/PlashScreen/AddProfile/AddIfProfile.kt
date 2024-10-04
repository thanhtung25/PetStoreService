package com.example.petstoreservice.PlashScreen.AddProfile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.RadioButton
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.test.services.storage.file.PropertyFile.Column
import com.example.petstoreservice.PlashScreen.common.NewsIconsButton
import com.example.petstoreservice.PlashScreen.common.NewsTextField
import com.example.petstoreservice.PlashScreen.common.avatarPet
import com.example.petstoreservice.R

@Composable
fun AddIfProfile(navController: NavHostController) {
    val scrollState = rememberScrollState()

    var namePet by remember { mutableStateOf("") }
    var breedPet by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    var weightPet by remember { mutableStateOf("") }
    var selectedGender by remember { mutableStateOf("Đực") }
    var nutritionOptions = remember { mutableStateMapOf("Khô" to false, "Ấm" to false, "Bán ấm" to false, "Nhà nấu" to false, "Tươi sống" to false) }

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
            ).padding(10.dp)
    ){
    }
    Column(
        modifier = Modifier.padding(16.dp).verticalScroll(scrollState),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceEvenly
    ){
        Column(
            modifier = Modifier.fillMaxWidth().fillMaxHeight(fraction = 0.4f).padding(20.dp,5.dp) ,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ){
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Thong tin thu cung",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            avatarPet()

        }
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                modifier = Modifier.padding(20.dp, 5.dp),
                text = "Ten thu cung",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            NewsTextField(
                label = "Name Pet",
                placeholder = "Nhập Name Pet" ,
                text = namePet,
                onTextChange = {newText ->
                    namePet = newText
                },
            )
            Text(
                modifier = Modifier.padding(20.dp, 5.dp),
                text = "Giong thu cung",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            NewsTextField(
                label = "Breed Pet",
                placeholder = "Meo anh long ngan" ,
                text = breedPet,
                onTextChange = {newText ->
                    breedPet = newText
                },
            )
            Text(
                modifier = Modifier.padding(20.dp, 5.dp),
                text = "Ngay sinh thu cung",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            NewsTextField(
                label = "Date of Birth",
                placeholder = "10/10/2002" ,
                text = dateOfBirth,
                onTextChange = {newText ->
                    breedPet = newText
                },
            )
            Text(
                modifier = Modifier.padding(20.dp, 5.dp),
                text = "Can nang thu cung",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            NewsTextField(
                label = "Weight Pet",
                placeholder = "5 kg" ,
                text = weightPet,
                onTextChange = {newText ->
                    weightPet = newText
                },
            )
            Text(
                modifier = Modifier.padding(20.dp, 5.dp),
                text = "Can nang thu cung",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(20.dp,0.dp)
            ) {
                RadioButton(
                    selected = selectedGender == "Đực",
                    onClick = { selectedGender = "Đực" }
                )
                Text(text = "Đực", modifier = Modifier.padding(end = 16.dp))

                RadioButton(
                    selected = selectedGender == "Cái",
                    onClick = { selectedGender = "Cái" }
                )
                Text(text = "Cái")
            }
            Text(
                modifier = Modifier.padding(20.dp, 5.dp),
                text = "Dinh duong cho thu cung",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(10.dp).horizontalScroll(rememberScrollState())

            ) {
                nutritionOptions.forEach { (label, isChecked) ->
                    // Tạo ô checkbox với text
                    Box(
                        modifier = Modifier
                            .background(
                                color = if (isChecked) Color(0xFFE0E0E0) else Color.Transparent,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(8.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Checkbox(
                                checked = isChecked,
                                onCheckedChange = { isChecked ->
                                    nutritionOptions[label] = isChecked
                                }
                            )
                            Text(text = label)
                        }
                    }
                }
            }

            Button(
                onClick = { /* Xử lý khi nhấn nút */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50)), // Màu xanh cho button
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Lưu thông tin", color = Color.White, fontSize = 16.sp)
            }
        }
    }
}



@Preview
@Composable
fun AddIfProfilePreview() {
    val navController = rememberNavController()
    AddIfProfile(navController)
}