package com.example.capstonrreaderbook.model

data class MUser(
    val id: String?,
    val uid: String,
    val email: String,
    val userName: String,
    val avatarUrl: String,
    val aboutMe: String,
    val phoneNumber: String

){
    fun toMap():MutableMap<String, Any>{
        return mutableMapOf(
            "user_id" to this.uid,
            "email" to this.email,
            "user_name" to this.userName,
            "avatar_url" to this.avatarUrl,
            "about_me" to this.aboutMe,
            "phone_number" to this.phoneNumber

        )

    }
}
