package com.pixel.azkarcompose.azkar.presentation.radio

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun RadioRoute(coordinator: RadioCoordinator = rememberRadioCoordinator()) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(RadioState())

    val uiEvent = coordinator.screenEvent

    // UI Actions
    val actionsHandler: (RadioAction) -> Unit = { action ->
        coordinator.handle(action)
    }

    // UI Rendering
    RadioScreen(
        state = uiState,
        event = uiEvent,
        onAction = actionsHandler,
    )
}
