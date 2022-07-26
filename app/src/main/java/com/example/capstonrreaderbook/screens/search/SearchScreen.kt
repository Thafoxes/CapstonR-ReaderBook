package com.example.capstonrreaderbook.screens.search

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.capstonrreaderbook.components.OutlineTextFieldInputs
import com.example.capstonrreaderbook.components.ReturnButton
import com.example.capstonrreaderbook.model.Item
import com.example.capstonrreaderbook.navigation.ReaderScreens
import com.example.capstonrreaderbook.tools.CheckImageExist
import java.util.*


@Composable
fun SearchScreen(
    navController: NavController,
    viewModel: BookSearchViewModel = hiltViewModel()
) {


    Surface(modifier = Modifier.fillMaxSize()) {
        val textState = rememberSaveable {
            mutableStateOf("")
        }
        val bookList = viewModel.bookList
        val keyboardOptions = LocalFocusManager.current


        Column(
            modifier = Modifier
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
                onAction = KeyboardActions(onDone = {
                    //action when done
                    keyboardOptions.clearFocus()
                    val string = textState.value.replace(" ", "+").lowercase(Locale.getDefault())
                    viewModel.SearchBooks(bookQuery = string)
                    textState.value = ""

                })
            )
            Divider(modifier = Modifier.padding(top = 12.dp, bottom = 12.dp))



            if (viewModel.isLoading) {

                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth(0.1f)
                            .fillMaxHeight(0.1f)
                    )

                }

            } else {


                LazyColumn(
                    modifier = Modifier
                        .padding(2.dp)
                ) {

                    items(bookList) { index ->

                        SearchResultBookBar(index) {
                            Log.d("bookid", "${index.id} selected")
                            navController.navigate(ReaderScreens.DetailScreen.name + "/${index.id}" )
                        }
                    }
                }

            }


        }


    }


}

@Composable
fun SearchResultBookBar(item: Item, onClick: () -> Unit = {}) {

    //query book here
    val imageThumbnail = CheckImageExist(item)
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .clickable(onClick = onClick),
        elevation = 5.dp
    ) {
        Row(modifier = Modifier.height(100.dp)) {
            Image(
                painter = rememberImagePainter(imageThumbnail),
                contentDescription = "book title",
                modifier = Modifier.padding(3.dp),
                contentScale = ContentScale.Fit,
            )

            //title section here
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(start = 10.dp, top = 10.dp)
            ) {
                Text(
                    text = "${item.volumeInfo.title}",
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,

                        ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text =
                    if (!item.volumeInfo.authors.isNullOrEmpty()) {


                        buildAnnotatedString {
                            append("by: ")
                            item.volumeInfo.authors.forEach {
                                append(it + ", ")
                            }
                        }
                    } else {
                        buildAnnotatedString {
                            append("by:")
                        }

                    },
                    style = TextStyle(
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,

                        ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                if (!item.volumeInfo.averageRating.isNaN() || item.volumeInfo.averageRating.isFinite()) {
                    Text(
                        text = buildAnnotatedString {
                            append("rating: ")
                            append("${item.volumeInfo.averageRating}")
                            append(" out of 5 ⭐")
                        },
                        style = TextStyle(
                            color = Color.Black,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 12.sp,

                            ),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }


            }

        }


    }

}
