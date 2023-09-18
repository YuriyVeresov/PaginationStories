package ru.veresov.paginationstories.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.veresov.paginationstories.R
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

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        context.getString(R.string.app_database)
                    ).build()
                }
                return instance
            }
        }
    }

}