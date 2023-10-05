package ru.veresov.paginationstories

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.veresov.paginationstories.di.daoModule
import ru.veresov.paginationstories.di.databaseModule
import ru.veresov.paginationstories.di.repositoryModule
import ru.veresov.paginationstories.di.viewModelModule

/**
 * @author Veresov Yuriy
 * @date 15.09.2023
 */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(repositoryModule, viewModelModule, databaseModule, daoModule)
        }
    }
}

