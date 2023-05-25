package com.example.lab7.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.lab7.screens.home.HomeScreens

@Preview
@Composable
fun MovieNavigation (){
    val navController = rememberNavController()
    NavHost(navController = navController,
    startDestination = MovieScreens.HomeScreen.name ){
        composable(MovieScreens.HomeScreen.name){
            HomeScreens(navController = navController)
        }
    }
}