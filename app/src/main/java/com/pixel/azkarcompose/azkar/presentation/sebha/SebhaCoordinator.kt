package com.pixel.azkarcompose.azkar.presentation.sebha

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.koin.androidx.compose.koinViewModel

/**
 * Screen's coordinator which is responsible for handling actions from the UI layer
 * and one-shot actions based on the new UI state
 */
class SebhaCoordinator(
    val viewModel: SebhaViewModel,
) {
    val screenStateFlow = viewModel.stateFlow

    fun handle(action: SebhaAction) {
        when (action) {
            SebhaAction.OnClick -> { // Handle action
                Log.e("Coordinator", "Got Into onClick handle fun")
                viewModel.tasbeh()
            }
        }
    }
}

@Composable
fun rememberSebhaCoordinator(viewModel: SebhaViewModel = koinViewModel()): SebhaCoordinator =
    remember(viewModel) {
        SebhaCoordinator(
            viewModel = viewModel,
        )
    }
