package ru.veresov.paginationstories

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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import ru.veresov.paginationstories.ui.screen.HomeScreen
import ru.veresov.paginationstories.ui.screen.StoryScreen
import ru.veresov.paginationstories.ui.theme.PaginationStoriesTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PaginationStoriesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "HomeScreen") {
                        composable("HomeScreen") {
                            HomeScreen { storyIndex ->
                                navController.navigate("StoryScreen/${storyIndex}")
                            }
                        }

                        composable(
                            "StoryScreen/{storyId}",
                            arguments = listOf(navArgument("storyId") { type = NavType.IntType })
                        ) {
                            val index = it.arguments?.getInt("storyId") ?: 0
                            StoryScreen(index, navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PaginationStoriesTheme {
        Greeting("Android")
    }
}