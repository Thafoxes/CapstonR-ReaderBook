package com.example.capstonrreaderbook.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.capstonrreaderbook.R

@Composable
fun SubTitleConfig(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(5.dp),
        style = MaterialTheme.typography.h5,
        color = colorResource(id = R.color.primaryTextColor),
        fontWeight = FontWeight.Bold
    )
}