package com.example.lab9.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lab9.screens.detals.DetailsScreen
import com.example.lab9.screens.home.HomeScreens

@Composable
fun MovieNavigation () {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MovieScreens.HomeScreen.name
    ) {
        composable(MovieScreens.HomeScreen.name) {
            HomeScreens(navController = navController)
        }
        composable(
            MovieScreens.DetailsScreen.name + "/{movie}",
            arguments = listOf(navArgument(name = "movie") { type = NavType.StringType })
        )
        { backStackyEntey ->
            DetailsScreen(
                navController = navController,
                backStackyEntey.arguments?.getInt("movie")
            )
        }

    }
}