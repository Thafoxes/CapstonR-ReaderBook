package com.example.capstonrreaderbook.screens.login

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.capstonrreaderbook.components.AppTopBar
import com.example.capstonrreaderbook.components.ButtonLogin
import com.example.capstonrreaderbook.components.HyperLinkPage
import com.example.capstonrreaderbook.components.OutlineTextFieldInputs
import com.example.capstonrreaderbook.navigation.ReaderScreens
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun LoginScreen(navController: NavController,
                viewModel: AuthViewModel = viewModel()){

    val email = remember{
        mutableStateOf("")
    }
    val password = remember{
        mutableStateOf("")
    }
    val showPassword = remember{
        mutableStateOf(false)
    }
    var context = LocalContext.current
    Surface(
        modifier = Modifier
            .fillMaxSize(),
        
        ) {
        
        Scaffold( topBar = {

            AppTopBar()
        }) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp, start = 5.dp, end = 5.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
                ) {

                Text(
                    "Login email",
                    modifier = Modifier.padding(3.dp),
                    style = MaterialTheme.typography.h6,
                    textDecoration = TextDecoration.None,
                )

                OutlineTextFieldInputs(
                    textState = email,
                    labelTextField = "Email",
                    placeholderTextField = "Enter your email here"
                    )
                OutlineTextFieldInputs(
                    textState = password,
                    labelTextField = "Password",
                    placeholderTextField = "Enter your password here",
                    icon = Icons.Default.Lock,
                    iconDescription = "enter your password here",
                    keyboardType = KeyboardType.Password,
                    isSensitiveData = true,

                    )
                //forgot password
                HyperLinkPage(
                    text = "Forgot password?",
                    textAlign = TextAlign.Start,
                    color = Color.DarkGray.copy(0.8f),
                    textDecoration = TextDecoration.None,
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                ){
                    Toast.makeText(context, "Go Restore Password", Toast.LENGTH_LONG).show()
                }

                ButtonLogin(buttonText = "Log in"){
                    if (password.value.isNullOrEmpty() || email.value.isNullOrEmpty()) {
                        Toast.makeText(context, "information not fill in", Toast.LENGTH_LONG).show()

                    } else {
//                        do firebase authentication here
                        viewModel.LoginUser(email = email.value, password = password.value){
                            navController.navigate(ReaderScreens.MainScreen.name)
                        }
                        password.value = ""
                        email.value = ""
                    }
                }

                HyperLinkPage(text = "No account? Create new one!"){
                    navController.navigate(ReaderScreens.RegisterScreen.name)
                }

            }
        }

    }

}



