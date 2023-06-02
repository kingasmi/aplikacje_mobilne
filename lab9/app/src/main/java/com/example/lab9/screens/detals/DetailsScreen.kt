package com.example.lab9.screens.detals

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.lab9.model.Movie
import com.example.lab9.model.getMovies
import com.example.lab9.widgets.MovieRow


@SuppressLint("UnusedMaterialScaffoldPaddingParametr")
@Composable
fun DetailsScreen (navController: NavController, movieId: Int?) {
    val newmovieList = getMovies().filter { movie -> movie.id == movieId }
    Scaffold(
        topBar = {
            TopAppBar(backgroundColor = Color.Transparent, elevation = 5.dp) {

                Row(horizontalArrangement = Arrangement.Start) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Arrow Back",
                        Modifier.clickable {
                            navController.popBackStack()
                        })
                    Spacer(modifier = Modifier.width(50.dp))
                    Text(text = "Movies")
                }/*Top app bar content*/
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                MovieRow(movie = newmovieList.first())
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Text(text = "Movie Images")
                HorizintalScrollableImageView(newmovieList)
            }
        }
    }
}

@Composable
fun HorizintalScrollableImageView(newmovieList: List<Movie>) {
    LazyRow {
        items(newmovieList[0].images)
        { image ->
            Card(
                modifier = Modifier
                    .padding(12.dp)
                    .size(240.dp),
                elevation = 5.dp
            )
            {
                val painter = rememberAsyncImagePainter(image)
                val state = painter.state
                if (state is AsyncImagePainter.State.Success) {
                    //Perform the transition animation.
                }
                Image(
                    painter = painter,
                    contentDescription = "Movvie Poster"
                )
            }
        }
    }
}
