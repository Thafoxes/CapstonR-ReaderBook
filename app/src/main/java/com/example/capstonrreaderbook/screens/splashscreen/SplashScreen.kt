package com.example.capstonrreaderbook.screens.splashscreen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.capstonrreaderbook.R
import com.example.capstonrreaderbook.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavController){
    val scale = remember{
        //for some reason one of the import is having clause with animatable
        androidx.compose.animation.core.Animatable(0f)
    }
    LaunchedEffect(key1 = true){
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                delayMillis = 500,
                easing =

                    LinearOutSlowInEasing
                )
        )
        delay(1000L)

        if(FirebaseAuth.getInstance().currentUser?.email.isNullOrEmpty()) {
            navController.navigate(ReaderScreens.LoginScreen.name) {
                popUpTo(ReaderScreens.SplashScreen.name) { inclusive = true }
            }
        }else{
            navController.navigate(ReaderScreens.MainScreen.name) {
                popUpTo(ReaderScreens.SplashScreen.name) { inclusive = true }
            }
        }

    }
    
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .scale(scale.value),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                buildAnnotatedString {
                    withStyle( style = SpanStyle(
                        color = Color.Black,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Medium
                        )){
                        append("Capston")
                    }
                    append(" ")
                    withStyle( style = SpanStyle(
                        color = Color.Red,
                        fontWeight = FontWeight.Bold,
                        fontSize = 72.sp)){
                        append("R")
                    }
                    withStyle( style = SpanStyle(
                        color = Color.Red,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp)){
                        append("eader")
                    }

                }
                )
            Text(buildAnnotatedString {
                withStyle(style = SpanStyle(
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Cursive,
                    color = Color.Black,
                    fontSize = 48.sp
                    )){
                    append("Book")
                }
            })
            Text(buildAnnotatedString {
                withStyle(style = SpanStyle( fontWeight = FontWeight.SemiBold)){
                    append("A reader book for everyone")
                }
            })

        }
        Column( modifier = Modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,) {
            
            Text(text = "Made by " + stringResource(id = R.string.author),
                modifier = Modifier.padding(bottom = 23.dp),
                color = Color.DarkGray,
                style = MaterialTheme.typography.caption

                )
            
        }

    }


}