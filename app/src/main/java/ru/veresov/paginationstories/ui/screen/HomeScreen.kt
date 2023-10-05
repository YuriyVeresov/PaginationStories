package ru.veresov.paginationstories.ui.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import ru.veresov.paginationstories.ui.model.StoryItem

/**
 * @author Veresov Yuriy
 * @date 02.10.2023
 */
@Composable
fun HomeScreen(onStoryClick: (Int) -> Unit) {
    val viewModel: HomeScreenViewModel = koinViewModel()
    val stories = viewModel.allStories.collectAsState(initial = listOf())

    LazyRow(modifier = Modifier.padding(vertical = 8.dp), content = {
        itemsIndexed(stories.value) { index, item ->
            if (index == 0)
                Spacer(modifier = Modifier.padding(start = 8.dp))
            StoryItem(modifier = Modifier, item) {
                onStoryClick(index)
            }
        }
    })
    viewModel.initStories()
}