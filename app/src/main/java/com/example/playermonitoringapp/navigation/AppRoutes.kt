package com.example.playermonitoringapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.playermonitoringapp.navigation.AppRoutes.HOME_ROUTE
import com.example.playermonitoringapp.navigation.AppRoutes.SIGNIN_ROUTE
import com.example.playermonitoringapp.navigation.AppRoutes.SIGNUP_ROUTE
import com.example.playermonitoringapp.ui.theme.screens.HomeScreen
import com.example.playermonitoringapp.ui.theme.screens.OnboardingScreen
import com.example.playermonitoringapp.ui.theme.screens.SigninScreen
import com.example.playermonitoringapp.ui.theme.screens.SignupScreen
import com.example.playermonitoringapp.ui.theme.screens.SplashScreen

object AppRoutes {
    const val SIGNUP_ROUTE = "signup"
    const val SIGNIN_ROUTE = "signin"
    const val HOME_ROUTE = "home"
    const val SPLASH_ROUTE = "splash"
    const val ONBOARDING_ROUTE = "onboarding"
}

@Composable
fun AppNavHost(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoutes.SPLASH_ROUTE, modifier = modifier
    ) {
        composable(route = SIGNUP_ROUTE) { SignupScreen(navController) }
        composable(route = SIGNIN_ROUTE) { SigninScreen(navController) }
        composable(route = HOME_ROUTE) { HomeScreen() }
        composable(route = AppRoutes.SPLASH_ROUTE) { SplashScreen(navController) }
        composable(route = AppRoutes.ONBOARDING_ROUTE) { OnboardingScreen(navController) }
    }
}
