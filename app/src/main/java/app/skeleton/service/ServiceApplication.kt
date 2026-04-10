package app.skeleton.service

import android.app.Application
import app.skeleton.service.di.dataModule
import app.skeleton.service.di.dispatcherModule
import app.skeleton.service.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class ServiceApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val appModules = dataModule + viewModule + dispatcherModule

        startKoin {
            androidLogger()
            androidContext(this@ServiceApplication)
            modules(appModules)
        }
    }
}