package com.example.capstonrreaderbook.model

data class GoogleBookApi(
    val items: List<Item>,
    val kind: String,
    val totalItems: Int
)