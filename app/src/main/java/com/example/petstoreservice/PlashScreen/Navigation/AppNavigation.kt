package com.example.petstoreservice.PlashScreen.Navigation


enum class Screen{
    HOME,
    LOGIN,
    ONBOARDING,
    SIGNUP,
}
sealed class NavigationIteam(val route: String) {
    object Onboarding : NavigationIteam(Screen.ONBOARDING.name)
    object login : NavigationIteam(Screen.LOGIN.name)
    object signup : NavigationIteam(Screen.SIGNUP.name)
    object Home : NavigationIteam(Screen.HOME.name)

}