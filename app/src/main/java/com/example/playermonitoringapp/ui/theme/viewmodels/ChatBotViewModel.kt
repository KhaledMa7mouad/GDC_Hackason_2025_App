package com.example.playermonitoringapp.ui.theme.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

// Define a simple UI state to track the response status.
sealed class UiState {
    object Idle : UiState()
    object Loading : UiState()
    data class Success(val message: String) : UiState()
    data class Error(val error: String) : UiState()
}

class ChatBotViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState

    // Initialize the Gemini model.
    // Ensure that your Gemini SDK dependency is added and that the API key is valid.
    private val generativeModel = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = "AIzaSyBxK5t3zquqHo688mbw44ztki4apNXv7Ls"
    )

    // This function sends a prompt to the Gemini model and updates the UI state.
    fun sendPrompt(prompt: String) {
        _uiState.value = UiState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = generativeModel.generateContent(
                    content {
                        text(prompt)
                    }
                )
                val outputContent = response.text ?: "No answer"
                _uiState.value = UiState.Success(outputContent)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    // This function sends a prompt to the Gemini model and updates the UI state.
    fun predict(prompt: String) {
        _uiState.value = UiState.Loading

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = generativeModel.generateContent(
                    content {
                        text("why this ")
                    }
                )
                val outputContent = response.text ?: "No answer"
                _uiState.value = UiState.Success(outputContent)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.localizedMessage ?: "Unknown error")
            }
        }
    }

    // For convenience, sendMessage delegates to sendPrompt.
    fun sendMessage(question: String) {
        sendPrompt(question)
    }
}
