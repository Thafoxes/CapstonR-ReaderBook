package com.example.capstonrreaderbook.screens.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun MainScreen(navController: NavController){
    
    Surface(
        modifier = Modifier
            .fillMaxSize(),

        ) {
        
        Text(text = "Main page")
        
    }

}