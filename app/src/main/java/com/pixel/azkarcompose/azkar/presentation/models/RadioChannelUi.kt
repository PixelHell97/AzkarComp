package com.pixel.azkarcompose.azkar.presentation.models

import com.pixel.azkarcompose.azkar.domain.models.RadioChannel

data class RadioChannelUi(
    val id: Int,
    val name: String,
    val url: String,
    val isPlaying: Boolean = false,
    val isFavorite: Boolean = false,
    val isMuted: Boolean = false,
)

fun RadioChannel.toRadioChannelUi(): RadioChannelUi =
    RadioChannelUi(
        id = id,
        name = name,
        url = url,
    )
