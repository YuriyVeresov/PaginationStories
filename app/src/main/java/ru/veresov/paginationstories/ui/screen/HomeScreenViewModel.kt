package ru.veresov.paginationstories.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.veresov.paginationstories.data.database.model.Story
import ru.veresov.paginationstories.data.database.repository.StoryRepository

/**
 * @author Veresov Yuriy
 * @date 02.10.2023
 */
class HomeScreenViewModel(
    private val repository: StoryRepository
) : ViewModel() {

    private val _allStories: Flow<List<Story>> = repository.getStoriesFlow()
        .map { storyEntities ->
            storyEntities.map { it.toStory() }
        }
    val allStories get() = _allStories

    fun initStories() {
        viewModelScope.launch {
            _allStories.collect {
                if (repository.stories.isEmpty())
                    repository.setList(it)
            }
        }
    }
}
