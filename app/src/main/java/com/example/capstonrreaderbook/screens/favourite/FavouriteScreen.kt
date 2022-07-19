package com.example.capstonrreaderbook.screens.favourite

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.capstonrreaderbook.components.ReturnButton

@Composable
fun FavouriteScreen(navController: NavController){
    Surface(
        modifier = Modifier
            .fillMaxSize()) {
        Column( modifier = Modifier.fillMaxSize().padding(10.dp)) {
            ReturnButton(navController = navController)

        }
        
    }

}