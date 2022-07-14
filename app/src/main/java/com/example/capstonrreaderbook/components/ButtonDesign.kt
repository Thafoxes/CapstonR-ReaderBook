package com.example.capstonrreaderbook.components

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.capstonrreaderbook.R

@Composable
fun ButtonLogin(
    buttonText: String = "Log in",
    modifier: Modifier = Modifier
        .fillMaxWidth(0.9f)
        .padding(5.dp),
    onClick: () -> Unit = {},

){

    Button(
        onClick = onClick,
        modifier = modifier,
        shape = RoundedCornerShape(5.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = colorResource(id = R.color.primaryLightColor)
        )

    ) {
        Text(text = buttonText, color = colorResource(id = R.color.primaryTextColor))
    }


}
