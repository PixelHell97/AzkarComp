package com.pixel.azkarcompose.azkar.domain

import com.pixel.azkarcompose.azkar.domain.models.RadioChannel
import com.pixel.azkarcompose.core.domain.util.NetworkError
import com.pixel.azkarcompose.core.domain.util.Result

interface RadioDataSource {
    suspend fun getRadioChannels(): Result<List<RadioChannel>, NetworkError>
}
