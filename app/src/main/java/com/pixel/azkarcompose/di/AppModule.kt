package com.pixel.azkarcompose.di

import com.pixel.azkarcompose.azkar.presentation.radio.RadioViewModel
import com.pixel.azkarcompose.azkar.presentation.sebha.SebhaViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule =
    module {

        viewModelOf(::SebhaViewModel)
        viewModelOf(::RadioViewModel)
    }
