package com.pixel.azkarcompose.azkar.presentation.radio

import android.media.MediaPlayer

class RadioPlayer {
    private var mediaPlayer: MediaPlayer? = null

    fun play(channelUrl: String) {
        stop() // Stop the current player before starting a new one
        mediaPlayer =
            MediaPlayer().apply {
                setDataSource(channelUrl)
                prepare()
                start()
            }
    }

    fun stop() {
        mediaPlayer?.apply {
            stop()
            release()
        }
        mediaPlayer = null
    }
}
