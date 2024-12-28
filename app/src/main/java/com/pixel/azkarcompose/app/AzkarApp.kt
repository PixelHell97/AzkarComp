package com.pixel.azkarcompose.app

import android.app.Application
import com.pixel.azkarcompose.azkar.presentation.sebha.SebhaViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.dsl.module

class AzkarApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AzkarApp)
            androidLogger()
            modules(
                modules =
                    module {
                        viewModelOf(::SebhaViewModel)
                    },
            )
        }
    }
}
