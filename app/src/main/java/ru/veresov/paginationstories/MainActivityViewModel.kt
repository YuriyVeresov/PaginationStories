package ru.veresov.paginationstories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.veresov.paginationstories.data.database.repository.StoryRepository
import ru.veresov.paginationstories.model.Story

/**
 * @author Veresov Yuriy
 * @date 15.09.2023
 */
class MainActivityViewModel(
    private val repository: StoryRepository
) : ViewModel() {

    private val _allStories: Flow<List<Story>> = repository.getStoriesFlow()
        .map { storyEntities ->
            storyEntities.map { it.toStory() }
        }
    val allStories get() = _allStories

    fun markStoryAsViewed(preview: String) {
        viewModelScope.launch {
            repository.markStoryAsViewed(preview)
        }
    }

    fun insert(story: Story) {
        viewModelScope.launch {
            repository.insert(story)
        }
    }
}