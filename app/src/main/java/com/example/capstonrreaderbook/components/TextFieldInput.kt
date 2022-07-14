package com.example.capstonrreaderbook.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.capstonrreaderbook.ui.theme.subTitleSize

@Preview(showBackground = true)
@Composable
fun OutlineTextFieldInputs(
    textState: MutableState<String> = remember{ mutableStateOf("j")},
    imeAction: ImeAction = ImeAction.Next,
    labelTextField: String = "Username / Email",
    placeholderTextField: String = "Enter your username or email here",
    keyboardType: KeyboardType = KeyboardType.Text,
    onAction: KeyboardActions = KeyboardActions.Default,
    isSingleLine : Boolean = true,
    icon: ImageVector = Icons.Default.Person,
    iconDescription: String = "Username or email",
    isEnabled: Boolean = true,
    isSensitiveData: Boolean = false,
    ){
    var isShowPassword = remember {
        mutableStateOf(false)
    }


    OutlinedTextField(
        value = textState.value,
        onValueChange = {
            textState.value = it
        },
        enabled = isEnabled,
        label = { Text(text = labelTextField)},
        placeholder = { Text(
            text = placeholderTextField)
                      },
        leadingIcon = { Icon(
            imageVector = icon,
            contentDescription = iconDescription)
                      },
        trailingIcon = {
           if(isSensitiveData){
               if(isShowPassword.value){
                   Icon(
                       imageVector = Icons.Default.VisibilityOff,
                       contentDescription = "show password",
                        modifier = Modifier.clickable {
                            isShowPassword.value = !isShowPassword.value
                        }
                       )

               }else{
                   Icon(
                       imageVector = Icons.Default.Visibility,
                       contentDescription = "hide password",
                       modifier = Modifier.clickable {
                           isShowPassword.value = !isShowPassword.value
                       })

               }
           }
        },
        textStyle = TextStyle(
            fontSize = subTitleSize ,
            color = Color.Black),
        keyboardActions = onAction,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction),
        singleLine = isSingleLine,
        visualTransformation =
        if(isSensitiveData){
            if (isShowPassword.value) {
                VisualTransformation.None

            } else {
                PasswordVisualTransformation()
            }
        }else{
            VisualTransformation.None
        }

        ,
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()

        )

}