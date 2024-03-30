package com.example.emergencyalert.screens

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileScreen(context: Context) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBar()
        ProfilePicture()
        Spacer(modifier = Modifier.padding(8.dp))
        ProfileDetails()
        ProfileLogOut()
    }
}


@Composable
fun TopBar(
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = "",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = "Profile",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp

        )
    }


}

@Composable
fun ProfilePicture(

) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth(),
    ) {

        Icon(
            imageVector = Icons.Filled.Person, // Choose the desired icon from the Material icons library
            contentDescription = "Person Icon", // Add a content description for accessibility
            tint = Color.Black, // Optionally specify a color for the icon
            modifier = Modifier
                .height(140.dp)
                .width(140.dp)// Optionally specify size using modifier
        )

    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = "Satej Sawant",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,

            )


    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            text = "email@gmail.com",
            color = Color.Gray,
//            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )


    }


}

@Composable
fun ProfileDetails(

) {

    Column(
        modifier = Modifier.fillMaxWidth()


    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Email",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Gray,
            )

            Text(
                text = "email@gmail.com",
                color = Color.Gray,

                fontSize = 20.sp
            )


        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Medical Information",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Gray,
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Forward Arrow Icon",
                tint = Color.Gray, // Optional: specify a color for the icon
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)// Optional: specify size using modifier
            )


        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Emergency Contacts",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Gray,
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Forward Arrow Icon",
                tint = Color.Gray, // Optional: specify a color for the icon
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)// Optional: specify size using modifier
            )


        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "App Settings",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Gray,
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "Forward Arrow Icon",
                tint = Color.Gray, // Optional: specify a color for the icon
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)// Optional: specify size using modifier
            )


        }

    }

}

@Composable
fun OutlinedButtonExample(onClick: () -> Unit) {
    OutlinedButton(onClick = { onClick() }) {
        Text("Log out")
    }
}

@Composable
fun ProfileLogOut() {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        OutlinedButtonExample {

        }


    }


}