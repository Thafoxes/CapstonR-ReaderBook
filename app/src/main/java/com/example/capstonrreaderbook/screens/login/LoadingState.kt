package com.example.capstonrreaderbook.screens.login

sealed class LoadingState(val outputString: String? = null){
    object idle: LoadingState()
    data class success(val username: String? = null): LoadingState(outputString = username)
    class failed(val failed: String? = null): LoadingState(outputString = failed)

}

