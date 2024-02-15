package com.example.emergencyalert

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun SignUpScreen(navController: NavController) {
    Surface(

        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp),

        ) {
        Column(Modifier.fillMaxSize()) {
            TextBold(value = "Create Account")
            Spacer(modifier = Modifier.height(10.dp))
            Mytextfield(
                labelvalue = "First Name",
                painterResource(id = R.drawable.account_outline)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Mytextfield(
                labelvalue = "Phone No",
                painterResource = painterResource(id = R.drawable.cellphone )

            )
            Spacer(modifier = Modifier.height(10.dp))
            Mytextfield(
                labelvalue = "Email",
                painterResource = painterResource(id = R.drawable.gmail)
            )
            Spacer(modifier = Modifier.height(10.dp))
            PasswordMytextfield(
                labelvalue = "Password",
                painterResource = painterResource(id = R.drawable.lock_outline)
            )
            Spacer(modifier = Modifier.height(50.dp))
            Buttoncomponent(
                value = "Register"
            ){

            }
            Spacer(modifier = Modifier.height(10.dp))

            ClickableLoginTextComponent(tryingToLogin = true,navController)
        }

    }
}


@Composable
@Preview(showBackground = true)
fun Previewscreen(){
    val navController = rememberNavController()
    SignUpScreen(navController = navController)
}
