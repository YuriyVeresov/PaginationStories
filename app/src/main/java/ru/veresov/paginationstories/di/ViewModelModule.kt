package ru.veresov.paginationstories.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.veresov.paginationstories.ui.screen.HomeScreenViewModel
import ru.veresov.paginationstories.ui.screen.StoryScreenViewModel

/**
 * @author Veresov Yuriy
 * @date 25.09.2023
 */
val viewModelModule = module {
    viewModel { HomeScreenViewModel(get()) }
    viewModel { StoryScreenViewModel(get()) }
}