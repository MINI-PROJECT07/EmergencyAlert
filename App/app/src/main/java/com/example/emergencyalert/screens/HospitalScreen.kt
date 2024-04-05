package com.example.emergencyalert.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.emergencyalert.R
import com.example.emergencyalert.dataStore
import com.example.emergencyalert.hospitals.HospitalViewModel
import com.example.emergencyalert.hospitals.dto.Hospital
import com.example.emergencyalert.hospitals.services.HospitalService
import com.example.emergencyalert.location.LatLong
import com.example.emergencyalert.location.LocationViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Composable
fun HospitalScreen(
    context: Context,
    hospitalViewModel: HospitalViewModel,
    locationViewModel: LocationViewModel
) {
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
                    hospitalViewModel.getHospitals(
                        LatLong(
                            locationViewModel.location.value!!.latitude,
                            locationViewModel.location.value!!.longitude
                        )
                    )
                    println(hospitalViewModel.hospitals)
                    isLoading = false
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
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
                    items(items = hospitalViewModel.hospitals) { hospital ->
                        HospitalItem(hospital);
                    }
                }
            }
        }
    }
}


@Composable
fun HospitalItem(hospital: Hospital) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        elevation = CardDefaults.elevatedCardElevation(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background), // Replace with hospital image resource
                contentDescription = null, // You can provide description for accessibility
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = hospital.name,
                style = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = hospital.address,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp),
                )

                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "${hospital.remCapacity} Beds",
                    color = Color.Gray
                )
            }
        }
    }
}
