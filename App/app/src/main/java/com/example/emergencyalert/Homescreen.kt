package com.example.emergencyalert

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.emergencyalert.sensor.SensorViewModel


@Composable
fun HomeScreen(navController: NavHostController, context: Context,viewModel: SensorViewModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .padding(20.dp)
                    .background(Color.White),
                containerColor = Color(0xFFF5F5F5),
                tonalElevation = 10.dp,

                ) {
                Row(modifier = Modifier.fillMaxSize()) {
                    Text("BottomAppBar")
                }
            }
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
        ) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)) {
                Text("Home Screen")
            }
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(5.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                Text("Value: ${viewModel.value1}")

            }
        }
    }
}

