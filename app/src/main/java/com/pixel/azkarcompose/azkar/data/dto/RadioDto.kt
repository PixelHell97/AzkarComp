package com.pixel.azkarcompose.azkar.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class RadioDto(
    val id: Int,
    val name: String,
    val url: String,
)
