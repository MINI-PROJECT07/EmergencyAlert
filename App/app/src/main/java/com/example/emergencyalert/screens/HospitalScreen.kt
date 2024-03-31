package com.example.emergencyalert.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Composable
fun HospitalScreen(context:Context,hospitalViewModel: HospitalViewModel) {
//    val hospitalService = HospitalService.create()
//    var hospitals = remember {
//        mutableListOf<Hospital>();
//    }
//    var authToken by remember {
//        mutableStateOf("")
//    }
//    LaunchedEffect(key1 = true) {
//        val authCounter = stringPreferencesKey("auth_counter")
//        val authCounterFlow: Flow<String> = context.dataStore.data.map { preferences ->
//            preferences[authCounter] ?: "0"
//        }
//        authCounterFlow.collect {
//            if(it!="0"){
//                authToken = it;
//                var curh = hospitalService.getHospitals(authToken);
//                for(h in curh){
//                    hospitals.add(h);
//                }
//                println("***"+hospitals)
//
//            }
//        }
//    }
    Scaffold() {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(it)
            .padding(bottom = 80.dp)){
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(items = hospitalViewModel.hospitals){
                    hospital->
                    HospitalItem(hospital);
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
