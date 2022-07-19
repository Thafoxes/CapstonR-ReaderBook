package com.example.capstonrreaderbook.tools

import java.sql.Date

fun UnixDateToString(date: Long): String{
    val sdf = java.text.SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val date = java.util.Date(date * 1000)
    sdf.format(date)
    return sdf.toString()
}

//might crash due to compatibility issue
fun convertISO_Instant(date: Long): String{
    return java.time.format.DateTimeFormatter.ISO_INSTANT
        .format(java.time.Instant.ofEpochSecond(date))

}