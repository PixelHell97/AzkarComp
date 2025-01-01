package com.pixel.azkarcompose.app

import android.app.Application
import com.pixel.azkarcompose.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AzkarApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AzkarApp)
            androidLogger()
            modules(
                modules = appModule,
            )
        }
    }
}
