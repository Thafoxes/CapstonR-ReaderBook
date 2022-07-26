package com.example.capstonrreaderbook.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstonrreaderbook.data.Resource
import com.example.capstonrreaderbook.model.Item
import com.example.capstonrreaderbook.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel
@Inject constructor(private val repository: BookRepository):
    ViewModel() {

    suspend fun getBookInfo(bookId: String): Resource<Item>{

        return repository.getBookById(bookId)
    }
}