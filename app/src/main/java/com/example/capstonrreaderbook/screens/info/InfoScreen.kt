package com.example.capstonrreaderbook.screens.info

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.capstonrreaderbook.R
import com.example.capstonrreaderbook.components.ReturnButton

@Composable
fun InfoScreen(navController: NavController){
    Column(modifier = Modifier.fillMaxSize().padding(12.dp)) {

        ReturnButton(navController)
        Text(text = stringResource(id = R.string.Info))
    }
}