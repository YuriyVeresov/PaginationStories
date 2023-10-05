package ru.veresov.paginationstories.data.database.repository

import ru.veresov.paginationstories.data.database.dao.StoryEntityDao
import ru.veresov.paginationstories.data.database.model.Story

/**
 * @author Veresov Yuriy
 * @date 15.09.2023
 */
class StoryRepository(
    private val dao: StoryEntityDao
) {
    var stories: List<Story> = listOf()

    fun setList(list: List<Story>) {
        stories = list
    }

    fun getStoriesFlow() = dao.readStories()

    suspend fun markStoryAsViewed(preview: String) {
        val story = dao.getStoryBy(preview)
        if (story != null) {
            dao.update(story.copy(isViewed = true))
        }
    }
}