package com.example.emergencyalert.screens.useroperations
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.emergencyalert.userauth.UserViewModel
import com.example.emergencyalert.userauth.dto.UserMedInfo

@Composable
fun EditMedInfoForm(
    userViewModel: UserViewModel
) {
    var bloodGroup by remember { mutableStateOf(TextFieldValue("AB+")) }
    var age by remember { mutableStateOf(TextFieldValue("30")) }
    var gender by remember { mutableStateOf(TextFieldValue("Male")) }
    var diseaseInput by remember { mutableStateOf(TextFieldValue()) }
    var otherInfo by remember { mutableStateOf(TextFieldValue("No other information")) }
    var diseases by remember { mutableStateOf(mutableListOf("Hypertension", "Diabetes")) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        OutlinedTextField(
            value = bloodGroup,
            onValueChange = { bloodGroup = it },
            label = { Text("Blood Group") },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = gender,
            onValueChange = { gender = it },
            label = { Text("Gender") },
            modifier = Modifier.fillMaxWidth(),

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

        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = otherInfo,
            onValueChange = { otherInfo = it },
            label = { Text("Other Information") },
            modifier = Modifier.fillMaxWidth(),

        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                // Add the newly entered disease
                if (diseaseInput.text.isNotEmpty()) {
                    diseases.add(diseaseInput.text)
                    diseaseInput = TextFieldValue()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Disease")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Update medical information
                val updatedMedInfo = UserMedInfo(
                    bloodGroup = bloodGroup.text,
                    age = age.text.toIntOrNull() ?: 0,
                    gender = gender.text,
                    disease = diseases,
                    otherInfo = otherInfo.text
                )
                // Call the appropriate function to update the information
//                onEditMedInfo(updatedMedInfo)
                println(updatedMedInfo)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
    }
}
