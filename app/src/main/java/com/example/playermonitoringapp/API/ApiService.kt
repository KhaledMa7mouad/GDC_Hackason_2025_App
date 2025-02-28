package com.example.playermonitoringapp.API

import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @POST("/predict")
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    suspend fun getPrediction(
        @Body request: PredictionRequest
    ): PredictionResponse
}
