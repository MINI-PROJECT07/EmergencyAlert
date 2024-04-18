package com.example.emergencyalert.screens.useroperations

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun FirstAid(context: Context ) {
    Column(modifier = Modifier.fillMaxSize()) {
        DisplayFirstAid("Cuts or Scraps", "  1. Apply pressure to stop bleeding.\n\n" +
                "  2. Clean the wound with mild soap and water.\n\n" +
                "  3. Cover the wound with a sterile bandage or dressing.\n\n" +
                "  4. Elevate the injured area if possible.")
        DisplayFirstAid("Burns", "  1. Cool the burn with cold running water for at least 10 minutes.\n\n" +
                "  2. Remove clothing or jewelry from the burned area if possible.\n\n" +
                "  3. Cover the burn with a sterile, non-adhesive dressing.\n\n" +
                "  4. Do not apply ice, butter, or ointments to the burn.")
        DisplayFirstAid("Fractures","  1. Immobilize the injured area with a splint or sling.\n\n" +
                "  2. Apply ice to reduce swelling and pain.\n\n" +
                "  3. Elevate the injured limb if possible.\n\n" +
                "  4. Do not try to realign a bone or joint if broken.")
    }
}

@Composable
fun DisplayFirstAid(title: String,info: String) {
    var expanded by remember { mutableStateOf((title=="Fractures")) } // State to track if the dropdown is expanded

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp).clickable(
                    enabled = true,
                    role = Role.Button,
                    onClick = {
                        expanded = !expanded
                    }
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "\nFirst Aid Instructions in case of $title",
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                color = Color.Black,
            )

        }

        // Content of the dropdown
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            Column(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start,
            ) {
                Text(
                    text = "Instructions in case of $title",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(red = 51, green = 171, blue = 249)
                )

                Box(
                    modifier = Modifier
                        .height(290.dp)
                        .fillMaxWidth()
                        .border(2.dp, Color.Black, shape = RoundedCornerShape(8.dp))
                        .background(
                            Color(red = 51, green = 171, blue = 249),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(10.dp), // Set the size of the box
                    contentAlignment = Alignment.CenterStart, // Center align the content
                    content = {
                        Text(
                            text = info,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            color = Color.White,
                        )
                    }
                )
            }
        }



    }
}


