package com.example.capstonrreaderbook.screens.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capstonrreaderbook.R
import com.example.capstonrreaderbook.components.DrawerButton
import com.example.capstonrreaderbook.components.SubTitleConfig
import com.example.capstonrreaderbook.model.MBook
import com.example.capstonrreaderbook.model.getEmptyBookData
import com.example.capstonrreaderbook.model.getMBookData
import com.example.capstonrreaderbook.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@Composable
fun MainScreen(navController: NavController){
    val emailUser = if(!FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()){
        FirebaseAuth.getInstance().currentUser?.email
    }else{
        "Guest"
    }
    val userName = remember{
        "Jason"
    }
    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()
    val backgroundColor = colorResource(id = R.color.primaryColor)

        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            scaffoldState = scaffoldState,

            topBar = {
                TopAppBar(navController, userName, backgroundColor, scope, scaffoldState)
            },
            drawerContent = {
                DrawerContent(navController = navController, currentUser = emailUser!!)
            },

        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                color = colorResource(id = R.color.primaryColor),

            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    color = colorResource(id = R.color.secondaryColor),
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp, bottomStart = 10.dp, bottomEnd = 10.dp)) {

                    Column( modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)) {
                        LazyColumn{
                            item {
                                //type of text
                                SubTitleConfig("Discover new books")
                                val bookData = getMBookData()
                                LazyRow{
                                    items(bookData.size){
                                        index ->
                                        BookCard(bookData[index])
                                    }
                                    item {
                                        BookCard(getEmptyBookData())
                                    }

                                }
                                Divider(modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                                    thickness = 1.dp,
                                    color = colorResource(id =R.color.primaryTextColor)
                                )
                                SubTitleConfig("Continue your reading")
                                LazyRow{
                                   items(bookData.size-2){
                                       index -> BookCard(bookData[index])
                                   }
                                    item {
                                       BookCard(getEmptyBookData(bookTitle = "history"))
                                    }
                                }
                            }
                        }


                    }

                }

            }
        }


}


@Preview
@Composable
fun BookCard(bookData: MBook = getMBookData()[0], onClick : () -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                if (bookData.notBook) {
                    Log.d("book", "browser more book")
                } else {

                    Log.d("book", "got book ${bookData.bookTitle}")
                }
            },
        elevation = 5.dp,
        shape = RoundedCornerShape(23.dp),
    ) {

        Box(modifier = Modifier.padding(0.dp)) {
            Column {
                Box(
                    modifier = Modifier.height(height = 250.dp),
                    ){
                    Image(
                        //painter = rememberImagePainter(data = ""),

                        painter = painterResource(id =
                        if(bookData.bookThumbnail != null){
                            bookData.bookThumbnail!!
                        }else{
                            R.drawable.see_more
                        }),
                        contentDescription = "book for ${bookData.bookTitle!!}",
                        modifier = Modifier.fillMaxHeight(),
                        contentScale = ContentScale.FillHeight,

                    )
                    if(bookData.isTrending == true){
                        RoundedBookTrendingIcon()
                    }
                }

                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .width(100.dp),
                    contentAlignment = Alignment.BottomStart,
                ) {
                    Column {
                        Text(
                            text = if(!bookData.bookTitle.isNullOrEmpty()){
                                bookData.bookTitle!!
                            }else{
                                 ""
                                 },
                            modifier = Modifier,
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Text(
                            text = if(!bookData.bookAuthor.isNullOrEmpty()){
                                "by ${bookData.bookAuthor!!}"
                            }else{
                                 ""
                            },
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 12.sp,
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Text(
                            text = if(bookData.publicRating != null){
                                "rating: ${bookData.publicRating!!} out of 5⭐"
                            }else{
                                 ""
                                 },
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 10.sp,
                            )
                        )

                    }

                }
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun RoundedBookTrendingIcon(
    label: String = "Trending",
    color: Color = colorResource(id = R.color.primaryLightColor),
    radius : Int = 39
){
    Surface(
        modifier = Modifier
            .clip(RoundedCornerShape(
                bottomEndPercent = radius,
                topStartPercent = radius)),
        color = color,
        ) {

        Column(modifier = Modifier
            .width(90.dp)
            .padding(3.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
            ) {
            Text(text = label,
                color = colorResource(id = R.color.black),
                fontWeight = FontWeight.Bold)

        }

    }


}


@Composable
fun DrawerContent(
    navController: NavController,
                  currentUser: String) {

    Text(
        text = "Main menu",
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(15.dp)
        )
    Text(
        text = "Email: $currentUser",
        style = MaterialTheme.typography.caption,
        modifier = Modifier.padding(15.dp)
    )
    Divider()
    DrawerButton(
        navRoute = {
            navController.navigate(ReaderScreens.FavouriteScreen.name)
        },
        imageVector = Icons.Filled.Star,
        iconDescription = "Favourites",
        textDescription = "Favourites"
    )
    DrawerButton(
        navRoute = {
            navController.navigate(ReaderScreens.InfoScreen.name)
        },
        imageVector = Icons.Filled.BookOnline,
        iconDescription = "Browse screen",
        textDescription = "Browse book"
    )
    DrawerButton(
        navRoute = {
           navController.navigate(ReaderScreens.InfoScreen.name)
        },
        imageVector = Icons.Filled.Info,
        iconDescription = "About us screen",
        textDescription = "About us"
    )
    DrawerButton(
        navRoute = {
            Log.d("test", "Log out")
            //todo here
        },
        imageVector = Icons.Filled.Logout,
        iconDescription = "Log out button",
        textDescription = "Log out"
    )



}

@Composable
fun TopAppBar(
    navController: NavController,
    userName: String,
    backgroundColor: Color,
    drawerScope: CoroutineScope,
    scaffoldState: ScaffoldState,
) {
    //scaffold drawer can be seen here https://developer.android.com/jetpack/compose/layouts/material#drawers
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = backgroundColor,
    ) {

        Column(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(5.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.Top
            ) {

                Text(
                    text = "Welcome back, $userName",
                    style = MaterialTheme.typography.h5,
                    modifier = Modifier.padding(top = 15.dp),
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.primaryTextColor)
                )

                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search for book",
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .size(30.dp)
                        .clickable {
                            navController.navigate(ReaderScreens.SearchScreen.name)
                            // use for search screen and stuff
                        },
                    tint = colorResource(id = R.color.primaryTextColor),
                )
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu",
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .size(30.dp)
                        .clickable {
                            //drawer launcher
                            drawerScope.launch {
                                scaffoldState.drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        },
                    tint = colorResource(id = R.color.primaryTextColor)
                )

            }
        }
    }
}
