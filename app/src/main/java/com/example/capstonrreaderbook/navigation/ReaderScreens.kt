package com.example.capstonrreaderbook.navigation

enum class ReaderScreens {
    SplashScreen,
    LoginScreen,
    RegisterScreen,
    FavouriteScreen,
    MainScreen,
    SettingScreen,
    DetailScreen,
    UpdateScreen,
    ForgotPasswordScreen,
    SearchScreen,
    InfoScreen,
    ReaderStatsScreen;

    companion object {
        fun fromRoute(route: String): ReaderScreens = when (route?.substringBefore("/")){
            SplashScreen.name -> SplashScreen
            LoginScreen.name -> LoginScreen
            FavouriteScreen.name -> FavouriteScreen
            RegisterScreen.name -> RegisterScreen
            MainScreen.name -> MainScreen
            SettingScreen.name -> SettingScreen
            DetailScreen.name -> DetailScreen
            UpdateScreen.name -> UpdateScreen
            InfoScreen.name -> InfoScreen
            ForgotPasswordScreen.name -> ForgotPasswordScreen
            ReaderStatsScreen.name -> ReaderStatsScreen
            SearchScreen.name -> SearchScreen
            null -> MainScreen
            else -> throw IllegalArgumentException("Route $route is not recognised!")
        }
    }

}