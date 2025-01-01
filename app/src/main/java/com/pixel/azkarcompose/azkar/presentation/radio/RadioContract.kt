package com.pixel.azkarcompose.azkar.presentation.radio

import com.pixel.azkarcompose.azkar.presentation.models.RadioChannelUi
import com.pixel.azkarcompose.core.domain.util.NetworkError

/**
 * UI State that represents RadioScreen
 **/

data class RadioState(
    val isLoading: Boolean = true,
    val radioChannels: List<RadioChannelUi> = emptyList(),
    val currentPlayingChannel: RadioChannelUi? = null,
)

/*
* UI Event that represents RadioScreen
**/

sealed interface RadioChannelEvent {
    data class Error(
        val error: NetworkError,
    ) : RadioChannelEvent
}

/**
 * Radio Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface RadioAction {
    data class OnPlay(
        val radioChannel: RadioChannelUi,
    ) : RadioAction

    data object OnStop : RadioAction

    data class OnFavorite(
        val radioChannel: RadioChannelUi,
    ) : RadioAction

    data object OnGetAllRadioChannels : RadioAction

    data object OnGetFavoriteRadioChannels : RadioAction

    data object ReFetchChannels : RadioAction
}
