package com.example.capstonrreaderbook.screens.info

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.capstonrreaderbook.components.ReturnButton

@Composable
fun InfoScreen(navController: NavController){
    Column() {

        ReturnButton(navController)
        Text(text = "Info here")
    }
}