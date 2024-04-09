package com.example.emergencyalert.screens.useraccount


import android.content.res.Resources.Theme
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emergencyalert.ui.theme.MainColorDark
import com.example.emergencyalert.userauth.UserViewModel
import com.example.emergencyalert.userauth.dto.Contact

@Composable
fun Contacts(
    userViewModel: UserViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        item {
            Text(
                text = "Contacts",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp, top = 10.dp),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
            )
        }
        items(userViewModel.userInfo.value!!.emergencyNumbers) { contact ->
            ContactCard(contact = contact)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun ContactCard(contact: Contact) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(elevation = 4.dp),
        elevation = CardDefaults.elevatedCardElevation(),
        shape = RoundedCornerShape(8.dp),
        colors = CardColors(
            containerColor = Color.White,
            contentColor = CardDefaults.cardColors().contentColor,
            disabledContainerColor = CardDefaults.cardColors().disabledContainerColor,
            disabledContentColor = CardDefaults.cardColors().disabledContentColor
        ),
        border = BorderStroke(1.dp, MainColorDark)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        ) {
            Text(
                text = contact.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = contact.phoneNo,
                style = MaterialTheme.typography.displaySmall
            )
        }

    }
}

