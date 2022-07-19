package com.example.capstonrreaderbook.screens.SearchScreen

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.capstonrreaderbook.components.AppTopBar
import com.example.capstonrreaderbook.components.OutlineTextFieldInputs
import com.example.capstonrreaderbook.components.ReturnButton

@Composable
fun SearchScreen(navController: NavController){
    Surface( modifier = Modifier.fillMaxSize()) {
        val textState = rememberSaveable{
            mutableStateOf("")
        }


        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
           ) {
            ReturnButton(navController)

            OutlineTextFieldInputs(
                textState = textState,
                labelTextField = "Search",
                placeholderTextField = "Search books",
                icon = Icons.Default.Search,
                iconDescription = "Search icon",
                imeAction = ImeAction.Done,
                onAction = KeyboardActions( onDone = {
                    //action when done
                    Log.d("test", "${textState.value}")
                })
            )

        }

    }

}