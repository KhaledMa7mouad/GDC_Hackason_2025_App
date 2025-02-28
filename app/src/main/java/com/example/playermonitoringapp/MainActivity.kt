package com.example.playermonitoringapp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.playermonitoringapp.navigation.AppNavHost
import com.example.playermonitoringapp.ui.theme.PlayerMonitoringAppTheme
import com.example.playermonitoringapp.ui.theme.screens.ChatScreen
import com.example.playermonitoringapp.ui.theme.viewmodels.ChatBotViewModel


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PlayerMonitoringAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //ChatScreen(modifier = Modifier.padding(innerPadding))
                    AppNavHost(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

