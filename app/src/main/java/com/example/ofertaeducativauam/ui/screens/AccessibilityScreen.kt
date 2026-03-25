package com.example.ofertaeducativauam.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ofertaeducativauam.ui.viewmodel.AccessibilityViewModel
import com.example.ofertaeducativauam.ui.viewmodel.ColorFilterType
@Composable
fun AccessibilityScreen(viewModel: AccessibilityViewModel) {
    // Observamos el estado reactivo del ViewModel
    val state by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Accesibilidad", style = MaterialTheme.typography.headlineMedium, fontSize = 24.sp * state.textScale)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Personaliza tu experiencia visual.", fontSize = 14.sp * state.textScale)

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        // 1. Control de Tamaño de Texto
        Text(text = "Tamaño de texto", style = MaterialTheme.typography.titleMedium, fontSize = 18.sp * state.textScale)
        Slider(
            value = state.textScale,
            onValueChange = { viewModel.updateTextScale(it) },
            valueRange = 0.8f..1.5f
        )

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        // 2. Control de Modo Oscuro
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Modo Oscuro", style = MaterialTheme.typography.titleMedium, fontSize = 18.sp * state.textScale)
            Switch(
                checked = state.isDarkMode,
                onCheckedChange = { viewModel.toggleDarkMode(it) }
            )
        }

        Divider(modifier = Modifier.padding(vertical = 16.dp))

        // 3. Filtros de Color (Daltonismo)
        Text(text = "Filtros de Color", style = MaterialTheme.typography.titleMedium, fontSize = 18.sp * state.textScale)
        ColorFilterType.values().forEach { filter ->
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
                RadioButton(
                    selected = state.colorFilter == filter,
                    onClick = { viewModel.updateColorFilter(filter) }
                )
                Text(text = filter.name, fontSize = 16.sp * state.textScale)
            }
        }
    }
}