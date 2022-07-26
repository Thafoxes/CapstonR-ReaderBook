package com.example.capstonrreaderbook.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.capstonrreaderbook.R
import com.example.capstonrreaderbook.model.MBook
import com.example.capstonrreaderbook.model.getEmptyBookData
import com.example.capstonrreaderbook.screens.main.RoundedBookTrendingIcon


@Composable
fun BookCardTest(bookData: MBook = getEmptyBookData(), onClick : () -> Unit = {}) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable(
                onClick = onClick
            ),
        elevation = 5.dp,
        shape = RoundedCornerShape(23.dp),
    ) {

        Box(modifier = Modifier.padding(0.dp)) {
            Column {
                Box(
                    modifier = Modifier.height(height = 150.dp),
                ){
                    Image(
                        //painter = rememberImagePainter(data = ""),

                        painter =
                        if(bookData.bookThumbnail != null){
                            painterResource(id = bookData.bookThumbnail!!)
//                           rememberImagePainter(bookData.volumeInfo.imageLinks.thumbnail)
                        }else{
                            painterResource(id = R.drawable.see_more)
                        },
                        contentDescription = "book for ${bookData.bookAuthor}",
                        modifier = Modifier.fillMaxHeight(),
                        contentScale = ContentScale.FillHeight,

                        )

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


                    }

                }
            }
        }


    }
}