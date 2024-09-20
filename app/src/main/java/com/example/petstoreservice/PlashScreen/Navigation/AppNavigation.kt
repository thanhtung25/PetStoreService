package com.example.petstoreservice.PlashScreen.Navigation


enum class Screen{
    HOME,
    LOGIN,
    ONBOARDING,
    SIGNUP,
    ADDPROFILESR,
    ADDIMGPROFILE,
    ADDIFPROFILE,
}
sealed class NavigationIteam(val route: String) {
    object Onboarding : NavigationIteam(Screen.ONBOARDING.name)
    object login : NavigationIteam(Screen.LOGIN.name)
    object signup : NavigationIteam(Screen.SIGNUP.name)
    object Home : NavigationIteam(Screen.HOME.name)
    object addprofilesr : NavigationIteam(Screen.ADDPROFILESR.name)
    object addimgprofile : NavigationIteam(Screen.ADDIMGPROFILE.name)
    object addifprofile : NavigationIteam(Screen.ADDIFPROFILE.name)
}