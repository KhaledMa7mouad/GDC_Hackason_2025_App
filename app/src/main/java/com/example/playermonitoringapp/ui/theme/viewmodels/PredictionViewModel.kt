package com.example.playermonitoringapp.ui.theme.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playermonitoringapp.API.ApiRepository
import com.example.playermonitoringapp.API.PredictionRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PredictionViewModel(
    private val repository: ApiRepository = ApiRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(PredictionUiState())
    val uiState: StateFlow<PredictionUiState> = _uiState

    fun makePrediction(request: PredictionRequest) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            try {
                val response = repository.getPrediction(request)
                _uiState.value = _uiState.value.copy(isLoading = false, predictionResult = response)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = e.message)
            }
        }
    }
}
