package com.pixel.azkarcompose.azkar.presentation.radio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.androidx.compose.koinViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class RadioCoordinator(
    val viewModel: RadioViewModel,
) {
    val screenStateFlow = viewModel.stateFlow
    val screenEvent = viewModel.event

    fun handle(action: RadioAction) {
        when (action) {
            is RadioAction.OnPlay -> {
                viewModel.playChannel(action.radioChannel)
            }
            RadioAction.OnStop -> {
                viewModel.stopPlaying()
            }
            is RadioAction.OnFavorite -> viewModel.toggleFavorite(action.radioChannel)
            RadioAction.OnGetFavoriteRadioChannels -> {
                viewModel.getFavoriteChannels()
            }
            RadioAction.OnGetAllRadioChannels -> {
                viewModel.getAllRadioChannels()
            }

            RadioAction.ReFetchChannels -> viewModel.fetchRadioChannels()
        }
    }
}

@Composable
fun rememberRadioCoordinator(viewModel: RadioViewModel = koinViewModel()): RadioCoordinator =
    remember(viewModel) {
        RadioCoordinator(
            viewModel = viewModel,
        )
    }
