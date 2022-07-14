package com.example.capstonrreaderbook.screens.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.capstonrreaderbook.model.MUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch

class AuthViewModel:ViewModel() {

    private val auth: FirebaseAuth = Firebase.auth

    private val _loading = MutableLiveData(false)


    fun CreateUser(email: String,
                   userName: String,
                   phoneNumber: String,
                   password: String,
                   goHome: () -> Unit) = viewModelScope.launch{
        if(_loading.value == false){
            _loading.value = true
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    it ->
                    if(it.isSuccessful){
                        Log.d("success", "${it.result.user?.email}")
                        createUser(userName, phoneNumber, email)
                        goHome()
                    }else{
                        Log.d("error", "${it.exception?.localizedMessage}")
                    }
                    _loading.value = false

                }

        }

    }

    //add to database
    private fun createUser(userName: String,
                           phoneNumber: String,
                           email: String) {
        var userID = auth.currentUser?.uid
        val user = MUser(
            id = null,
            uid = userID!!,
            email = email,
            userName = userName,
            phoneNumber = phoneNumber,
            aboutMe = "",
            avatarUrl = "",
            ).toMap()


        FirebaseFirestore.getInstance().collection("users").add(user)

    }

    fun LoginUser(email: String, password: String, goHome: () -> Unit = {}) = viewModelScope.launch{
        try {

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    task -> if(task.isSuccessful){

                    Log.d("alt", "${task.result.user?.email}")
                    goHome()
                }else{

                    Log.d("alt", "${task.exception?.localizedMessage}")

                }
            }.addOnFailureListener{
                    Log.d("alt", "${it.localizedMessage}")

            }
        }catch (ex : Exception){
            Log.d("alt", "${ex}")
        }

    }

}