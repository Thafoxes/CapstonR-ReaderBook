package com.example.capstonrreaderbook.screens.details

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Icon
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookOnline
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.capstonrreaderbook.R
import com.example.capstonrreaderbook.components.ReturnButton
import com.example.capstonrreaderbook.data.Resource
import com.example.capstonrreaderbook.model.Item
import com.example.capstonrreaderbook.model.MBook
import com.example.capstonrreaderbook.screens.search.BookSearchViewModel
import com.example.capstonrreaderbook.tools.CheckImageExist
import com.google.firebase.firestore.FirebaseFirestore

@SuppressLint("ProduceStateDoesNotAssignValue")
@Composable
fun DetailScreen(
    navController: NavController,
    viewModel: DetailViewModel = hiltViewModel(),
    bookId: String){

    val bookInfo = produceState<Resource<Item>>(initialValue = Resource.Loading()){
        value = viewModel.getBookInfo(bookId = bookId)

    }.value

    Log.d("state", "is loading $bookId")
    when(bookInfo){
        is Resource.Loading -> {
            LoadingScreen()
        }
        is Resource.Success -> {
            ShowBookDetails(navController, bookInfo.data!!)
        }
        is Resource.Error ->{
            Log.d("state", "got error")
            Text("Error loading ${bookInfo.message}")
        }

    }





}

@Composable
fun ShowBookDetails(navController: NavController, bookData: Item) {

    val context = LocalContext.current
    Surface(modifier = Modifier.fillMaxSize()) {
        val uriHandler = LocalUriHandler.current

        Scaffold(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            topBar = {

                ReturnButton(navController)
            }
        ) {
            LazyColumn{
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ImageTop(bookData)
                        TitleAndAuthor(bookData)
                        BookDescription(bookData)

                        Divider()
                        BookType(
                            textOne = "Language: ${bookData.volumeInfo.language}",
                            textTwo = "Maturity rating: ${bookData.volumeInfo.maturityRating}")

                        Divider()
                        ButtonInfo(
                            bookLink = bookData.volumeInfo.infoLink,
                            buttonText = "More Info About This Book",
                            icon = Icons.Default.Info
                        ){
                            uriHandler.openUri(bookData.volumeInfo.infoLink)
                        }
                        ButtonInfo(
                            bookLink = bookData.volumeInfo.previewLink,
                            buttonText = "Preview Now",
                            icon = Icons.Default.BookOnline
                        ){
                            uriHandler.openUri(bookData.volumeInfo.infoLink)
                        }
                        ButtonInfo(
                            bookLink = "dsada",
                            buttonText = "Save Book",
                            icon = Icons.Default.Save
                        ){
                            val book = MBook(
                                id = bookData.id,
                                bookTitle = bookData.volumeInfo.title,
                                bookAuthor = bookData.volumeInfo.authors[0],
                                publisher = bookData.volumeInfo.publisher,
                                bookPublishedDate = bookData.volumeInfo.publishedDate,
                                bookThumbnailURL = CheckImageExist(bookData),

                            )

                            savetoDatabase(
                                book = book,
                                navController = navController,
                                context = context)

                        }


                    }

                }
            }



        }

    }
}


fun savetoDatabase(book: MBook, navController: NavController, context: Context) {

    val db = FirebaseFirestore.getInstance()
    val dbCollection = db.collection("books")

    if(book.toString().isNotEmpty()){
        dbCollection.add(book).addOnSuccessListener {
            documentRef ->
            val docId = documentRef.id
            dbCollection.document(docId)
                .update(hashMapOf("id" to docId) as Map<String, Any>)
                .addOnCompleteListener {
                    Toast.makeText(context, "Saved successfully", Toast.LENGTH_SHORT).show()
                    navController.popBackStack()
                }
                .addOnFailureListener {
                    Log.d("error", "error : $it")
                    Toast.makeText(context, "Error saving book", Toast.LENGTH_SHORT).show()
                }
        }
    }else{

    }


}

@Composable
fun BookType(textOne: String, textTwo: String) {
    Row(
        modifier = Modifier
            .padding(top = 12.dp, bottom = 12.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = textOne,
            style = MaterialTheme.typography.subtitle1
        )
        Text(
            text = textTwo,
            style = MaterialTheme.typography.subtitle1
        )
    }
}

@Composable
private fun ButtonInfo(
    bookLink: String,
    buttonText: String,
    icon: ImageVector,
    onclick: () -> Unit = {}
) {

    Row(modifier = Modifier
        .padding(12.dp)
        .fillMaxWidth(0.9f),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "More Info: ", modifier = Modifier.padding(start = 12.dp))
        if (!bookLink.isNullOrEmpty()) {
            Button(
                onClick = onclick,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = colorResource(id = R.color.primaryLightColor),
                        contentColor = colorResource(id = R.color.primaryTextColor)
                    )
                    ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = buttonText
                    )
                    Text(text = buttonText)

                }

        } else {
            Text(text = "No info about this book")
        }
    }
}

@Composable
fun BookDescription(bookData: Item) {

    Divider(modifier = Modifier.padding(top = 23.dp), thickness = 0.dp)
    Text(text = "Description", style = MaterialTheme.typography.h5)
    Divider()
    Text(text =
        if (!bookData.volumeInfo.description.isNullOrEmpty()) {
            bookData.volumeInfo.description
        }else {
            "No Description available"
        }
        , modifier = Modifier.padding(3.dp))

}

@Composable
fun TitleAndAuthor(bookData: Item) {
    Text(
        text = bookData.volumeInfo.title,
        style = TextStyle(
            color = Color.Black,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
        ),
        textAlign = TextAlign.Center
    )
    if (!bookData.volumeInfo.authors.isNullOrEmpty()) {
        Text(
            text = buildAnnotatedString {
                append("by ")
                bookData.volumeInfo.authors.forEach {
                    append("$it, ")
                }

            },
            style = TextStyle(
                color = Color.Black,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
            ),
            textAlign = TextAlign.Center
        )
    }
    Text(text = "published on ${bookData.volumeInfo.publishedDate}" , style = MaterialTheme.typography.subtitle1)
}

@Composable
fun ImageTop(bookData: Item) {


        Image(
            painter = rememberImagePainter(data = CheckImageExist(bookData)),
            contentDescription = "book thumbnail",
            modifier = Modifier
                .height(250.dp)
                .padding(12.dp)
                .border(
                    border = BorderStroke(0.5.dp, Color.Black),
                    shape = RectangleShape
                ),
            contentScale = ContentScale.Crop
        )

}

@Composable
fun LoadingScreen() {
    Surface(
        modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            CircularProgressIndicator()

        }
    }
}
