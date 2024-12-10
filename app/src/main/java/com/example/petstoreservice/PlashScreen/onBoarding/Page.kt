package com.example.petstoreservice.PlashScreen.onBoarding
import androidx.annotation.DrawableRes
import com.example.petstoreservice.R

data class Page(
    val title: String,
    val description: String,
    @DrawableRes val img : Int,
)

val pages = listOf(
    Page(
        title = "Записи онлайн-мониторинга здоровья",
        description = "Приложение поможет вам и ветеринарным центрам быстро и точно отслеживать состояние здоровья вашего питомца",
        img = R.drawable.onboarding1
    ),
    Page(
        title = "Спланируйте меню и научную диету",
        description = "Приложение поможет вам и ветеринарным центрам быстро и точно отслеживать состояние здоровья вашего питомца",
        img = R.drawable.onboarding2
    ),
    Page(
        title = "Управляйте хранением продуктов в доме",
        description = "Приложение поможет вам и ветеринарным центрам быстро и точно отслеживать состояние здоровья вашего питомца",
        img = R.drawable.onboarding3
    ),
    Page(
        title = "Ветеринарные услуги в радиусе 1 км.",
        description = "Приложение поможет вам и ветеринарным центрам быстро и точно отслеживать состояние здоровья вашего питомца.",
        img = R.drawable.img_cat_study
    ),
    Page(
        title = "Быстрая покупка и доставка",
        description = "Приложение поможет вам и ветеринарным центрам быстро и точно отслеживать состояние здоровья вашего питомца",
        img = R.drawable.onboarding4
    ),
)