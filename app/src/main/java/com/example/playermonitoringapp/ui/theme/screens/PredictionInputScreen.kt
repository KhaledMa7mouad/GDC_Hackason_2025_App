package com.example.playermonitoringapp.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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
import com.example.playermonitoringapp.ui.theme.FotGreen
import com.example.playermonitoringapp.ui.theme.viewmodels.PredictionViewModel

// Define your custom color; you can also use your theme's color.

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
            .background(Color.Black)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            "Enter Your Info",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.White
        )
        StyledInputField(label = "Sex", value = sex, onValueChange = { sex = it })
        StyledInputField(label = "Marital Status", value = maritalStatus, onValueChange = { maritalStatus = it })
        StyledInputField(label = "Age", value = age, onValueChange = { age = it })
        StyledInputField(label = "Salary", value = salary, onValueChange = { salary = it })
        StyledInputField(label = "Additional Income", value = additionalIncome, onValueChange = { additionalIncome = it })
        StyledInputField(label = "House Ownership", value = houseOwnership, onValueChange = { houseOwnership = it })
        StyledInputField(label = "Home Telephone", value = homeTelephone, onValueChange = { homeTelephone = it })
        StyledInputField(label = "Education Level", value = educationLevel, onValueChange = { educationLevel = it })
        StyledInputField(label = "Loans Other Banks", value = loansOtherBanks, onValueChange = { loansOtherBanks = it })
        StyledInputField(label = "Corporate Guarantee", value = corporateGuarantee, onValueChange = { corporateGuarantee = it })
        StyledInputField(label = "Company 01 (1 for true, 0 for false)", value = company01, onValueChange = { company01 = it })
        StyledInputField(label = "Company 10 (1 for true, 0 for false)", value = company10, onValueChange = { company10 = it })

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
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = FotGreen,
                contentColor = Color.DarkGray,
                disabledContainerColor = Color.Gray
            )
        ) {
            Text("Get Prediction")
        }

        Button(
            onClick = {
                navController.navigate(AppRoutes.CHATSCREEN)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = FotGreen,
                contentColor = Color.DarkGray,
                disabledContainerColor = Color.Gray
            )
        ) {
            Text("Chat With Ai")
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
fun StyledInputField(label: String, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = label,
                color = Color.White
            )
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedPlaceholderColor = FotGreen,
            focusedContainerColor = Color.DarkGray,
            unfocusedContainerColor = Color.DarkGray,
            focusedBorderColor = FotGreen,
            unfocusedBorderColor = Color.Gray,
            focusedLabelColor = FotGreen
        ),
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        shape = RoundedCornerShape(50) // This makes the text field pill-shaped
    )
}

