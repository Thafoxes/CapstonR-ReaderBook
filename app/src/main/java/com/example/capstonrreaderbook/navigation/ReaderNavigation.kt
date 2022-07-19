package com.example.capstonrreaderbook.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.capstonrreaderbook.screens.SearchScreen.SearchScreen
import com.example.capstonrreaderbook.screens.details.DetailScreen
import com.example.capstonrreaderbook.screens.favourite.FavouriteScreen
import com.example.capstonrreaderbook.screens.forgotpwd.ForgotPasswordScreen
import com.example.capstonrreaderbook.screens.info.InfoScreen
import com.example.capstonrreaderbook.screens.login.LoginScreen
import com.example.capstonrreaderbook.screens.main.MainScreen
import com.example.capstonrreaderbook.screens.registeration.*
import com.example.capstonrreaderbook.screens.setting.SettingScreen
import com.example.capstonrreaderbook.screens.splashscreen.SplashScreen


@Composable
fun ReaderNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ReaderScreens.SplashScreen.name){

        composable(route = ReaderScreens.SplashScreen.name){
            SplashScreen(navController = navController)
        }

        composable(route = ReaderScreens.LoginScreen.name){
            LoginScreen(navController = navController)
        }

        composable(route = ReaderScreens.RegisterScreen.name){
            SignUpScreen(navController = navController)
        }

        composable(route = ReaderScreens.MainScreen.name){
            MainScreen(navController = navController)
        }

        composable(route = ReaderScreens.SettingScreen.name){
            SettingScreen(navController = navController)
        }

        composable(route = ReaderScreens.DetailScreen.name){
            DetailScreen(navController = navController)
        }

        composable(route = ReaderScreens.ForgotPasswordScreen.name){
            ForgotPasswordScreen(navController = navController)
        }

        composable(route = ReaderScreens.SearchScreen.name){
            SearchScreen(navController = navController)
        }

        composable(route = ReaderScreens.InfoScreen.name){
            InfoScreen(navController = navController)
        }

        composable(route = ReaderScreens.FavouriteScreen.name){
            FavouriteScreen(navController = navController)
        }



    }
}