package ru.veresov.paginationstories.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.veresov.paginationstories.data.database.model.Story

/**
 * @author Veresov Yuriy
 * @date 14.09.2023
 */
@Entity(tableName = "story")
data class StoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "preview") val preview: String,
    @ColumnInfo(name = "content") val content: List<String>,
    @ColumnInfo(name = "is_viewed") var isViewed: Boolean = false
) {
    fun toStory() = Story(preview, content, isViewed)
}
