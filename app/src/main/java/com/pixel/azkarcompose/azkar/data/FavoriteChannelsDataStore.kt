package com.pixel.azkarcompose.azkar.data

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "favorite_channels")

class FavoriteChannelsDataStore(
    context: Context,
) {
    companion object {
        private val FAVORITE_CHANNELS_KEY = stringSetPreferencesKey("favoriteChannelsId")
    }

    private val dataStore = context.dataStore

    val favoriteChannelsFLow: Flow<Set<String>> =
        dataStore.data.map { preference ->
            preference[FAVORITE_CHANNELS_KEY] ?: emptySet()
        }

    suspend fun saveFavorite(channelId: Int) {
        dataStore.edit { preferences ->
            val currentFavorites = preferences[FAVORITE_CHANNELS_KEY] ?: emptySet()
            preferences[FAVORITE_CHANNELS_KEY] = currentFavorites + channelId.toString()
        }
    }

    suspend fun removeFavorite(channelId: Int) {
        dataStore.edit { preference ->
            val currentFavorite = preference[FAVORITE_CHANNELS_KEY] ?: emptySet()
            preference[FAVORITE_CHANNELS_KEY] = currentFavorite - channelId.toString()
        }
    }
}
