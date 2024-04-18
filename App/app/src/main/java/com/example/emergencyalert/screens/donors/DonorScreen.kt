package com.example.emergencyalert.screens.donors

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emergencyalert.donors.DonorViewModel
import com.example.emergencyalert.donors.dto.BloodDonorInfo
import com.example.emergencyalert.location.LatLong
import com.example.emergencyalert.location.LocationViewModel
import com.example.emergencyalert.screens.hospital.HospitalItem
import com.example.emergencyalert.ui.theme.MainColor

@Composable
fun DonorScreen(donorViewModel: DonorViewModel, locationViewModel: LocationViewModel) {
    var isLoading by remember { mutableStateOf(true) }
    Scaffold() {
        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(it)
                .padding(bottom = 80.dp)
        ) {

            LaunchedEffect(locationViewModel.location) {
                if (locationViewModel.location.value != null) {
                    donorViewModel.getBloodDonorsNearest(
                        locationViewModel.location.value!!.latitude,
                        locationViewModel.location.value!!.longitude)
                    isLoading = false
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                if (isLoading) {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier.width(64.dp),
                            color = MaterialTheme.colorScheme.secondary,
                            trackColor = MaterialTheme.colorScheme.surfaceVariant,
                        )
                    }
                } else {
                    items(items = donorViewModel.bloodDonors) { donor ->
                        BloodDonorCard(donor);
                    }
                }
            }
        }
    }
}


@Composable
fun BloodDonorCard(donor: BloodDonorInfo) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(1.dp, Color.LightGray)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = donor.name,
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                color = MainColor,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Blood Group: ${donor.bloodGroup}",
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Phone: ${donor.phoneNo}",
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Age: ${donor.age}",
                style = TextStyle(fontSize = 16.sp),
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Address: ${donor.address}",
                style = TextStyle(fontSize = 16.sp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}