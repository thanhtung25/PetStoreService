package com.example.petstoreservice.PlashScreen.Navigation


enum class Screen{
    HOME,
    LOGIN,
    ONBOARDING,
    SIGNUP,
    ADDPROFILESR,
    ADDIMGPROFILE,
    ADDIFPROFILE,
    ODERCONFIRMSCREEN,
    DIET1,
    DIET2,
    DIET3,
    DIET4,
    DIET5,
}
sealed class NavigationIteam(val route: String) {
    object Onboarding : NavigationIteam(Screen.ONBOARDING.name)
    object login : NavigationIteam(Screen.LOGIN.name)
    object signup : NavigationIteam(Screen.SIGNUP.name)
    object Home : NavigationIteam(Screen.HOME.name)
    object addprofilesr : NavigationIteam(Screen.ADDPROFILESR.name)
    object addimgprofile : NavigationIteam(Screen.ADDIMGPROFILE.name)
    object addifprofile : NavigationIteam(Screen.ADDIFPROFILE.name)
    object  oderconfirmscreen: NavigationIteam(Screen.ODERCONFIRMSCREEN.name)
    object diet1:NavigationIteam(Screen.DIET1.name)
    object diet2:NavigationIteam(Screen.DIET1.name)
    object diet3:NavigationIteam(Screen.DIET1.name)
    object diet4:NavigationIteam(Screen.DIET1.name)
    object diet5:NavigationIteam(Screen.DIET1.name)
}