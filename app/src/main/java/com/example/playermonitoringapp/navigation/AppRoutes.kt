package com.example.playermonitoringapp.navigation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.playermonitoringapp.ui.theme.screens.OnboardingScreen
import com.example.playermonitoringapp.ui.theme.screens.SigninScreen
import com.example.playermonitoringapp.ui.theme.screens.SignupScreen
import com.example.playermonitoringapp.ui.theme.screens.SplashScreen
import com.example.playermonitoringapp.ui.theme.screens.PredictionInputScreen
import com.example.playermonitoringapp.ui.theme.screens.PredictionResultScreen
import com.example.playermonitoringapp.ui.theme.screens.ChatScreen
import com.example.playermonitoringapp.ui.theme.viewmodels.PredictionViewModel
import com.example.playermonitoringapp.ui.theme.viewmodels.ChatBotViewModel

object AppRoutes {
    const val SIGNUP_ROUTE = "signup"
    const val SIGNIN_ROUTE = "signin"
    const val HOME_ROUTE = "home"
    const val SPLASH_ROUTE = "splash"
    const val ONBOARDING_ROUTE = "onboarding"
    const val CHATSCREEN = "chat"
    const val PREDICTION_INPUT_ROUTE = "prediction_input"
    const val PREDICTION_RESULT_ROUTE = "prediction_result"
}

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    // Create shared instances of the view models.
    val predictionViewModel: PredictionViewModel = viewModel()
    val chatBotViewModel: ChatBotViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.SPLASH_ROUTE,
        modifier = modifier
    ) {
        composable(route = AppRoutes.SIGNUP_ROUTE) { SignupScreen(navController) }
        composable(route = AppRoutes.SIGNIN_ROUTE) { SigninScreen(navController) }

        composable(route = AppRoutes.SPLASH_ROUTE) { SplashScreen(navController) }
        composable(route = AppRoutes.ONBOARDING_ROUTE) { OnboardingScreen(navController) }

        // Prediction Input Screen after sign in.
        composable(route = AppRoutes.PREDICTION_INPUT_ROUTE) {
            PredictionInputScreen(
                navController = navController,
                viewModel = predictionViewModel
            )
        }
        // Prediction Result Screen after making a prediction.
        composable(route = AppRoutes.PREDICTION_RESULT_ROUTE) {
            val state = predictionViewModel.uiState.collectAsState().value
            state.predictionResult?.let { result ->
                PredictionResultScreen(result = result, navController = navController)
            } ?: Text("No prediction result available")
        }
        // Chat Screen route.
        composable(route = AppRoutes.CHATSCREEN) {
            ChatScreen(
                navController = navController,
                viewModel = chatBotViewModel
            )
        }
    }
}
