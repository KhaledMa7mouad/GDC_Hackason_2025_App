package com.example.playermonitoringapp.ui.theme.viewmodels

import com.example.playermonitoringapp.API.PredictionResponse

data class PredictionUiState(
    val isLoading: Boolean = false,
    val predictionResult: PredictionResponse? = null,
    val error: String? = null
)
