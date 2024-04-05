package com.example.emergencyalert.screens.useraccount

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emergencyalert.userauth.UserViewModel
import com.example.emergencyalert.userauth.dto.Contact
import com.example.emergencyalert.userauth.dto.UserInfo

@Composable
fun ProfileScreen(context: Context, userViewModel: UserViewModel) {
    val diseases = listOf<String>()
    val emergencyNumbers = listOf<Contact>()
    var userInfo by remember {
        mutableStateOf<UserInfo?>(
            UserInfo(
                name = "",
                email = "",
                phoneNo = "",
                bloodGroup = "",
                age = 18,
                gender = "",
                disease = diseases,
                otherInfo = "",
                emergencyNumbers = emergencyNumbers,
                date = ""
            )
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 40.dp)
            .background(color = Color.White)
    ) {
        LaunchedEffect(key1 = true) {
            if (userViewModel.userInfo.value != null) {
                userInfo = userViewModel.userInfo.value
            }
        }
        ProfilePicture(userInfo)
        Details(userInfo)
    }
}


@Composable()
fun ProfilePicture(userInfo: UserInfo?) {
    Column(
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Icon(
            imageVector = Icons.Filled.Person,
            contentDescription = "MyIcon",
            tint = Color.Black,
            modifier = Modifier
                .height(120.dp)
                .width(120.dp)
        )
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${userInfo?.name}",
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
            )
            Text(
                text = "${userInfo?.email}",
                color = Color.Gray,
                fontWeight = FontWeight.Light,
                fontSize = 20.sp,
            )
        }

    }
}

@Composable
fun Details(userInfo: UserInfo?) {
    Column(
        modifier = Modifier.fillMaxWidth()


    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            ) {

            Text(
                text = "Gender",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color(red = 51, green = 171, blue = 249)
            )

            Box(modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .border(2.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                .background(
                    color = Color.White, shape = RoundedCornerShape(8.dp)
                ), // Set the size of the box
                contentAlignment = Alignment.CenterStart, content = {
                    Text(
                        text = "   ${userInfo?.gender}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Gray,

                        )
                })
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            ) {

            Text(
                text = "Blood Group",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color(red = 51, green = 171, blue = 249)
            )
            // Here, the Box is added for "Male" text
            Box(modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .border(2.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                .background(
                    color = Color.White, shape = RoundedCornerShape(8.dp)
                ), // Set the size of the box
                contentAlignment = Alignment.CenterStart, // Center align the content
                content = {
                    Text(
                        text = "   ${userInfo?.bloodGroup}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Gray,
                    )
                })
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),

            ) {

            Text(
                text = "Diseases",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color(red = 51, green = 171, blue = 249),
            )
            // Here, the Box is added for "Male" text
            Box(modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .border(2.dp, color = Color.Gray, shape = RoundedCornerShape(8.dp))
                .background(
                    Color.White, shape = RoundedCornerShape(8.dp)
                ), // Set the size of the box
                contentAlignment = Alignment.CenterStart, // Center align the content
                content = {
                    Text(
                        text = " ${
                            userInfo?.disease?.toString()?.replace("[", "")?.replace("]", "")
                        }",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Gray,
                    )
                })
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Button(
                onClick = { /* Handle button 1 click */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(
                        red = 51, green = 171, blue = 249
                    )
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Blood Donation")
            }
            Button(
                onClick = { /* Handle button 2 click */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(
                        red = 51, green = 171, blue = 249
                    )
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "View Contacts")
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Button(
                onClick = { /* Handle button 3 click */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(
                        red = 51, green = 171, blue = 249
                    )
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Edit Info", color = Color.White
                )
            }
        }

    }

}
