package com.example.capstonrreaderbook.repository

import android.util.Log
import com.example.capstonrreaderbook.data.Resource
import com.example.capstonrreaderbook.model.Item
import com.example.capstonrreaderbook.model.MBook
import com.example.capstonrreaderbook.network.BooksApi
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FireRepository @Inject constructor(private val queryBook: Query) {

    suspend fun getFavBooks(): Resource<List<MBook>>{
        return try{


            Resource.Loading(data = true)
            val data = queryBook.get().await().documents.map {
                    documentSnapshot ->  documentSnapshot.toObject(MBook::class.java)!!
            }
            if(data.isEmpty()){
                return Resource.Error(message = "no data found")
            }

            Resource.Loading(false)

            Resource.Success(data)
        }catch (error: Exception){
            Log.d("error", "${error}")
            Resource.Error(message = error.message.toString())

        }

    }
}