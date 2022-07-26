package com.example.capstonrreaderbook.screens.search

import android.util.Log
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstonrreaderbook.data.DataOrException
import com.example.capstonrreaderbook.data.Resource
import com.example.capstonrreaderbook.model.Item
import com.example.capstonrreaderbook.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookSearchViewModel
@Inject constructor(private val bookRepository: BookRepository):
    ViewModel() {

    var bookList: List<Item> by mutableStateOf(emptyList())
    var favBookList: List<Item> by mutableStateOf(emptyList())
    var isLoading: Boolean by mutableStateOf(true)


    init{
        SearchBooks("popular")
    }

    fun SearchBooks(bookQuery: String) {

        viewModelScope.launch{
            isLoading = true



            if(bookQuery.isEmpty()){
                return@launch
            }

            try{
                when(val response = bookRepository.getAllBooks(bookQuery)){
                    is Resource.Success -> {
                        bookList = response.data!!
                        if(!bookList.isNullOrEmpty()){
                            isLoading = false
                        }
                    }
                    is Resource.Error -> {
                        isLoading = false
                        Log.d("error", "error on ${response.message}")
                    }
                    else -> {
                        isLoading = false
                    }
                }

            }catch (error: Exception){

                Log.d("errorViewModel", "${error}")

            }



        }
    }



}


//deprecated
/*
*
                listOfBooks.value.loading = true
                listOfBooks.value = bookRepository.getBooks(bookQuery)

                if (!listOfBooks.value.data.isNullOrEmpty()) {
                    listOfBooks.value.loading = false
                }
                Log.d("error", "${listOfBooks.value.e}")
*
* */