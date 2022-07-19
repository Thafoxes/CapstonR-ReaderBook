package com.example.capstonrreaderbook.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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

@Composable
fun DrawerButton(
    navRoute: () -> Unit = {},
    imageVector: ImageVector,
    iconDescription: String,
    textDescription: String){
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
            .clickable(onClick = navRoute),
        elevation = 5.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(imageVector = imageVector,
                contentDescription = iconDescription,
                modifier = Modifier.padding(end = 13.dp)
            )
            Text(text = textDescription , style = MaterialTheme.typography.h6)

        }
    }

}

@Composable
fun ReturnButton(navController: NavController, onClicked: () -> Unit = { navController.popBackStack() }){
    Row(modifier = Modifier
        .fillMaxWidth(0.5f)
        .clickable (
            onClick = onClicked
        )) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Return icon",
            modifier = Modifier.padding(end = 10.dp),
        )
        Text(text = "Return", style = MaterialTheme.typography.h6)
    }
}
