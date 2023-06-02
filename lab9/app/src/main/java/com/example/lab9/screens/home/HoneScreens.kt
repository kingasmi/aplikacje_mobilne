package com.example.lab9.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.lab9.model.Movie
import com.example.lab9.model.getMovies
import com.example.lab9.navigation.MovieScreens
import com.example.lab9.widgets.MovieRow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Magenta, elevation = 5.dp) {
                Text(text = "Movies")
                /*Top app bar content*/
            }
        }
    ) { it->Column(
        modifier = Modifier
            .padding(it))
    { MainContent(navController=navController)
        //widok
    }
    }
}


@Composable
fun MainContent(navController: NavController,
                moviesList: List<Movie> = getMovies()
){
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn{
            items(items = moviesList){
                MovieRow(movie = it){
                        movie ->navController.navigate(route = MovieScreens.DetailsScreen.name+"/$movie")}
            }
        }

    }
}