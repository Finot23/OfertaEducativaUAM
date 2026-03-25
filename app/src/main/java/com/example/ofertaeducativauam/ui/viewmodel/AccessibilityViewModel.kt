package com.example.ofertaeducativauam.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// Enumerador para los filtros de color requeridos en el mockup
enum class ColorFilterType {
    STANDARD, PROTANOPIA, DEUTERANOPIA, TRITANOPIA
}

data class AccessibilityState(
    val textScale: Float = 1.0f,
    val isDarkMode: Boolean = false,
    val colorFilter: ColorFilterType = ColorFilterType.STANDARD
)

class AccessibilityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AccessibilityState())
    val uiState: StateFlow<AccessibilityState> = _uiState.asStateFlow()

    fun updateTextScale(scale: Float) {
        _uiState.value = _uiState.value.copy(textScale = scale)
    }

    fun toggleDarkMode(isDark: Boolean) {
        _uiState.value = _uiState.value.copy(isDarkMode = isDark)
    }

    fun updateColorFilter(filter: ColorFilterType) {
        _uiState.value = _uiState.value.copy(colorFilter = filter)
    }
}