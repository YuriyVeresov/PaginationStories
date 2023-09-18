package ru.veresov.paginationstories.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.veresov.paginationstories.data.database.entity.StoryEntity

/**
 * @author Veresov Yuriy
 * @date 14.09.2023
 */
@Dao
interface StoryEntityDao {
    @Update
    suspend fun update(entity: StoryEntity)

    @Query("select * from story")
    fun readStories(): Flow<List<StoryEntity>>

    @Query("select * from story where preview = :preview")
    suspend fun getStoryBy(preview: String): StoryEntity?

    @Insert
    suspend fun insert(entity: StoryEntity)
}