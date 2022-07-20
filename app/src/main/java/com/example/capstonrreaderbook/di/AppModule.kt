package com.example.capstonrreaderbook.di

import com.example.capstonrreaderbook.network.BooksApi
import com.example.capstonrreaderbook.utilities.Constants
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
    fun ProvidesBookApi(): BooksApi{
        return Retrofit
            .Builder()
            .baseUrl(Constants.GOOGLE_BOOK_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(BooksApi::class.java )

    }
}