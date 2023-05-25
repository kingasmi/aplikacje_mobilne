package com.example.lab9
//kurs 4
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.lab9.navigation.MovieNavigation
import com.example.lab9.ui.theme.Lab9Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp{
                MovieNavigation()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    Lab9Theme {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MovieNavigation()
    }
}