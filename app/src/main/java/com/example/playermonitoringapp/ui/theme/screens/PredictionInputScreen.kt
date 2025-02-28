package com.example.playermonitoringapp.ui.theme.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.playermonitoringapp.API.PredictionRequest
import com.example.playermonitoringapp.navigation.AppRoutes
import com.example.playermonitoringapp.ui.theme.viewmodels.PredictionViewModel

@Composable
fun PredictionInputScreen(
    navController: NavController,
    viewModel: PredictionViewModel
) {
    var sex by remember { mutableStateOf("") }
    var maritalStatus by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var salary by remember { mutableStateOf("") }
    var additionalIncome by remember { mutableStateOf("") }
    var houseOwnership by remember { mutableStateOf("") }
    var homeTelephone by remember { mutableStateOf("") }
    var educationLevel by remember { mutableStateOf("") }
    var loansOtherBanks by remember { mutableStateOf("") }
    var corporateGuarantee by remember { mutableStateOf("") }
    var company01 by remember { mutableStateOf("") }
    var company10 by remember { mutableStateOf("") }

    val state = viewModel.uiState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Enter Prediction Parameters", style = MaterialTheme.typography.h5)
        InputField("Sex", sex) { sex = it }
        InputField("Marital Status", maritalStatus) { maritalStatus = it }
        InputField("Age", age) { age = it }
        InputField("Salary", salary) { salary = it }
        InputField("Additional Income", additionalIncome) { additionalIncome = it }
        InputField("House Ownership", houseOwnership) { houseOwnership = it }
        InputField("Home Telephone", homeTelephone) { homeTelephone = it }
        InputField("Education Level", educationLevel) { educationLevel = it }
        InputField("Loans Other Banks", loansOtherBanks) { loansOtherBanks = it }
        InputField("Corporate Guarantee", corporateGuarantee) { corporateGuarantee = it }
        InputField("Company 01 (1 for true, 0 for false)", company01) { company01 = it }
        InputField("Company 10 (1 for true, 0 for false)", company10) { company10 = it }

        if (state.isLoading) {
            CircularProgressIndicator()
        }
        if (state.error != null) {
            Text("Error: ${state.error}", color = Color.Red)
        }

        Button(
            onClick = {
                val request = PredictionRequest(
                    Sex = sex.toIntOrNull() ?: 0,
                    MaritalStatus = maritalStatus.toIntOrNull() ?: 0,
                    Age = age.toIntOrNull() ?: 0,
                    Salary = salary.toDoubleOrNull() ?: 0.0,
                    AdditionalIncome = additionalIncome.toIntOrNull() ?: 0,
                    HouseOwnership = houseOwnership.toIntOrNull() ?: 0,
                    HomeTelephone = homeTelephone.toIntOrNull() ?: 0,
                    EducationLevel = educationLevel.toIntOrNull() ?: 0,
                    LoansOtherBanks = loansOtherBanks.toIntOrNull() ?: 0,
                    CorporateGuarantee = corporateGuarantee.toIntOrNull() ?: 0,
                    Company_01 = company01 == "1",
                    Company_10 = company10 == "1"
                )
                viewModel.makePrediction(request)
                // Do not navigate immediately; wait for the prediction result.
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Get Prediction")
        }
        Button(
            onClick = {
                navController.navigate(AppRoutes.CHATSCREEN)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("User Screen")
        }


    }



    // Navigate to the result screen once a prediction result is available.
    LaunchedEffect(key1 = state.predictionResult) {
        if (state.predictionResult != null) {
            navController.navigate(AppRoutes.PREDICTION_RESULT_ROUTE)
        }
    }
}

@Composable
fun InputField(label: String, value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = { newValue ->
            // Allow only digits and a decimal point (for salary) if needed.
            onValueChange(newValue.filter { it.isDigit() || it == '.' })
        },
        label = { Text(label) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier.fillMaxWidth()
    )
}
