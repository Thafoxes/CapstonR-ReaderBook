package com.example.capstonrreaderbook.components

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp

@Composable
fun HyperLinkPage(
    text: String = "No account? Create new one!",
    textAlign: TextAlign = TextAlign.Center,
    color: Color = Color.Blue,
    textDecoration: TextDecoration = TextDecoration.Underline,
    modifier: Modifier = Modifier
        .padding(13.dp)
        .fillMaxWidth(),
    onClick: () -> Unit = {},

){
    Text(text = text,
        modifier = modifier.clickable(onClick = onClick),
        style = MaterialTheme.typography.subtitle1,
        textAlign = textAlign,
        color = color,
        textDecoration = textDecoration
    )
}