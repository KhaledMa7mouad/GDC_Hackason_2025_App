package com.example.playermonitoringapp.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.playermonitoringapp.ui.theme.viewmodels.ChatBotViewModel
import com.example.playermonitoringapp.ui.theme.viewmodels.UiState

@Composable
fun ChatScreen(
    modifier: Modifier = Modifier,
    viewModel: ChatBotViewModel,
    navController: NavController
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF121212)))
            {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    AppHeader()

                    when (uiState) {
                        is UiState.Loading -> {
                            Text(
                                text = "Loading...",
                                modifier = Modifier.padding(8.dp),
                                color = Color.White,
                                fontSize = 18.sp
                            )
                        }
                        is UiState.Success -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(0.8f)
                                    .background(Color.DarkGray, shape = RoundedCornerShape(16.dp))
                                    .padding(12.dp)
                            ) {
                                Text(
                                    text = "Response: ${(uiState as UiState.Success).message}",
                                    color = Color.White,
                                    fontSize = 18.sp
                                )
                            }
                        }
                        is UiState.Error -> {
                            Text(
                                text = "Error: ${(uiState as UiState.Error).error}",
                                color = Color.Red,
                                modifier = Modifier.padding(8.dp),
                                fontSize = 18.sp
                            )
                        }
                        else -> { /* No message in idle state */ }
                    }

                    MessageInput { message ->
                        viewModel.sendMessage(message)
                    }
                }
            }
    //marinasafwat212103754@gmail.com
    //Marina@2002
}

@Composable
fun MessageInput(onMessageSend: (String) -> Unit) {  // Fixed typo in parameter name
    var message by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .navigationBarsPadding(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp),
            placeholder = {
                Text(
                    "Type your message...",
                    color = Color.LightGray
                )
            },
            shape = RoundedCornerShape(50.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedContainerColor = Color(0xFF1E1E1E),
                unfocusedContainerColor = Color(0xFF1E1E1E),
                focusedBorderColor = Color(0xFF00FF00),
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color(0xFF00FF00)
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text
            ),
            singleLine = true,
            maxLines = 3
        )

        IconButton(
            onClick = {
                if (message.isNotBlank()) {
                    onMessageSend(message)
                    message = ""
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.Send,
                contentDescription = "Send message",
                tint = Color(0xFF00FF00)
            )
        }
    }
}

@Composable
fun AppHeader(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "ChatBot",
            color = Color.White,
            fontSize = 26.sp,
            style = MaterialTheme.typography.headlineSmall
        )
    }
}