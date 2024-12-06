package com.example.petstoreservice.PlashScreen.Home.PayScreen.SubContainer

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.petstoreservice.PlashScreen.LoginRegister.getUserId
import com.example.petstoreservice.PlashScreen.Model.CartViewModel
import com.example.petstoreservice.PlashScreen.Model.Products
import com.example.petstoreservice.PlashScreen.Model.cart
import com.example.petstoreservice.R
import kotlinx.coroutines.launch

@Composable
fun ProductItem(
    userCartItem : cart,
    product: Products,
    loadCartItems: () -> Unit,
    onUpdateTotal: (Int) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }
    var quantity by remember { mutableStateOf(userCartItem.quantity) }
    val coroutineScope = rememberCoroutineScope()
    var updateCartMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var isSelected by remember { mutableStateOf(true) }
    // Gọi hàm onUpdateTotal để cập nhật tổng tiền ngay khi khởi tạo
    LaunchedEffect(Unit) {
        onUpdateTotal(product.price * quantity)

    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp , 10.dp)
            .height(100.dp)
            .background(
                Color.White,
                shape = RoundedCornerShape(20.dp
                )
            ).border(1.dp, Color.Gray.copy(alpha = 0.5f), shape = RoundedCornerShape(20.dp
            )),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        // Radio button
        RadioButton(
            modifier = Modifier.padding(7.dp,0.dp,0.dp,0.dp),
            selected = isSelected,
            onClick = {
                isSelected = !isSelected
            },
            colors = RadioButtonDefaults.colors(selectedColor = Color.Black)
        )

        // Product Image
        Image(
            painter = rememberImagePainter(data = product.image_url),
            contentDescription = null,
            modifier = Modifier
                .size(64.dp)
                .clip(RoundedCornerShape(8.dp))
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            // Product Name
            Text(text = product.name, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)

            // Product Price
            Text(
                text = product.price.toString() + " рублей ",
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
            )
        }

        // Quantity Controller (Decrease - Quantity - Increase)
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                if (quantity > 1) {
                    quantity--
                    onUpdateTotal(-product.price)
                    //coroutineScope.launch { scaffoldState.bottomSheetState.collapse() }
                    // Xử lý thêm sản phẩm vào giỏ hàng ở đây
                    coroutineScope.launch {
                        isLoading = true
                        try {
                            // Log giá trị idcart và quantity để kiểm tra
                            Log.d("UpdateCart", "Updating idcart: ${userCartItem.idcart}, new quantity: $quantity")

                            val response = RetrofitClient.instance.updateQuantity(userCartItem.idcart, quantity)
                            if (response.success) {
                                updateCartMessage = "update Successful: ${response.message}"
                                Log.d("UpdateCart", "Success: ${response.message}")
                            } else {
                                updateCartMessage = "update Failed: ${response.message}"
                                Log.e("UpdateCart", "Failed: ${response.message}")
                            }
                        } catch (e: Exception) {
                            updateCartMessage = "Error: ${e.message}"
                            Log.e("UpdateCart", "Error: ${e.message}")
                        } finally {
                            isLoading = false
                        }
                    }
                }
            }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = "Decrease Quantity"
                )
            }

            // Quantity Text
            Text(text = quantity.toString())

            IconButton( onClick = {
                quantity++
                onUpdateTotal(product.price)
                //coroutineScope.launch { scaffoldState.bottomSheetState.collapse() }
                // Xử lý thêm sản phẩm vào giỏ hàng ở đây
                coroutineScope.launch {
                    isLoading = true
                    try {
                        // Log giá trị idcart và quantity để kiểm tra
                        Log.d("UpdateCart", "Updating idcart: ${userCartItem.idcart}, new quantity: $quantity")

                        val response = RetrofitClient.instance.updateQuantity(userCartItem.idcart, quantity)
                        if (response.success) {
                            updateCartMessage = "update Successful: ${response.message}"
                            Log.d("UpdateCart", "Success: ${response.message}")
                        } else {
                            updateCartMessage = "update Failed: ${response.message}"
                            Log.e("UpdateCart", "Failed: ${response.message}")
                        }
                    } catch (e: Exception) {
                        updateCartMessage = "Error: ${e.message}"
                        Log.e("UpdateCart", "Error: ${e.message}")
                    } finally {
                        isLoading = false
                    }
                }
            }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Increase Quantity"
                )
            }
        }
        IconButton(
            onClick = {
                showDialog = true
            },
            modifier = Modifier.padding(0.dp,0.dp,7.dp,0.dp),
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Remove Product",
                tint = Color.Black
            )
        }

        // Hộp thoại xác nhận
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text(text = "Подтвердить удаление") },
                text = { Text(text = "Вы уверены, что хотите удалить этот товар из корзины?") },
                confirmButton = {
                    Button(onClick = {
                        showDialog = false // Đóng hộp thoại
                        coroutineScope.launch {
                            isLoading = true
                            try {
                                // Gọi API để xóa sản phẩm khỏi giỏ hàng
                                val response = RetrofitClient.instance.deletecart(userCartItem.idcart)
                                if (response.success) {
                                    updateCartMessage = "Product removed successfully."
                                    loadCartItems()
                                    onUpdateTotal(-product.price * quantity)
                                    Log.d("DeleteCart", "Success: ${response.message}")
                                } else {
                                    updateCartMessage = "Failed to remove product: ${response.message}"
                                    Log.e("DeleteCart", "Failed: ${response.message}")
                                }
                            } catch (e: Exception) {
                                updateCartMessage = "Error: ${e.message}"
                                Log.e("DeleteCart", "Error: ${e.message}")
                            } finally {
                                isLoading = false
                            }

                        }
                    }) {
                        Text("Удалить")
                    }
                },
                dismissButton = {
                    Button(onClick = { showDialog = false }) {
                        Text("Отмена")
                    }
                }
            )
        }
    }
}
@Preview
@Composable
fun ProductItemPreview() {
    // Dữ liệu mẫu cho cart item
    val dummyCartItem = cart(
        idcart = 1,
        iduser = 1,
        idproduct = 1,
        quantity = 2
    )

    // Dữ liệu mẫu cho product
    val dummyProduct = Products(
        idproduct = 1,
        name = "Sample Product",
        price = 1200,
        image_url = "https://bit.ly/3NjOMEM",
        type = "",
        weight = 100,
        calor = 100,
        brand = "",
        description = "",
        expiry_date = ""
    )

    ProductItem(
        userCartItem = dummyCartItem,
        product = dummyProduct,
        loadCartItems= {},
        {}
    )
}
