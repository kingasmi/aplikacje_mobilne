package com.example.lab9.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.lab9.screens.detals.DetailsScreen
import com.example.lab9.screens.home.HomeScreen
import com.example.lab9.navigation.MovieNavigation


@Preview
@Composable
fun MovieNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MovieScreens.HomeScreen.name) {
        composable(MovieScreens.HomeScreen.name) {
            HomeScreen(navController = navController)
        }
        composable(
            MovieScreens.DetailsScreen.name+"/{move}",
            arguments = listOf(
                navArgument(name="move") {
                    type = NavType.IntType
                }
            )
        ) {
                backStackEntry ->
            DetailsScreen(navController = navController, backStackEntry.arguments?.getInt("move"))
        }
    }
}