package com.example.lab9.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.*
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.lab9.model.Movie
import com.example.lab9.model.getMovies


@Preview
@Composable
fun MovieRow(movie: Movie = getMovies()[0], onItemClick: (String) -> Unit = {}) {
    var expanded by remember {
        mutableStateOf(false)
    }
    val density = LocalDensity.current
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(movie.id.toString())
            },
    shape = RoundedCornerShape(corner = CornerSize(16.dp)), elevation = 6.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){
            Surface(modifier = Modifier
                .padding(12.dp)
                .size(100.dp),
                shape = RectangleShape,
                elevation = 4.dp
            ) {
                val painter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .size(coil.size.Size.ORIGINAL)
                       .transformations(CircleCropTransformation())
                        .crossfade(false) //set the target size to load the image at.
                        .build()
                )
                Image(painter = painter, contentDescription = "Movie Poster")
            }
            Column(modifier = Modifier.padding(4.dp)) {
                Text(text = movie.title, style = MaterialTheme.typography.h6)
                Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.caption)
                Text(text = "Released: ${movie.year}", style = MaterialTheme.typography.caption)
                AnimatedVisibility(
                    visible = expanded,
                    enter = slideInVertically { //Slide in form 40 dp from the top.
                    with(density){ -40.dp.roundToPx() }
                    } + expandVertically(
                        expandFrom = Alignment.CenterVertically
                    ) + fadeIn(
                        initialAlpha = 0.4f
                    ),
                    exit = slideOutVertically() + shrinkVertically() + fadeOut() ) {
                        Column {
                            Text(buildAnnotatedString {
                                withStyle(style = SpanStyle(color = Color.DarkGray, fontSize = 13.sp)){
                                    append("Plot:")
                                }
                                withStyle(style = SpanStyle(color = Color.Magenta,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.ExtraBold)){
                                    append(movie.plot)
                                }
                                withStyle(style = SpanStyle(color = Color.Blue,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Normal)){
                                    append("\n Mobile kurs4")
                                }
                            }, modifier = Modifier.padding(6.dp))
                            Divider(modifier = Modifier.padding(3.dp))

                            Text(text = "Director: ${movie.director}", style = MaterialTheme.typography.caption)
                            Text(text = "Actors: ${movie.actors}", style = MaterialTheme.typography.caption)
                        }
                    }
                Icon(
                    imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                    contentDescription = "Down Arrow",
                    Modifier
                        .size(25.dp)
                        .clickable { expanded = !expanded }, tint = Color.LightGray
                )
            }
        }

    }
}