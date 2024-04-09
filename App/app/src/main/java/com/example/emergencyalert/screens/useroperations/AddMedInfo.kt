package com.example.emergencyalert.screens.useroperations

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.emergencyalert.ui.theme.MainColor
import com.example.emergencyalert.userauth.UserViewModel
import com.example.emergencyalert.userauth.dto.UserMedInfo
import kotlinx.coroutines.launch

@Composable
fun AddMedInfo(context: Context, navController: NavController, userViewModel: UserViewModel) {
    var bloodGroup by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var diseaseInput by remember { mutableStateOf("") }
    var otherInfo by remember { mutableStateOf("") }
    var diseases by remember { mutableStateOf(listOf<String>()) }
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
            text = "Add Medical Information",
            style = TextStyle(
                color = Color.Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(8.dp),
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

        ChipGroupSingleLineSample(diseases) { chip ->
            diseases = diseases.filter { it != chip }
        }

        OutlinedTextField(
            value = diseaseInput,
            onValueChange = { diseaseInput = it },
            label = { Text("Add Disease") },
            modifier = Modifier.fillMaxWidth(),
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
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MainColor,
                focusedLabelColor = MainColor,
                cursorColor = MainColor
            )
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
        Spacer(modifier = Modifier.height(32.dp))
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
            Text("Submit")
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

fun validateMedInfo(
    userMedInfo: UserMedInfo
): Boolean {
    return userMedInfo.bloodGroup.isNotEmpty() &&
            userMedInfo.age != 0 && userMedInfo.gender.isNotEmpty()
}

@Composable
fun ChipGroupSingleLineSample(chips: List<String>, onRemove: (String) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
            chips.forEach { chip ->
                AssistChip(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    onClick = { onRemove(chip) },
                    label = { Text(chip) }
                )
            }
        }
    }
}

