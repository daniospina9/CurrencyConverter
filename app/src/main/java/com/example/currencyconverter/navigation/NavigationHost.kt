package com.example.currencyconverter.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.currencyconverter.feature.history.HistoryScreen
import com.example.currencyconverter.feature.main.MainScreen
import com.example.currencyconverter.navigation.history.HistoryRoute
import com.example.currencyconverter.navigation.main.MainRoute

@Composable
fun NavigationHost() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainRoute
    ) {
        composable<MainRoute> {
            MainScreen(
                navController = navController
            )
        }
        composable<HistoryRoute> {
            HistoryScreen(
                navController = navController
            )
        }
    }
}