package com.example.ofertaeducativauam.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

private val DarkColorScheme = darkColorScheme(
    primary = UamOrangeDark,
    secondary = UamGreyLight,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = Color.Black,
    onBackground = Color.White,
    onSurface = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = UamOrange,
    onPrimary = Color.White,
    primaryContainer = UamOrange.copy(alpha = 0.1f),
    secondary = UamGrey,
    onSecondary = Color.White,
    background = UamBone,
    onBackground = UamBlack,
    surface = UamWhite,
    onSurface = UamBlack,
    surfaceVariant = Color(0xFFE0E0E0)
)

private val HighContrastLightSelection = lightColorScheme(
    primary = Color.Black,
    onPrimary = Color.White,
    background = Color.White,
    onBackground = Color.Black,
    surface = Color.White,
    onSurface = Color.Black,
    outline = Color.Black
)

private val HighContrastDarkSelection = darkColorScheme(
    primary = Color.White,
    onPrimary = Color.Black,
    background = Color.Black,
    onBackground = Color.White,
    surface = Color.Black,
    onSurface = Color.White,
    outline = Color.White
)

@Composable
fun OfertaEducativaUAMTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    isHighContrast: Boolean = false,
    lineSpacingExtra: Float = 0f,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        isHighContrast && darkTheme -> HighContrastDarkSelection
        isHighContrast && !darkTheme -> HighContrastLightSelection
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    // Aplicar espaciado dinámico a los estilos principales
    val dynamicTypography = Typography.copy(
        bodyLarge = Typography.bodyLarge.copy(lineHeight = (Typography.bodyLarge.lineHeight.value + lineSpacingExtra).sp),
        bodyMedium = Typography.bodyMedium.copy(lineHeight = (Typography.bodyMedium.lineHeight.value + lineSpacingExtra).sp),
        bodySmall = Typography.bodySmall.copy(lineHeight = (Typography.bodySmall.lineHeight.value + lineSpacingExtra).sp),
        headlineLarge = Typography.headlineLarge.copy(lineHeight = (Typography.headlineLarge.lineHeight.value + lineSpacingExtra).sp),
        headlineMedium = Typography.headlineMedium.copy(lineHeight = (Typography.headlineMedium.lineHeight.value + lineSpacingExtra).sp),
        titleLarge = Typography.titleLarge.copy(lineHeight = (Typography.titleLarge.lineHeight.value + lineSpacingExtra).sp),
        titleMedium = Typography.titleMedium.copy(lineHeight = (Typography.titleMedium.lineHeight.value + lineSpacingExtra).sp)
    )

    MaterialTheme(
        colorScheme = colorScheme,
        typography = dynamicTypography,
        content = content
    )
}
