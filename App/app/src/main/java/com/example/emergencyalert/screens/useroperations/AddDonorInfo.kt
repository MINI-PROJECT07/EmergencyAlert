package com.example.emergencyalert.screens.useroperations

import android.content.Context
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.emergencyalert.ui.theme.MainColor
import com.example.emergencyalert.userauth.UserViewModel
import com.example.emergencyalert.userauth.dto.UserMedInfo
import kotlinx.coroutines.launch

@Composable
fun AddDonorInfo(context: Context, navController: NavController, userViewModel: UserViewModel)
{
    var name by remember { mutableStateOf("") }
    var phoneNo by remember { mutableStateOf("") }
    var bloodGroup by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var permanantLocation by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var error by remember { mutableStateOf("") }
    var scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Be a Blood Donor",
            style = TextStyle(
                color = Color.Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(8.dp).fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MainColor,
                focusedLabelColor = MainColor,
                cursorColor = MainColor
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = phoneNo,
            onValueChange = { age = it },
            label = { Text("Phone Number") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MainColor,
                focusedLabelColor = MainColor,
                cursorColor = MainColor
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = bloodGroup,
            onValueChange = { bloodGroup = it },
            label = { Text("Blood Group") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MainColor,
                focusedLabelColor = MainColor,
                cursorColor = MainColor
            )
        )
        Spacer(modifier = Modifier.height(16.dp))


        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MainColor,
                focusedLabelColor = MainColor,
                cursorColor = MainColor
            )
        )
        Spacer(modifier = Modifier.height(16.dp))




        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Address") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MainColor,
                focusedLabelColor = MainColor,
                cursorColor = MainColor
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {

            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MainColor)
        ) {
            Text("Submit")
        }
    }
}

//fun validateMedInfo(
//    userMedInfo: UserMedInfo
//): Boolean {
//    return userMedInfo.bloodGroup.isNotEmpty() &&
//            userMedInfo.age != 0 && userMedInfo.gender.isNotEmpty()
//}
//
//@Composable
//fun ChipGroupSingleLineSample(chips: List<String>, onRemove: (String) -> Unit) {
//    Column(horizontalAlignment = Alignment.CenterHorizontally) {
//        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
//            chips.forEach { chip ->
//                AssistChip(
//                    modifier = Modifier.padding(horizontal = 4.dp),
//                    onClick = { onRemove(chip) },
//                    label = { Text(chip) }
//                )
//            }
//        }
//    }
//}