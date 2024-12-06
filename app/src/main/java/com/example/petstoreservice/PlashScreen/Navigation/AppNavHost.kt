package com.example.petstoreservice.PlashScreen.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.petstoreservice.PlashScreen.AddProfile.AddIfProfile
import com.example.petstoreservice.PlashScreen.AddProfile.AddImgProFile
import com.example.petstoreservice.PlashScreen.AddProfile.AddProfileSreen
import com.example.petstoreservice.PlashScreen.Home.CustomBottomBar
import com.example.petstoreservice.PlashScreen.Home.FoodScreen.Diet1
import com.example.petstoreservice.PlashScreen.Home.FoodScreen.Diet2
import com.example.petstoreservice.PlashScreen.Home.FoodScreen.Diet3
import com.example.petstoreservice.PlashScreen.Home.FoodScreen.Diet4
import com.example.petstoreservice.PlashScreen.Home.FoodScreen.Diet5
import com.example.petstoreservice.PlashScreen.Home.PayScreen.OderConfirmScreen
import com.example.petstoreservice.PlashScreen.LoginRegister.LoginScreen
import com.example.petstoreservice.PlashScreen.LoginRegister.RegisterScreen
import com.example.petstoreservice.PlashScreen.onBoarding.onBoardingScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNacHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationIteam.Onboarding.route
    //startDestination: String = NavigationIteam.login.route
    ){
    var selectedIndex by remember { mutableStateOf(0) }
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination

    ){
        composable(NavigationIteam.Home.route) {
            CustomBottomBar(
                navController,
                initialIndex = selectedIndex,
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
            Diet1()
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
    }
}