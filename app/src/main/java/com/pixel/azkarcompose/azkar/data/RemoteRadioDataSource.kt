package com.pixel.azkarcompose.azkar.data

import com.pixel.azkarcompose.BuildConfig.RADIO_API_URL
import com.pixel.azkarcompose.azkar.data.dto.RadioResponseDto
import com.pixel.azkarcompose.azkar.domain.RadioDataSource
import com.pixel.azkarcompose.azkar.domain.models.RadioChannel
import com.pixel.azkarcompose.core.data.mappers.toRadioChannel
import com.pixel.azkarcompose.core.data.networking.safeCall
import com.pixel.azkarcompose.core.domain.util.NetworkError
import com.pixel.azkarcompose.core.domain.util.Result
import com.pixel.azkarcompose.core.domain.util.map
import io.ktor.client.HttpClient
import io.ktor.client.request.get

class RemoteRadioDataSource(
    private val httpClient: HttpClient,
) : RadioDataSource {
    override suspend fun getRadioChannels(): Result<List<RadioChannel>, NetworkError> =
        safeCall<RadioResponseDto> {
            httpClient.get(
                urlString = RADIO_API_URL,
            )
        }.map { response ->
            response.radios.map { it.toRadioChannel() }
        }
}
