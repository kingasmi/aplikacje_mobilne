package com.example.lab9.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.lab9.navigation.MovieScreens
import com.example.lab9.widgets.MovieRow
import com.example.lab9.model.getMovies
import com.example.lab9.model.Movie

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Magenta, elevation = 5.dp) {
                Text(text = "Movies TopAppBar")
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
    moviesList: List<Movie> = getMovies()
) {
    LazyColumn {
        items(items = moviesList) { movie ->
            MovieRow(movie = movie) { movie2 ->
                navController.navigate(route = "${MovieScreens.DetailsScreen.name}/$movie2")
            }
        }
    }
}