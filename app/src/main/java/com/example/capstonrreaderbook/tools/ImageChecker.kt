package com.example.capstonrreaderbook.tools

import android.util.Log
import com.example.capstonrreaderbook.model.Item

fun CheckImageExist(bookData: Item): String {
    return if (!bookData.volumeInfo.readingModes.image) {
        "https://images.unsplash.com/photo-1541963463532-d68292c34b19?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=80&q=80"
    } else {
//        Log.d("image", "${bookData.volumeInfo.imageLinks.thumbnail }")
        bookData.volumeInfo.imageLinks.thumbnail
    }
}