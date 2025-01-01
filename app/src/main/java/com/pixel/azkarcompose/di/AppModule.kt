package com.pixel.azkarcompose.di

import com.pixel.azkarcompose.azkar.data.FavoriteChannelsDataStore
import com.pixel.azkarcompose.azkar.data.RemoteRadioDataSource
import com.pixel.azkarcompose.azkar.domain.RadioDataSource
import com.pixel.azkarcompose.azkar.presentation.radio.RadioViewModel
import com.pixel.azkarcompose.azkar.presentation.sebha.SebhaViewModel
import com.pixel.azkarcompose.core.data.networking.HttpClientFactory
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule =
    module {
        single { HttpClientFactory.create(CIO.create()) }
        singleOf(::RemoteRadioDataSource).bind<RadioDataSource>()
        singleOf(::FavoriteChannelsDataStore)

        viewModelOf(::SebhaViewModel)
        viewModelOf(::RadioViewModel)
    }
