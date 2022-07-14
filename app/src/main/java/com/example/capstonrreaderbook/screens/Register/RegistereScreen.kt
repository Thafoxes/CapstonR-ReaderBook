package com.example.capstonrreaderbook.screens.Register

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import java.util.regex.Pattern

@Composable
fun RegisterScreen(navController: NavController){

    val email = remember{
        mutableStateOf("")
    }
    val username = remember{
        mutableStateOf("")
    }
    val repeatPassword = remember{
        mutableStateOf("")
    }
    val phoneNumber = remember{
        mutableStateOf("")
    }
    val password = remember{
        mutableStateOf("")
    }

    var context = LocalContext.current
    Surface(
        modifier = Modifier
            .fillMaxSize(),

        ) {

        Scaffold( topBar = {
            AppTopBar( topAppTitle = "Register here")
        }) {


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp, start = 5.dp, end = 5.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    "Username",
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.subtitle1,
                    textDecoration = TextDecoration.None,
                    textAlign = TextAlign.Start
                )
                OutlineTextFieldInputs(
                    textState = username,
                    labelTextField = "Username",
                    icon = Icons.Default.Person,
                    iconDescription = "Username",
                )

                Text(
                    "Email",
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.subtitle1,
                    textDecoration = TextDecoration.None,
                    textAlign = TextAlign.Start
                )
                OutlineTextFieldInputs(
                    textState = email,
                    labelTextField = "email",
                    icon = Icons.Default.Email,
                    iconDescription = "Email",
                )

                Text(
                    "Phone number",
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.subtitle1,
                    textDecoration = TextDecoration.None,
                    textAlign = TextAlign.Start
                )
                OutlineTextFieldInputs(
                    textState = phoneNumber,
                    labelTextField = "Phone number",
                    icon = Icons.Default.Phone,
                    iconDescription = "Phone number",
                    keyboardType = KeyboardType.Phone

                )

                Text(
                    "Password",
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.subtitle1,
                    textDecoration = TextDecoration.None,
                    textAlign = TextAlign.Start
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

                Text(
                    "Repeat password",
                    modifier = Modifier
                        .padding(3.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.subtitle1,
                    textDecoration = TextDecoration.None,
                    textAlign = TextAlign.Start
                )
                OutlineTextFieldInputs(
                    textState = repeatPassword,
                    labelTextField = "Repeat password",
                    placeholderTextField = "Repeat your password here",
                    icon = Icons.Default.Lock,
                    iconDescription = "repeat your password here",
                    keyboardType = KeyboardType.Password,
                    isSensitiveData = true,

                    )

                ButtonLogin(buttonText = "Register Account"){
                    //series of check here
                    //check empty textfield
                    if (username.value.isNullOrEmpty() ||
                        email.value.isNullOrEmpty() ||
                        password.value.isNullOrEmpty() ||
                        repeatPassword.value.isNullOrEmpty() ||
                        phoneNumber.value.isNullOrEmpty()) {

                        Toast.makeText(context, "information not fill in", Toast.LENGTH_LONG).show()
                        return@ButtonLogin
                    }
                    //check password same
                    if(!password.value.equals(repeatPassword.value)){
                        Toast.makeText(context, "Password not same", Toast.LENGTH_LONG).show()
                        return@ButtonLogin
                    }
                    //check phone number
                    if(!Patterns.PHONE.matcher(phoneNumber.value).matches()){
                        Toast.makeText(context, "Wrong phone number format", Toast.LENGTH_LONG).show()
                        return@ButtonLogin
                    }
                    //check valid email
                    if(!Patterns.EMAIL_ADDRESS.matcher(email.value).matches()){
                        Toast.makeText(context, "Email not valid", Toast.LENGTH_LONG).show()
                        return@ButtonLogin
                    }
                    //check username special Characters
                    val specialCharacters = Pattern.compile("[^A-Za-z0-9]")
                    if(specialCharacters.matcher(username.value).find()){
                        Toast.makeText(context, "Username not valid", Toast.LENGTH_LONG).show()
                        return@ButtonLogin
                    }

//                  do firebase authentication here
                    Log.d("inputcheck ", "${password.value} and username is ${email.value}")


                }

                HyperLinkPage(text = "Existing account? Log in here"){
                    navController.navigate(ReaderScreens.LoginScreen.name)
                }

            }
        }

    }
}
