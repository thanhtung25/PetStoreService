package com.example.petstoreservice.PlashScreen.Home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.petstoreservice.PlashScreen.Home.BagScreen.StorageScreen
import com.example.petstoreservice.PlashScreen.Home.FoodScreen.FoodScreen
import com.example.petstoreservice.PlashScreen.Home.HomeScreen.HomeScreen
import com.example.petstoreservice.PlashScreen.Home.PayScreen.PayScreen
import com.example.petstoreservice.PlashScreen.Home.StoreScreen.StoreScreen
import com.example.petstoreservice.PlashScreen.Navigation.NavigationIteam
import com.example.petstoreservice.R

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CustomBottomBar (navHostController: NavHostController,initialIndex: Int = 0 ){
    var selectedIndex by remember { mutableStateOf(initialIndex) }
    val selectedColor = Color.White// Color for selected state
    val unselectedColor = Color.Black // Color for unselected state
    val iconBackgroundColor = Color("#5AB2FF".toColorInt())

    // Sử dụng Scaffold để bố cục màn hình
    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor =Color.White ,
                elevation = 8.dp,
                modifier = Modifier.navigationBarsPadding()
            ) {
                val items = listOf(
                    Pair("Home", R.drawable.ic_home),
                    Pair("Cart", R.drawable.ic_shopping_cart),
                    Pair("Store", R.drawable.ic_store),
                    Pair("Food", R.drawable.ic_food),
                    Pair("Bag", R.drawable.ic_bag)
                )

                // Create a BottomNavigationItem for each item
                items.forEachIndexed { index, item ->
                    BottomNavigationItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = item.second),
                                contentDescription = item.first,
                                modifier = Modifier.size(30.dp).padding(0.dp,0.dp,0.dp,5.dp),
                                tint = Color.Unspecified
                            )
                        },

                        label = {
                            Text(
                                text = item.first,
                                color = if (selectedIndex == index) selectedColor else unselectedColor,
                                fontSize = 14.sp,
                            )
                        },
                        selected = selectedIndex == index,
                        onClick = {
                            selectedIndex = index
                                  },
                        // Wrap the entire BottomNavigationItem with a Box for full background coverage
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                if (selectedIndex == index) iconBackgroundColor else Color.Transparent
                            )
                            .padding(5.dp), // Adjust padding to match your design
                        selectedContentColor = Color.Unspecified,
                        unselectedContentColor = Color.Unspecified
                    )
                }
            }
        },
        content = { paddingValues ->
            // Nội dung chính của màn hình ở đây
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when (selectedIndex) {
                    2 -> StoreScreen(navController = navHostController, onclickCart = {selectedIndex = 1})
                    1 -> PayScreen(navController = navHostController, clicktostore = {selectedIndex = 2})
                    0 -> HomeScreen(navController = navHostController)
                    3 -> FoodScreen()
                    4 -> StorageScreen(navController = navHostController,clicktostore = {selectedIndex = 2})
                }
            }
        }
    )
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun CustomBottomBarPreview() {
    val navController = rememberNavController()
    CustomBottomBar(navController)
}
