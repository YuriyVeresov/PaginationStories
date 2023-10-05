package ru.veresov.paginationstories.ui.screen

import androidx.lifecycle.ViewModel
import ru.veresov.paginationstories.data.database.model.Story
import ru.veresov.paginationstories.data.database.repository.StoryRepository

/**
 * @author Veresov Yuriy
 * @date 02.10.2023
 */
class StoryScreenViewModel(
    private val repository: StoryRepository
) : ViewModel() {

    suspend fun markStoryAsViewed(story: Story) {
        val key = story.preview
        repository.markStoryAsViewed(key)
    }

    fun getStories() = repository.stories

}