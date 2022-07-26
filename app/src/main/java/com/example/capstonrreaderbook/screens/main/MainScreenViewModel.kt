package com.example.capstonrreaderbook.screens.main

import androidx.lifecycle.ViewModel
import com.example.capstonrreaderbook.data.Resource
import com.example.capstonrreaderbook.model.MBook
import com.example.capstonrreaderbook.repository.FireRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val repository: FireRepository): ViewModel() {



    suspend fun getAllSavedBook(): Resource<List<MBook>>{

        return repository.getFavBooks()
    }
}