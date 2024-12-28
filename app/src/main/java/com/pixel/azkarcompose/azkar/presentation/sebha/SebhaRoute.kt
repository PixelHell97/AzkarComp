package com.pixel.azkarcompose.azkar.presentation.sebha

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue

@Composable
fun SebhaRoute(coordinator: SebhaCoordinator = rememberSebhaCoordinator()) {
    // State observing and declarations
    val uiState by coordinator.screenStateFlow.collectAsState(SebhaState())

    // UI Actions
    val actionsHandler: (SebhaAction) -> Unit = { action ->
        Log.e("Route", "$uiState")
        coordinator.handle(action)
    }

    // UI Rendering
    SebhaScreen(
        state = uiState,
        onAction = actionsHandler,
    )
}
