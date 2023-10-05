package ru.veresov.paginationstories.di

import org.koin.dsl.module
import ru.veresov.paginationstories.data.database.repository.StoryRepository

/**
 * @author Veresov Yuriy
 * @date 25.09.2023
 */
val repositoryModule = module {
    single { StoryRepository(get()) }
}