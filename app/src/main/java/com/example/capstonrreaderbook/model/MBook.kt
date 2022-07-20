package com.example.capstonrreaderbook.model

import com.example.capstonrreaderbook.R

data class MBook (
    var id: String? = "",
    var bookTitle: String? = "",
    var bookAuthor: String? = "",
    var bookDescription: String? = "nothing to say",
    var publisher: String? = "",
    var ISBN: String? = "",
    var bookPublishedDate: Long? = null,
    var publicRating: Int? = null,
    var isTrending: Boolean? = false,
    var bookThumbnail: Int? = R.drawable.see_more,
    var notBook: Boolean = false,
    var genre: List<String> = emptyList()


    )

fun getEmptyBookData(bookTitle: String? = "Browse more?"): MBook{
    return MBook(
        id = "",
        bookTitle = bookTitle,
        bookThumbnail = R.drawable.see_more,
        bookDescription = "",
        publisher = "",
        ISBN = "",
        bookPublishedDate = 0L,
        publicRating = null,
        isTrending = false,
        notBook = true

        )
}
fun getMBookData(): List<MBook>{
    return listOf(
        MBook(
            id = "1",
            bookTitle = "C++ for dummies",
            bookAuthor = "Dummies",
            publisher = "Mayor bick",
            bookDescription = "a programming language tutorial book for dummies",
            publicRating = 4,
            isTrending = false,
            bookThumbnail = R.drawable.bookthumbnails,
            ISBN = null,
            bookPublishedDate = null
        ),
        MBook(
            id = "2",
            bookTitle = "A journey to the moon",
            bookAuthor = "Tommy Cooper",
            publisher = "Kids publishing house",
            bookDescription = "Discover the space with Tommy Cooper",
            publicRating = 4,
            bookThumbnail = R.drawable.bookthumbnails,
            isTrending = true,
            ISBN = null,
            bookPublishedDate = null
        ),
        MBook(
            id = "3",
            bookTitle = "A journey to the moon and the sun with the jupiter",
            bookAuthor = "Tommy Cooper van de linder Heil Mayor",
            publisher = "Kids publishing house",
            bookDescription = "Discover the space with Tommy Cooper",
            publicRating = 4,
            bookThumbnail = R.drawable.bookthumbnails,
            isTrending = true,
            ISBN = null,
            bookPublishedDate = null
        ),
        MBook(
            id = "4",
            bookTitle = "A journey to the moon lorem ipsum to me the king of what the hell",
            bookAuthor = "Tommy Cooper",
            publisher = "Kids publishing house",
            bookDescription = "Discover the space with Tommy Cooper",
            publicRating = 4,
            bookThumbnail = R.drawable.bookthumbnails,
            isTrending = null,
            ISBN = null,
            bookPublishedDate = null
        ),
        MBook(
            id = "5",
            bookTitle = "A journey to the moon",
            bookAuthor = "Tommy Cooper",
            publisher = "Kids publishing house",
            bookDescription = "Discover the space with Tommy Cooper",
            publicRating = 4,
            bookThumbnail = R.drawable.bookthumbnails,
            isTrending = null,
            ISBN = null,
            bookPublishedDate = null
        ),
        MBook(
            id = "6",
            bookTitle = "A journey to the moon",
            bookAuthor = "Tommy Cooper",
            publisher = "Kids publishing house",
            bookDescription = "Discover the space with Tommy Cooper",
            publicRating = 4,
            bookThumbnail = R.drawable.bookthumbnails,
            isTrending = false,
            ISBN = null,
            bookPublishedDate = null
        ),

        )
}