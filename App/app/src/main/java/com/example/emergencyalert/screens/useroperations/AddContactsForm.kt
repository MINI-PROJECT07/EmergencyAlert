package com.example.emergencyalert.screens.useroperations

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emergencyalert.ui.theme.MainColor
import com.example.emergencyalert.userauth.UserViewModel
import com.example.emergencyalert.userauth.dto.AddContact
import com.example.emergencyalert.userauth.dto.AddContacts
import kotlinx.coroutines.launch

@Composable
fun AddContactsForm(userViewModel: UserViewModel) {
    var contact1Name by remember { mutableStateOf(TextFieldValue()) }
    var contact1Number by remember { mutableStateOf(TextFieldValue()) }
    var contact2Name by remember { mutableStateOf(TextFieldValue()) }
    var contact2Number by remember { mutableStateOf(TextFieldValue()) }
    var error by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            "Add contacts",
            style = TextStyle(
                color = Color.Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
        )

        Text(
            text = "Contact 1",
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Left
            ),
            modifier = Modifier
                .fillMaxWidth(),
        )
        OutlinedTextField(
            value = contact1Name,
            onValueChange = { contact1Name = it },
            label = { Text("Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MainColor,
                focusedLabelColor = MainColor,
                cursorColor = MainColor
            )
        )

        OutlinedTextField(
            value = contact1Number,
            onValueChange = { contact1Number = it },
            label = { Text("Number") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MainColor,
                focusedLabelColor = MainColor,
                cursorColor = MainColor
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Contact 2",
            style = TextStyle(
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Left
            ),
            modifier = Modifier
                .fillMaxWidth(),
        )
        OutlinedTextField(
            value = contact2Name,
            onValueChange = { contact2Name = it },
            label = { Text("Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MainColor,
                focusedLabelColor = MainColor,
                cursorColor = MainColor
            )
        )

        OutlinedTextField(
            value = contact2Number,
            onValueChange = { contact2Number = it },
            label = { Text("Number") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MainColor,
                focusedLabelColor = MainColor,
                cursorColor = MainColor
            )
        )

        Text(
            text = error,
            style = TextStyle(
                color = Color.Red,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth(),
        )
        Button(
            onClick = {
                val contact1 = AddContact(contact1Name.text, contact1Number.text)
                val contact2 = AddContact(contact2Name.text, contact2Number.text)
                val addContacts = listOf(contact1, contact2)
                scope.launch {
                    if (validateContacts(addContacts)) {
                        userViewModel.addContacts(
                            AddContacts(
                                emergencyNumbers = addContacts
                            )
                        )
                    } else {
                        error = "Please fill all the fields"
                    }
                    println("contacts $addContacts")
                }

            },
            modifier = Modifier.align(Alignment.End),
            colors = ButtonDefaults.buttonColors(containerColor = MainColor)

        ) {
            Text("Add Contacts")
        }
    }
}

fun validateContacts(contacts: List<AddContact>): Boolean {
    for (contact in contacts) {
        if (contact.name.isEmpty() || contact.phoneNo.isEmpty()) {
            return false
        }
    }
    return true
}
