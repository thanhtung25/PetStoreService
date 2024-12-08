package com.example.petstoreservice.PlashScreen.Home.BagScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.petstoreservice.PlashScreen.Home.BagScreen.SubContainer.AppbarStorage
import com.example.petstoreservice.PlashScreen.Home.BagScreen.SubContainer.WareHouseProducts
import com.example.petstoreservice.PlashScreen.LoginRegister.getUserId
import com.example.petstoreservice.PlashScreen.Model.ProductMealModel
import com.example.petstoreservice.PlashScreen.Model.Products
import com.example.petstoreservice.PlashScreen.Model.ProductsViewModel
import com.example.petstoreservice.PlashScreen.Model.WasehouseViewModel
import com.example.petstoreservice.PlashScreen.Navigation.NavigationIteam
import com.example.petstoreservice.R
import kotlinx.coroutines.launch

@Composable
fun StorageScreen(
    viewModel: ProductsViewModel = viewModel(),
    viewModel1: WasehouseViewModel = viewModel(),
    productMealModel: ProductMealModel,
    navController: NavHostController,
    clicktostore: () -> Unit,
    mealType: String?
) {
    val scrollState = rememberScrollState()
    val scaffoldState = rememberBottomSheetScaffoldState()
    val products by viewModel.products.observeAsState(emptyList())
    val wareItems by viewModel1.warehouse.observeAsState(emptyList())
    val coroutineScope = rememberCoroutineScope()
    var selectedProduct by remember { mutableStateOf<Products?>(null) }
    // lay iduser
    val context = navController.context
    val iduser = getUserId(context)
    println(iduser)
    // Gọi API khi `Composable` được tạo
    LaunchedEffect(Unit) {
        viewModel.fetchProducts()
        viewModel1.fetchWasehouse()
        viewModel1.fetchProducts()
    }
    // Lọc sản phẩm theo `iduser` và `idproduct`
    val filteredProducts = products.mapNotNull { product ->
        val matchingWarehouse = wareItems.find { wareItem ->
            wareItem.iduser == iduser && wareItem.idproduct == product.idproduct
        }
        matchingWarehouse?.let { Pair(product, it) }
    }

    LaunchedEffect(selectedProduct) {
        selectedProduct?.let { product ->
            coroutineScope.launch {
                scaffoldState.bottomSheetState.expand()
            }

        }
    }
    val userWareItems = wareItems.filter { it.iduser == iduser }
// State hiển thị thông báo sau khi thêm sản phẩm
    var isProductAdded by remember { mutableStateOf(false) }
    // Mảng danh sách sản phẩm từ Model
    if (userWareItems.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            AppbarStorage(viewModel1,{})
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .background(
                        color = Color.White, shape = RoundedCornerShape(size = 20.dp)
                    ).border(1.dp, color = Color.Gray , shape = RoundedCornerShape(size = 20.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column (
                    modifier = Modifier
                        .fillMaxWidth(0.7f)
                        .padding(0.dp, 20.dp, 0.dp, 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ){
                    // Thêm chữ
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "В вашем доме нет товаров",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        textAlign = TextAlign.Center,

                        )
                    TextButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        onClick = {clicktostore() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color("#5AB2FF".toColorInt()),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(size = 10.dp)
                    ) {
                        Text(
                            text = "Перейти в магазин",
                            fontSize = 16.sp,
                        )
                    }
                }
                // Thêm ảnh
                Image(
                    painter = painterResource(id = R.drawable.img_box_cat), // Đổi URL hình ảnh theo ý muốn
                    contentDescription = "Giỏ hàng trống",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }


        }
        // Hiển thị thông báo khi giỏ hàng trống với ảnh và nút "Add Giỏ hàng"

    }
    else{
        BottomSheetScaffold(
            scaffoldState = scaffoldState,
            sheetPeekHeight = 0.dp,
            sheetContent = {
                selectedProduct?.let {product->
                    var weightcalor by remember { mutableStateOf(100) }
                    var productcalor by remember {mutableStateOf(product.calor)  }
                    val idware = viewModel1.warehouse.value?.find { it.idproduct == product.idproduct }?.idware ?: 0
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        IconButton(
                            onClick = {
                                coroutineScope.launch { scaffoldState.bottomSheetState.collapse() }
                                selectedProduct = null
                            },
                            modifier = Modifier
                                .size(20.dp)
                                .align(Alignment.End)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Đóng",
                                tint = Color.Gray // Màu sắc của icon
                            )
                        }
                        Text(
                            text = product.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        Image(
                            painter = rememberAsyncImagePainter(model = product.image_url),
                            contentDescription = null,
                            modifier = Modifier
                                .size(200.dp)
                                .padding(20.dp)// Adjust the size as needed
                        )
                        // Hiển thị chi tiết sản phẩm
                        Text(
                            text = product.description,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(bottom = 8.dp),
                            textAlign = TextAlign.Justify
                        )
                        Row (
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ){

                            IconButton(onClick = {
                                if(weightcalor > 0){
                                    weightcalor -= 50
                                    productcalor -= product.calor / 2
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowLeft,
                                    contentDescription = "Decrease Quantity",
                                    tint = Color.Red
                                )
                            }
                            Text(
                                text = "${weightcalor} грамм",
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                color = Color.Red,
                                modifier = Modifier.padding(10.dp,0.dp)
                            )
                            IconButton(onClick = {
                                if(weightcalor<500){
                                    weightcalor += 50
                                    productcalor += product.calor / 2
                                }
                            }) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowRight,
                                    contentDescription = "Decrease Quantity",
                                    tint = Color.Red

                                )
                            }
                            Text(
                                text = " ${productcalor} Kcal",
                                fontWeight = FontWeight.Normal,
                                fontSize = 16.sp,
                                color = Color.Red,
                                modifier = Modifier.padding(10.dp,0.dp)
                            )

                        }
                        // Nút thêm vào giỏ hàng
                        Button(
                            onClick = {
//                                // add product meal
//                                productMealModel.addbreakfastList(
//                                    product.name,weightcalor,productcalor,idware.toString()
//                                )
//                                // Kiểm tra nếu sản phẩm đã được thêm vào
//                                val productList = productMealModel.breakfastList
//                                isProductAdded = productList.any {
//                                    it.name == product.name && it.weight == weightcalor && it.calor == productcalor
//                                }
//                                if (isProductAdded) {
//                                    // Điều hướng đến màn hình khác nếu thành công
//
//                                    navController.navigate(NavigationIteam.breakfast.route)
//                                }

                                // Ví dụ: Thực hiện các thao tác tùy thuộc vào loại bữa ăn
                                if (mealType == "breakfast") {
                                    // Thêm sản phẩm vào breakfastList
                                    productMealModel.addbreakfastList(product.name,weightcalor,productcalor,idware.toString())
                                    navController.navigate(NavigationIteam.breakfast.route)
                                } else if (mealType == "lunch") {
                                    // Thêm sản phẩm vào lunchList
                                    productMealModel.addlunchList(product.name,weightcalor,productcalor,idware.toString())
                                    navController.navigate(NavigationIteam.lunch.route)
                                }
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text("Добавить в меню")
                        }

                    }
                }
            }


        ) {paddingValues ->
            Column (modifier = Modifier.fillMaxWidth())
            {
                AppbarStorage(viewModel1,onProductClick = { product ->
                    // Khi nhấn vào sản phẩm trong tìm kiếm, cập nhật selectedProduct và mở BottomSheet
                    selectedProduct = product
                })
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .verticalScroll(scrollState)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(30.dp, 20.dp, 30.dp, 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Еда",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                        )
                        Text(
                            text = "Посмотреть больше  ->",
                            fontWeight = FontWeight.Normal,
                            fontSize = 13.sp,
                            color = Color("#5AB2FF".toColorInt())
                        )
                    }

                    filteredProducts.forEach { (product, warehouse) ->
                        WareHouseProducts(
                            product = product,
                            warehouse = warehouse
                        ) {
                            selectedProduct = product
                        }
                    }
                }
            }

        }
    }

}

//@Preview
//@Composable
//fun StorageScreenPreview(){
//    StorageScreen()
//}