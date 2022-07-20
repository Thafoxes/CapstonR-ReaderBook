package com.example.capstonrreaderbook.screens.SearchScreen

import android.graphics.fonts.FontStyle
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capstonrreaderbook.R
import com.example.capstonrreaderbook.components.AppTopBar
import com.example.capstonrreaderbook.components.OutlineTextFieldInputs
import com.example.capstonrreaderbook.components.ReturnButton
import com.example.capstonrreaderbook.model.getMBookData

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
            Divider(modifier = Modifier.padding(top = 12.dp, bottom = 12.dp))
            LazyColumn(
                modifier = Modifier
                    .padding(2.dp)){

                items(9){
                    SearchResultBookBar(textState.value)
                }

            }

        }

    }

}

@Composable
fun SearchResultBookBar(bookTitle: String) {

    //query book here
    val book = getMBookData()[0]
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        elevation = 5.dp
        ) {
       Row( modifier = Modifier.height(100.dp)) {
           Image(
               painter = painterResource(id = R.drawable.bookthumbnails),
               contentDescription = "book title",
               modifier = Modifier.padding(3.dp),
               contentScale = ContentScale.FillHeight,
           )

           //title section here
           Column(modifier = Modifier
               .fillMaxWidth()
               .fillMaxHeight()
               .padding(start = 10.dp, top = 10.dp)){
               Text(
                   text = "${book.bookTitle}",
                   style = TextStyle(
                       color = Color.Black,
                       fontWeight = FontWeight.Bold,
                       fontSize = 14.sp,

                       ),
                   maxLines = 1,
                   overflow = TextOverflow.Ellipsis
               )
               Text(
                   text = "by ${book.bookAuthor}",
                   style = TextStyle(
                       color = Color.Black,
                       fontWeight = FontWeight.SemiBold,
                       fontSize = 12.sp,

                       ),
                   maxLines = 1,
                   overflow = TextOverflow.Ellipsis
               )

           }
       }

        
    }

}
