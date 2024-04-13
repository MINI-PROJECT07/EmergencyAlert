package com.example.emergencyalert.screens.useroperations

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.emergencyalert.ui.theme.MainColor
import com.example.emergencyalert.userauth.UserViewModel
import com.example.emergencyalert.userauth.dto.UserMedInfo
import kotlinx.coroutines.launch

@Composable
fun EditMedInfoForm(
    userViewModel: UserViewModel,
    navController: NavController
) {
    var userInfo by remember { userViewModel.userInfo }
    var bloodGroup by remember { mutableStateOf(userInfo?.bloodGroup.toString()) }
    var age by remember { mutableStateOf(userInfo?.age.toString()) }
    var gender by remember { mutableStateOf(userInfo?.gender.toString()) }
    var diseaseInput by remember { mutableStateOf("") }
    var otherInfo by remember { mutableStateOf(userInfo?.otherInfo.toString()) }
    var diseases by remember {
        mutableStateOf(
            userInfo?.disease?.toMutableList() ?: mutableListOf()
        )
    }
    var error by remember { mutableStateOf("") }

    var scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(16.dp).fillMaxSize().verticalScroll(
                rememberScrollState()
            )
    ) {
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
            value = gender,
            onValueChange = { gender = it },
            label = { Text("Gender") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MainColor,
                focusedLabelColor = MainColor,
                cursorColor = MainColor
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Display existing diseases
        ChipGroupSingleLineSample(diseases) { chip ->
            diseases = diseases.filter { it != chip }.toMutableList()
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = diseaseInput,
            onValueChange = { diseaseInput = it },
            label = { Text("Add Disease") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MainColor,
                focusedLabelColor = MainColor,
                cursorColor = MainColor
            ),
            trailingIcon = {
                IconButton(onClick = {
                    val newDisease = diseaseInput.trim()
                    if (newDisease.isNotEmpty()) {
                        diseases += newDisease
                        diseaseInput = "";
                    }
                }) {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                }
            },
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = otherInfo,
            onValueChange = { otherInfo = it },
            label = { Text("Other Information") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MainColor,
                focusedLabelColor = MainColor,
                cursorColor = MainColor
            )
        )

        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = error,
            color = Color.Red,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(),
            textAlign = TextAlign.Center
        );
        Button(
            onClick = {
                val medInfo = UserMedInfo(
                    bloodGroup = bloodGroup,
                    age = age.toIntOrNull() ?: 0,
                    gender = gender,
                    disease = diseases,
                    otherInfo = otherInfo
                )
                scope.launch {
                    if (validateMedInfo(medInfo)) {
                        userViewModel.addMedInfo(medInfo)
                        userViewModel.getUserInfo()
                        navController.popBackStack()
                    } else {
                        error = "Please fill all the fields"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MainColor)
        ) {
            Text("Save")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
