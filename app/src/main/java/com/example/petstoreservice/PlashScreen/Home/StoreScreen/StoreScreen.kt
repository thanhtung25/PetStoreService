package com.example.petstoreservice.PlashScreen.Home.StoreScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.petstoreservice.PlashScreen.Home.StoreScreen.SubContainer.Products
import com.example.petstoreservice.PlashScreen.Home.StoreScreen.SubContainer.AppbarStore
import com.example.petstoreservice.PlashScreen.LoginRegister.getUserId
import com.example.petstoreservice.PlashScreen.Model.CartViewModel
import com.example.petstoreservice.PlashScreen.Model.Products
import com.example.petstoreservice.PlashScreen.Model.ProductsViewModel
import com.example.petstoreservice.PlashScreen.Model.cart
import com.example.petstoreservice.PlashScreen.Navigation.NavigationIteam
import kotlinx.coroutines.launch

@Composable
fun StoreScreen (
    viewModel: ProductsViewModel = viewModel(),
    viewModel1: CartViewModel = viewModel(),
    navController: NavHostController,
    onclickCart: ()-> Unit
){
    val scrollState = rememberScrollState()
    val scaffoldState = rememberBottomSheetScaffoldState()
    val products by viewModel.products.observeAsState(emptyList())
    val cartItems by viewModel1.cart.observeAsState(emptyList())
    val coroutineScope = rememberCoroutineScope()
    var selectedProduct by remember { mutableStateOf<Products?>(null) }
    var cartCount by remember { mutableStateOf(0) }
    // lay iduser
    val context = navController.context
    val iduser = getUserId(context)
    println(iduser)
    // Gọi API khi `Composable` được tạo
    LaunchedEffect(Unit) {
        viewModel.fetchProducts()
        viewModel1.fetchCart()
    }

    // Khi giỏ hàng thay đổi, tính tổng số lượng sản phẩm và gán vào cartCount
    LaunchedEffect(cartItems) {
        // Lọc các mục giỏ hàng theo iduser
        val userCartItems = cartItems.filter { it.iduser == iduser }
        // Tính tổng số lượng sản phẩm trong giỏ của người dùng hiện tại
        cartCount = userCartItems.size
        println("Filtered Cart Items: $userCartItems") // Kiểm tra xem có lọc đúng không
        println("Cart Count: $cartCount") // Kiểm tra số lượng sản phẩm trong giỏ
    }
    LaunchedEffect(selectedProduct) {
        selectedProduct?.let {
            coroutineScope.launch {
                scaffoldState.bottomSheetState.expand()
            }
        }
    }

    // Api Add Product to Cart
    var addCartMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetPeekHeight = 0.dp,
        sheetContent = {
            selectedProduct?.let {product->
                Column(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    IconButton(
                        onClick = {
                            coroutineScope.launch { scaffoldState.bottomSheetState.collapse() }
                            selectedProduct = null
                        },
                        modifier = Modifier.size(20.dp).align(Alignment.End)
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
                            .size(200.dp).padding(20.dp)// Adjust the size as needed
                    )
                    // Hiển thị chi tiết sản phẩm
                    Text(
                        text = product.description,
                        fontSize = 14.sp,
                        modifier = Modifier.padding(bottom = 8.dp),
                        textAlign = TextAlign.Justify
                    )
                    // Hiển thị giá
                    Text(
                        text = "Цена: ${product.price} RUB",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Red,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    // Nút thêm vào giỏ hàng
                    Button(
                        onClick = {

                            coroutineScope.launch { scaffoldState.bottomSheetState.collapse() }
                            // Xử lý thêm sản phẩm vào giỏ hàng ở đây
                            isLoading = true
                            coroutineScope.launch {
                                try {
                                    // Gọi API add cart
                                    val response = RetrofitClient.instance.addcart(iduser, product.idproduct)
                                    if (response.success) {
                                        viewModel1.fetchCart()
                                        addCartMessage = "add Successful: ${response.message}"
                                    } else {
                                        addCartMessage = "add Failed: ${response.message}"
                                    }
                                } catch (e: Exception) {
                                    addCartMessage = "Error: ${e.message}"
                                } finally {
                                    isLoading = false
                                }
                            }
                            viewModel1.fetchCart()
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Добавить в корзину")
                    }
                }
            }
        }


    ) {paddingValues ->
        Column (modifier = Modifier.fillMaxWidth())
        {
            AppbarStore(viewModel,cartCount, onclickCart,onProductClick = { product ->
                // Khi nhấn vào sản phẩm trong tìm kiếm, cập nhật selectedProduct và mở BottomSheet
                selectedProduct = product
            })
            Column(
                modifier = Modifier.fillMaxWidth().fillMaxHeight().verticalScroll(scrollState)
            ) {

                Text(
                    modifier = Modifier.fillMaxWidth().padding(30.dp,10.dp),
                    text = "Список номинаций",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
                Row (
                    modifier = Modifier
                        .padding(30.dp, 10.dp, 0.dp, 20.dp)
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ){
                    products.forEach { product ->
                        Products(product = product) {
                            // Khi sản phẩm được nhấp, cập nhật `selectedProduct` và mở Bottom Sheet
                            //coroutineScope.launch {scaffoldState.bottomSheetState.expand()}
                            selectedProduct = product
                        }
                    }
                }
                Text(
                    modifier = Modifier.fillMaxWidth().padding(30.dp,10.dp),
                    text = "Доступная еда",
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                )
                val chunkedProducts = products.chunked(3)

                // Hiển thị sản phẩm theo nhóm hàng
                chunkedProducts.forEach { rowProducts ->
                    Row(
                        modifier = Modifier
                            .horizontalScroll(rememberScrollState())
                            .padding(30.dp,10.dp), // Thêm padding nếu cần
                        horizontalArrangement = Arrangement.SpaceEvenly,
                    ) {
                        rowProducts.forEach { product ->
                            Products(product = product) {
                                // Khi sản phẩm được nhấp, cập nhật `selectedProduct` và mở Bottom Sheet
                                selectedProduct = product
                                //coroutineScope.launch { scaffoldState.bottomSheetState.expand() }
                            }
                        }
                    }
                }
            }
        }

    }


}