package com.example.petstoreservice.PlashScreen.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.petstoreservice.PlashScreen.AddProfile.AddIfProfile
import com.example.petstoreservice.PlashScreen.AddProfile.AddImgProFile
import com.example.petstoreservice.PlashScreen.AddProfile.AddProfileSreen
import com.example.petstoreservice.PlashScreen.Home.CustomBottomBar
import com.example.petstoreservice.PlashScreen.Home.FoodScreen.DietScreen.Diet1
import com.example.petstoreservice.PlashScreen.Home.FoodScreen.DietScreen.Diet2
import com.example.petstoreservice.PlashScreen.Home.FoodScreen.DietScreen.Diet3
import com.example.petstoreservice.PlashScreen.Home.FoodScreen.DietScreen.Diet4
import com.example.petstoreservice.PlashScreen.Home.FoodScreen.DietScreen.Diet5
import com.example.petstoreservice.PlashScreen.Home.FoodScreen.Meal.breakfast
import com.example.petstoreservice.PlashScreen.Home.FoodScreen.Meal.lunch
import com.example.petstoreservice.PlashScreen.Home.PayScreen.OderConfirmScreen
import com.example.petstoreservice.PlashScreen.LoginRegister.LoginScreen
import com.example.petstoreservice.PlashScreen.LoginRegister.RegisterScreen
import com.example.petstoreservice.PlashScreen.Model.ProductMealModel
import com.example.petstoreservice.PlashScreen.onBoarding.onBoardingScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNacHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationIteam.Onboarding.route
    //startDestination: String = NavigationIteam.login.route
    ){
    var mealType by remember {  mutableStateOf("") }
    var selectedIndex by remember { mutableStateOf(0) }
    val productMealModel: ProductMealModel = viewModel()
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination

    ){
        composable(NavigationIteam.Home.route) {
            CustomBottomBar(
                navController,
                productMealModel = productMealModel,
                initialIndex = selectedIndex,
                mealType = mealType,
            )
        }
        composable(NavigationIteam.Onboarding.route) {
            onBoardingScreen(navController)
        }
        composable(NavigationIteam.login.route) {
            LoginScreen(navController)
        }
        composable(NavigationIteam.signup.route) {
            RegisterScreen(navController)
        }
        composable(NavigationIteam.addprofilesr.route) {
            AddProfileSreen(navController)
        }
        composable(NavigationIteam.addimgprofile.route) {
            AddImgProFile(navController)
        }
        composable(NavigationIteam.addifprofile.route) {
            AddIfProfile(navController)
        }
        composable(NavigationIteam.oderconfirmscreen.route) {
            OderConfirmScreen(
                navController,
                onNavigateToCart = {
                    selectedIndex = 1 // Chuyển tab về Cart
                    navController.navigate(NavigationIteam.Home.route)
                })
        }
        composable(NavigationIteam.diet1.route) {
            Diet1(
                navController,
                productMealModel = productMealModel,
                onNavigateToCart = {
                    selectedIndex = 3 // Chuyển tab về Cart
                    navController.navigate(NavigationIteam.Home.route)
                })

        }
        composable(NavigationIteam.diet2.route) {
            Diet2()
        }
        composable(NavigationIteam.diet3.route) {
            Diet3()
        }
        composable(NavigationIteam.diet4.route) {
            Diet4()
        }
        composable(NavigationIteam.diet5.route) {
            Diet5()
        }
        composable(NavigationIteam.breakfast.route) {
            breakfast(
                navController,
                productMealModel = productMealModel,
                onNavigateToCart = {
                    mealType = "breakfast"
                    selectedIndex = 4 // Chuyển tab về Cart
                    navController.navigate(NavigationIteam.Home.route)
                },
            )
        }
        composable(NavigationIteam.lunch.route) {
            lunch (
                navController,
                productMealModel = productMealModel,
                onNavigateToCart = {
                    mealType = "lunch"
                    selectedIndex = 4 // Chuyển tab về Cart
                    navController.navigate(NavigationIteam.Home.route)
                },
            )
        }
    }
}