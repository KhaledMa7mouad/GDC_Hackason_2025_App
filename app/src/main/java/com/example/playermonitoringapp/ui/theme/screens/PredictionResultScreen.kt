package com.example.playermonitoringapp.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.playermonitoringapp.API.PredictionResponse
import com.example.playermonitoringapp.ui.theme.FotGreen

@Composable
fun PredictionResultScreen(result: PredictionResponse, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                shape = RoundedCornerShape(12.dp),
                backgroundColor = FotGreen,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(128.dp)
                    .padding(8.dp),
                elevation = 4.dp
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "Prediction Result",
                        style = MaterialTheme.typography.h6.copy(fontSize = 24.sp),
                        color = Color.White
                    )
                    Text(
                        text = "Predicted Score: ${"%.2f".format(result.prediction)}",
                        style = MaterialTheme.typography.h6.copy(fontSize = 24.sp),
                        color = Color.White
                    )
                }
            }
            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.padding(top = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = FotGreen,
                )
            ) {
                Text(text = "Return", fontSize = 20.sp)
            }
        }
    }
}
