package com.pixel.azkarcompose.azkar.presentation.radio

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pixel.azkarcompose.azkar.data.FavoriteChannelsDataStore
import com.pixel.azkarcompose.azkar.domain.RadioDataSource
import com.pixel.azkarcompose.azkar.presentation.models.RadioChannelUi
import com.pixel.azkarcompose.azkar.presentation.models.toRadioChannelUi
import com.pixel.azkarcompose.core.domain.util.onError
import com.pixel.azkarcompose.core.domain.util.onSuccess
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RadioViewModel(
    savedStateHandle: SavedStateHandle,
    private val radioDataSource: RadioDataSource,
    private val favoriteChannelsDataStore: FavoriteChannelsDataStore,
) : ViewModel() {
    private val radioPlayer = RadioPlayer()

    private val _stateFlow: MutableStateFlow<RadioState> = MutableStateFlow(RadioState())

    val stateFlow: StateFlow<RadioState> =
        _stateFlow
            .onStart {
                fetchRadioChannels()
                initFavoriteChannels()
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000L),
                initialValue = RadioState(),
            )

    private val _event = Channel<RadioChannelEvent>()
    val event = _event.receiveAsFlow()

    private val loadedRadioChannels: MutableStateFlow<List<RadioChannelUi>> =
        MutableStateFlow(
            emptyList(),
        )
    private var favoriteChannelIds: Set<String> = emptySet()

    private fun initFavoriteChannels() {
        viewModelScope.launch {
            favoriteChannelsDataStore.favoriteChannelsFLow.collect { ids ->
                favoriteChannelIds = ids
            }
        }
    }

    fun toggleFavorite(channel: RadioChannelUi) {
        viewModelScope
            .launch {
                if (channel.id.toString() in favoriteChannelIds) {
                    favoriteChannelsDataStore.removeFavorite(channel.id)
                } else {
                    favoriteChannelsDataStore.saveFavorite(channel.id)
                }
                _stateFlow.update { state ->
                    val updatedChannels =
                        state.radioChannels.map {
                            it.copy(
                                isFavorite = (it.id.toString() in favoriteChannelIds),
                            )
                        }
                    state.copy(
                        radioChannels = updatedChannels,
                    )
                }
            }
    }

    fun playChannel(currentPlayingChannel: RadioChannelUi) {
        _stateFlow.update { state ->
            val updatedChannels =
                state.radioChannels.map { it.copy(isPlaying = it == currentPlayingChannel) }
            state.copy(
                radioChannels = updatedChannels,
                currentPlayingChannel = currentPlayingChannel,
            )
        }
        radioPlayer.play(currentPlayingChannel.url)
    }

    fun stopPlaying() {
        _stateFlow.update { state ->
            val updatedChannels = state.radioChannels.map { it.copy(isPlaying = false) }
            state.copy(
                radioChannels = updatedChannels,
                currentPlayingChannel = null,
            )
        }
        radioPlayer.stop()
    }

    fun getFavoriteChannels() {
        _stateFlow.update { state ->
            val updatedChannels =
                loadedRadioChannels.value
                    .filter { channel ->
                        (channel.id.toString() in favoriteChannelIds)
                    }.map {
                        it.copy(
                            isFavorite = true,
                            isPlaying = it.id == _stateFlow.value.currentPlayingChannel?.id,
                        )
                    }
            state.copy(
                radioChannels = updatedChannels,
            )
        }
    }

    fun getAllRadioChannels() {
        _stateFlow.update { state ->
            state.copy(
                isLoading = false,
                radioChannels =
                    loadedRadioChannels.value.map {
                        it.copy(
                            isFavorite = (it.id.toString() in favoriteChannelIds),
                            isPlaying = it.id == state.currentPlayingChannel?.id,
                        )
                    },
            )
        }
    }

    fun fetchRadioChannels() {
        viewModelScope.launch {
            Log.e("radioVM", "start fetching the radio channels")
            radioDataSource
                .getRadioChannels()
                .onSuccess { radioChannels ->
                    Log.e("radioVM", "Load success $radioChannels")
                    loadedRadioChannels.update {
                        radioChannels.map { it.toRadioChannelUi() }
                    }
                    Log.e("radioVM", "${loadedRadioChannels.value}")
                    getAllRadioChannels()
                }.onError { error ->
                    Log.e("radioVM", "Load Error $error")
                    _event.send(RadioChannelEvent.Error(error))
                }
        }
    }
}
