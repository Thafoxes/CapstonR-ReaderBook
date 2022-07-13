package com.example.capstonrreaderbook.navigation

enum class ReaderScreens {
    SplashScreen,
    LoginScreen,
    RegisterScreen,
    MainScreen,
    SettingScreen,
    DetailScreen,
    UpdateScreen,
    ReaderStatsScreen;

    companion object {
        fun fromRoute(route: String): ReaderScreens = when (route?.substringBefore("/")){
            SplashScreen.name -> SplashScreen
            LoginScreen.name -> LoginScreen
            RegisterScreen.name -> RegisterScreen
            MainScreen.name -> MainScreen
            SettingScreen.name -> SettingScreen
            DetailScreen.name -> DetailScreen
            UpdateScreen.name -> UpdateScreen
            ReaderStatsScreen.name -> ReaderStatsScreen
            null -> MainScreen
            else -> throw IllegalArgumentException("Route $route is not recognised!")
        }
    }

}