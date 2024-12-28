package com.pixel.azkarcompose.azkar.presentation.sebha

/**
 * UI State that represents SebhaScreen
 **/
data class SebhaState(
    var sebhaCurrentCount: Int = 0,
    var sebhaCurrentSpinCount: Int = 0,
    var degreeRotation: Float = 0f,
)

/**
 * Sebha Actions emitted from the UI Layer
 * passed to the coordinator to handle
 **/

sealed interface SebhaAction {
    data object OnClick : SebhaAction
}
