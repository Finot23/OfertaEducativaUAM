package com.example.ofertaeducativauam.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ofertaeducativauam.ui.viewmodel.AccessibilityViewModel
import com.example.ofertaeducativauam.ui.viewmodel.ColorFilterType

@Composable
fun AccessibilityScreen(viewModel: AccessibilityViewModel) {
    val state by viewModel.uiState.collectAsState()
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(
            text = "ACCESIBILIDAD",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Black,
            fontSize = 24.sp * state.textScale
        )
        Text(
            text = "Personaliza la aplicación según tus necesidades visuales.",
            fontSize = 14.sp * state.textScale,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(24.dp))

        // TAMAÑO DE FUENTE
        AccessibilitySection(titulo = "Tamaño de fuente", textScale = state.textScale) {
            Slider(
                value = state.textScale,
                onValueChange = { viewModel.updateTextScale(it) },
                valueRange = 0.8f..1.8f,
                steps = 5
            )
            Text(
                text = "Ejemplo de texto con este tamaño",
                fontSize = 16.sp * state.textScale
            )
        }

        // ESPACIADO DE LÍNEA
        AccessibilitySection(titulo = "Espaciado de línea", textScale = state.textScale) {
            Slider(
                value = state.lineSpacingExtra,
                onValueChange = { viewModel.updateLineSpacing(it) },
                valueRange = 0f..10f
            )
            Text(
                text = "Aumentar el espacio entre renglones facilita la lectura.",
                fontSize = 14.sp * state.textScale,
                lineHeight = (20 + state.lineSpacingExtra).sp
            )
        }

        // Modo Oscuro y Contraste Alto
        AccessibilitySection(titulo = "Visualización", textScale = state.textScale) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Modo Oscuro", fontSize = 16.sp * state.textScale)
                Switch(checked = state.isDarkMode, onCheckedChange = { viewModel.toggleDarkMode(it) })
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text("Contraste Alto", fontSize = 16.sp * state.textScale)
                Switch(checked = state.isHighContrast, onCheckedChange = { viewModel.toggleHighContrast(it) })
            }
        }

        // FILTROS DE DALTONISMO
        AccessibilitySection(titulo = "Filtros de color (Daltonismo)", textScale = state.textScale) {
            ColorFilterType.entries.forEach { filter ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = state.colorFilter == filter,
                        onClick = { viewModel.updateColorFilter(filter) }
                    )
                    Text(
                        text = filter.label,
                        fontSize = 16.sp * state.textScale,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun AccessibilitySection(
    titulo: String,
    textScale: Float,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = titulo.uppercase(),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 14.sp * textScale
            )
            Spacer(modifier = Modifier.height(12.dp))
            content()
        }
    }
}
