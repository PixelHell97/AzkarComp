package com.pixel.azkarcompose.azkar.presentation.sebha

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SebhaViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {
    private val _stateFlow: MutableStateFlow<SebhaState> = MutableStateFlow(SebhaState())

    val stateFlow: StateFlow<SebhaState> = _stateFlow.asStateFlow()

    fun tasbeh() {
        Log.e("viewModel", "Got into tasbeh fun")
        if (_stateFlow.value.sebhaCurrentCount < 33) {
            _stateFlow.update {
                it.copy(
                    sebhaCurrentCount = _stateFlow.value.sebhaCurrentCount + 1,
                    degreeRotation = _stateFlow.value.degreeRotation + 11f,
                )
            }
            Log.e("viewModel", "${_stateFlow.value.sebhaCurrentCount}")
        } else if (_stateFlow.value.sebhaCurrentCount == 33) {
            _stateFlow.update {
                it.copy(
                    sebhaCurrentCount = 1,
                    sebhaCurrentSpinCount = _stateFlow.value.sebhaCurrentSpinCount + 1,
                    degreeRotation = _stateFlow.value.degreeRotation + 11f,
                )
            }
        }

        if (_stateFlow.value.sebhaCurrentSpinCount == 4) {
            _stateFlow.update {
                it.copy(
                    sebhaCurrentSpinCount = 0,
                )
            }
        }
    }
}
