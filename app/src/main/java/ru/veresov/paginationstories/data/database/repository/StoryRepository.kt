package ru.veresov.paginationstories.data.database.repository

import ru.veresov.paginationstories.data.database.dao.StoryEntityDao
import ru.veresov.paginationstories.data.database.entity.StoryEntity
import ru.veresov.paginationstories.model.Story

/**
 * @author Veresov Yuriy
 * @date 15.09.2023
 */
class StoryRepository(
    private val dao: StoryEntityDao
) {

    fun getStoriesFlow() = dao.readStories()

    suspend fun insert(story: Story) {
        dao.insert(StoryEntity(0, story.preview, story.content, story.isViewed))
    }

    suspend fun markStoryAsViewed(preview: String) {
        val story = dao.getStoryBy(preview)
        if (story != null) {
            dao.update(story.copy(isViewed = true))
        }
    }
}