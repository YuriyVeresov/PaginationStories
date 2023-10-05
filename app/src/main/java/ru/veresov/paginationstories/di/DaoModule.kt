package ru.veresov.paginationstories.di

import org.koin.dsl.module
import ru.veresov.paginationstories.data.database.AppDatabase

/**
 * @author Veresov Yuriy
 * @date 25.09.2023
 */

val daoModule = module {
    single {
        val database = get<AppDatabase>()
        database.getStoryDao()
    }
}