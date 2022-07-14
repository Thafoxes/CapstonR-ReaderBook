package com.example.capstonrreaderbook.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.capstonrreaderbook.R
import com.example.capstonrreaderbook.ui.theme.titleSize


@Composable
fun AppTopBar(
    modifierTopAppBar: Modifier = Modifier.fillMaxWidth(),
    topAppTitle: String = "Login Here",

    ) {
    TopAppBar(
        modifier = Modifier
            .fillMaxWidth(),
        backgroundColor = colorResource(id = R.color.primaryColor),
        elevation = 3.dp
    ) {

        Text(
            text = topAppTitle,
            modifier = Modifier.padding(start = 5.dp),
            color = colorResource(id = R.color.primaryTextColor),
            style =  TextStyle(
                textDecoration = TextDecoration.None,
            ),
            textAlign = TextAlign.Start,
            fontSize = titleSize

        )

    }

}