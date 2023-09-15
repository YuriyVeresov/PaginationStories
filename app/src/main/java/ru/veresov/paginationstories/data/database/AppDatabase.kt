package ru.veresov.paginationstories.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.veresov.paginationstories.data.database.converter.StringListConverter
import ru.veresov.paginationstories.data.database.dao.StoryEntityDao
import ru.veresov.paginationstories.data.database.entity.StoryEntity

/**
 * @author Veresov Yuriy
 * @date 14.09.2023
 */
@Database(
    entities = [StoryEntity::class],
    version = 1
)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getStoryDao(): StoryEntityDao

}