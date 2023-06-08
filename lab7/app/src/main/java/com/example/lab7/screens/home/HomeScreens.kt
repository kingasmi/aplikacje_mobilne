package com.example.lab7.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lab7.MovieRow
import com.example.lab7.navigation.MovieScreens

@Composable
fun HomeScreens(navController: NavController) {
    androidx.compose.material.Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Magenta, elevation = 5.dp) {
                androidx.compose.material.Text(text = "Movies TopAppBar")
            }
        },
        content = {
            Column(modifier = Modifier.padding(it)) {
                MainContent(navController = navController)
            }
        }
    )
}


@Composable
fun MainContent(
    navController: NavController,
    moviesList: List<String> = listOf(
        "Avatar",
        "555",
        "Harry Potter",
        "Life",
        "Lolek",
        "Bolek",
        "Krecik"
    )
) {
    LazyColumn {
        items(items = moviesList) { movie ->
            MovieRow(movie = movie) { movie2 ->
                navController.navigate(route = "${MovieScreens.DetailsScreen.name}/$movie2")
            }
        }
    }
}
