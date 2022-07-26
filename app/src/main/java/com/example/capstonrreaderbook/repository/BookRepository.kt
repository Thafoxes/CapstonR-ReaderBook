package com.example.capstonrreaderbook.repository

import android.util.Log
import com.example.capstonrreaderbook.data.DataOrException
import com.example.capstonrreaderbook.data.Resource
import com.example.capstonrreaderbook.model.Item
import com.example.capstonrreaderbook.model.MBook
import com.example.capstonrreaderbook.network.BooksApi
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class BookRepository @Inject constructor(private val api: BooksApi){


    suspend fun getAllBooks(searchQuery: String): Resource<List<Item>> {
        return try{

            Resource.Loading(data = true)
            val itemList = api.getAllBooks(searchQuery).items

            if(!itemList.isNullOrEmpty()) Resource.Loading(false)

            Resource.Success(data = itemList)
        }catch (error: Exception){
            Log.d("error", "${error}")
            Resource.Error(message = error.message.toString())

        }

    }

    suspend fun getBookById(query: String): Resource<Item>{
        return try{

            Resource.Loading(data = true)
            val itemList = api.getSpecificBooks(query)

            Resource.Loading(false)

            Resource.Success(data = itemList)
        }catch (error: Exception){
            Log.d("error", "${error}")
            Resource.Error(message = error.message.toString())

        }

    }



}

/*
* deprecated
*
* suspend fun getBooks(searchQuery: String):
            DataOrException<List<Item>, Boolean, Exception>{
        try{

            dataOrException.loading = true
            dataOrException.data = api.getAllBooks(searchQuery).items

            if(!dataOrException.data.isNullOrEmpty()){
                dataOrException.loading = false

            }
        }catch (error: Exception){
            dataOrException.e = error

        }

        return dataOrException
    }
* */
