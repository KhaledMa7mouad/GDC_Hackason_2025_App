package com.example.playermonitoringapp.API

class ApiRepository {
    suspend fun getPrediction(request: PredictionRequest): PredictionResponse {
        return RetrofitClient.apiService.getPrediction(request)
    }
}
