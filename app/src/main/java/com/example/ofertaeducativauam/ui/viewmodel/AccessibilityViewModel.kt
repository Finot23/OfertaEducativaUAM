package com.example.ofertaeducativauam.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

enum class ColorFilterType(val label: String) {
    STANDARD("Normal"),
    PROTANOPIA("Protanopia (Rojo)"),
    DEUTERANOPIA("Deuteranopia (Verde)"),
    TRITANOPIA("Tritanopia (Azul)")
}

data class AccessibilityState(
    val textScale: Float = 1.0f,
    val lineSpacingExtra: Float = 0f, // Recomendación: Espaciado extra
    val isDarkMode: Boolean = false,
    val isHighContrast: Boolean = false, // Recomendación: Contraste alto
    val colorFilter: ColorFilterType = ColorFilterType.STANDARD
)

class AccessibilityViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(AccessibilityState())
    val uiState: StateFlow<AccessibilityState> = _uiState.asStateFlow()

    fun updateTextScale(scale: Float) {
        _uiState.value = _uiState.value.copy(textScale = scale)
    }

    fun updateLineSpacing(extra: Float) {
        _uiState.value = _uiState.value.copy(lineSpacingExtra = extra)
    }

    fun toggleDarkMode(isDark: Boolean) {
        _uiState.value = _uiState.value.copy(isDarkMode = isDark)
    }

    fun toggleHighContrast(isHigh: Boolean) {
        _uiState.value = _uiState.value.copy(isHighContrast = isHigh)
    }

    fun updateColorFilter(filter: ColorFilterType) {
        _uiState.value = _uiState.value.copy(colorFilter = filter)
    }
}
