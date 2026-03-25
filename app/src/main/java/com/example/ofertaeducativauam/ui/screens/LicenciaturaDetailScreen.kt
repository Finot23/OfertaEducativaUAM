package com.example.ofertaeducativauam.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ofertaeducativauam.data.Licenciatura
import com.example.ofertaeducativauam.ui.viewmodel.AccessibilityState

@Composable
fun LicenciaturaDetailScreen(
    licenciatura: Licenciatura,
    accessibilityState: AccessibilityState
) {
    // Variables de estado para la divulgación progresiva (Acordeones)
    var expandirPerfil by remember { mutableStateOf(false) }
    var expandirPlan by remember { mutableStateOf(false) }
    var expandirCampo by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        Text(
            text = licenciatura.nombre,
            style = MaterialTheme.typography.headlineLarge,
            fontSize = 28.sp * accessibilityState.textScale
        )
        Text(
            text = "División de ${licenciatura.division.nombreCompleto}",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 16.sp * accessibilityState.textScale
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Información General (Visible por defecto)
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("Información General", fontWeight = androidx.compose.ui.text.font.FontWeight.Bold, fontSize = 18.sp * accessibilityState.textScale)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Duración: ${licenciatura.duracion}", fontSize = 16.sp * accessibilityState.textScale)
                Text("Modalidad: ${licenciatura.modalidad}", fontSize = 16.sp * accessibilityState.textScale)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Acordeón: Perfil de Ingreso
        AcordeonInfo(
            titulo = "Perfil del Candidato",
            contenido = licenciatura.perfilIngreso,
            expandido = expandirPerfil,
            onToggle = { expandirPerfil = !expandirPerfil },
            accessibilityState = accessibilityState
        )

        // Acordeón: Plan de Estudios
        AcordeonInfo(
            titulo = "Plan de Estudios",
            contenido = licenciatura.planEstudios,
            expandido = expandirPlan,
            onToggle = { expandirPlan = !expandirPlan },
            accessibilityState = accessibilityState
        )

        // Acordeón: Campo Laboral
        AcordeonInfo(
            titulo = "Campo Laboral",
            contenido = licenciatura.campoLaboral,
            expandido = expandirCampo,
            onToggle = { expandirCampo = !expandirCampo },
            accessibilityState = accessibilityState
        )
    }
}

// Componente reutilizable para reducir la saturación cognitiva
@Composable
fun AcordeonInfo(
    titulo: String,
    contenido: String,
    expandido: Boolean,
    onToggle: () -> Unit,
    accessibilityState: AccessibilityState
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onToggle() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = titulo,
                    style = MaterialTheme.typography.titleMedium,
                    fontSize = 18.sp * accessibilityState.textScale
                )
                Icon(
                    imageVector = if (expandido) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (expandido) "Ocultar $titulo" else "Mostrar $titulo"
                )
            }

            // AnimatedVisibility permite una transición suave al mostrar el contenido
            AnimatedVisibility(visible = expandido) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    Divider()
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = contenido,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 16.sp * accessibilityState.textScale
                    )
                }
            }
        }
    }
}