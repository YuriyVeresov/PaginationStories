package ru.veresov.paginationstories.di

import android.app.Application
import androidx.room.Room
import org.koin.dsl.module
import ru.veresov.paginationstories.R
import ru.veresov.paginationstories.data.database.AppDatabase

/**
 * @author Veresov Yuriy
 * @date 25.09.2023
 */

val databaseModule = module {
    single { createDatabase(get()) }
}

private fun createDatabase(app: Application) = Room.databaseBuilder(
    app,
    AppDatabase::class.java,
    app.getString(R.string.app_database)
).createFromAsset("app_database.db").build()