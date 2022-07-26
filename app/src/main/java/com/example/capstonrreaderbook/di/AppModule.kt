package com.example.capstonrreaderbook.di

import com.example.capstonrreaderbook.network.BooksApi
import com.example.capstonrreaderbook.repository.BookRepository
import com.example.capstonrreaderbook.repository.FireRepository
import com.example.capstonrreaderbook.utilities.Constants
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun ProvideBookRepository(api: BooksApi) = BookRepository(api)

    @Singleton
    @Provides
    fun ProvideFirebookRepository() = FireRepository(
        queryBook = FirebaseFirestore
            .getInstance()
            .collection("books"),

            )

    @Singleton
    @Provides
    fun ProvidesBookApi(): BooksApi{
        return Retrofit
            .Builder()
            .baseUrl(Constants.GOOGLE_BOOK_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BooksApi::class.java )

    }
}