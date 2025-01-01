package com.pixel.azkarcompose.azkar.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class RadioResponseDto(
    val radios: List<RadioDto>,
)
