package com.example.emergencyalert

import android.content.res.Resources.Theme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.graphics.toColorInt
import androidx.navigation.NavController
import com.example.emergencyalert.routes.Screens
import com.example.emergencyalert.ui.theme.Greenish
import kotlinx.coroutines.delay


@Composable
fun Mytextfield(labelvalue: String, painterResource: Painter): String {
    val textvalue = remember {
        mutableStateOf("")
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        label = { Text(text = labelvalue) },
        value = textvalue.value,
        keyboardOptions = KeyboardOptions.Default,
        onValueChange = {
            textvalue.value = it
        },
        shape = RoundedCornerShape(20.dp),
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        }
    )
    return textvalue.value
}

@Composable
fun TextBold(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(),
        style = TextStyle(
            fontSize = 30.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal

        ),
        textAlign = TextAlign.Center,
    )
}

@Composable
fun TextNormal(value: String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        textAlign = TextAlign.Center,
    )
}

@Composable
fun PasswordMytextfield(labelvalue: String, painterResource: Painter): String {
    val password = remember {
        mutableStateOf("")
    }
    val passwordvisble = remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White),
        label = { Text(text = labelvalue) },
        shape = RoundedCornerShape(20.dp),
        value = password.value,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        onValueChange = {
            password.value = it
        },
        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },
        trailingIcon = {
            val icon = if (passwordvisble.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            IconButton(onClick = {
                passwordvisble.value = !passwordvisble.value
            }) {
                Icon(imageVector = icon, contentDescription = "")
            }
        },
        visualTransformation = if (passwordvisble.value)
            VisualTransformation.None else PasswordVisualTransformation()
    )
    return password.value
}

@Composable
fun Buttoncomponent(value: String, onclick: () -> Unit) {
    Button(
        onClick = { onclick() },
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(0.dp),
        shape = RoundedCornerShape(30.dp),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    color = Greenish,
                    shape = RoundedCornerShape(50.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = value, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        }
    }
}

@Composable
fun ClickableLoginTextComponent(tryingToLogin: Boolean = true, navController: NavController) {
    val initialText =
        if (tryingToLogin) "Already have an account? " else "Don’t have an account yet? "
    val loginText = if (tryingToLogin) "Login" else "Register"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = Color.Cyan)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }

    ClickableText(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal,
            textAlign = TextAlign.Center,

            ),
        text = annotatedString,
        onClick = {
            navController.navigate(if (tryingToLogin) Screens.Login.route else Screens.SignUp.route) {
                navController.graph.startDestinationRoute?.let { route ->
                    popUpTo(route) {
                        saveState = true
                    }
                }
                launchSingleTop = true
                restoreState = true
            }
        },
    )
}

