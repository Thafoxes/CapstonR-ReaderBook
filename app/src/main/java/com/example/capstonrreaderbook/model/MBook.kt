package com.example.capstonrreaderbook.model

import com.example.capstonrreaderbook.R
import com.google.firebase.firestore.Exclude
import com.google.firebase.firestore.PropertyName

data class MBook (
    @get:PropertyName("book_id")
    @set:PropertyName("book_id")
    var id: String? = "",

    @get:PropertyName("book_title")
    @set:PropertyName("book_title")
    var bookTitle: String? = "",

    @get:PropertyName("book_author")
    @set:PropertyName("book_author")
    var bookAuthor: String? = "",


    @get:PropertyName("publisher")
    @set:PropertyName("publisher")
    var publisher: String? = "",

    @get:PropertyName("publish_date")
    @set:PropertyName("publish_date")
    var bookPublishedDate: String? = null,

    @get:PropertyName("thumbnail_url")
    @set:PropertyName("thumbnail_url")
    var bookThumbnailURL: String? = null,

    //differentiate the put data into database and local
    @Exclude var bookDescription: String? = "nothing to say",
    @Exclude var bookThumbnail: Int? = R.drawable.see_more,
    @Exclude var notBook: Boolean = false,


    )

fun getEmptyBookData(bookTitle: String? = "Browse more?"): MBook{
    return MBook(
        id = "",
        bookTitle = bookTitle,
        bookThumbnail = R.drawable.see_more,
        bookDescription = "",
        publisher = "",
        bookPublishedDate = "",
        notBook = true

        )
}