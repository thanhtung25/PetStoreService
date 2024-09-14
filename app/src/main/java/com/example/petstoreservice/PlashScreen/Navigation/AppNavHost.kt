package com.example.petstoreservice.PlashScreen.Navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.NavHostController
import com.example.petstoreservice.PlashScreen.LoginRegister.LoginScreen
import com.example.petstoreservice.PlashScreen.LoginRegister.RegisterScreen
import com.example.petstoreservice.PlashScreen.onBoarding.onBoardingScreen

@Composable
fun AppNacHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationIteam.Onboarding.route
    ){
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ){
        composable(NavigationIteam.Home.route) {

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
    }
}