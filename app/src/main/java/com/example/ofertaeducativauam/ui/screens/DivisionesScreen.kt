package com.example.ofertaeducativauam.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ofertaeducativauam.data.Division
import com.example.ofertaeducativauam.ui.viewmodel.AccessibilityState

@Composable
fun DivisionesScreen(
    accessibilityState: AccessibilityState,
    onDivisionSelected: (Division) -> Unit
) {
    val scrollState = rememberScrollState()
    val divisiones = Division.entries

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background)
    ) {
        // 1. ENCABEZADO UNIFICADO (Mismo estilo que Licenciaturas y Detalles)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(vertical = 32.dp, horizontal = 16.dp)
        ) {
            Text(
                text = "DIVISIONES ACADÉMICAS",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Black,
                fontSize = 26.sp * accessibilityState.textScale,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // 2. CONTENIDO CON PADDING
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "UAM Cuajimalpa ofrece una oferta educativa de vanguardia dividida en tres grandes áreas del conocimiento. Elige tu camino de aprendizaje:",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp * accessibilityState.textScale,
                lineHeight = (22 + accessibilityState.lineSpacingExtra).sp,
                color = MaterialTheme.colorScheme.onBackground
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Cartas de divisiones
            divisiones.forEach { division ->
                DivisionCard(
                    division = division,
                    accessibilityState = accessibilityState,
                    onClick = { onDivisionSelected(division) }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun DivisionCard(
    division: Division,
    accessibilityState: AccessibilityState,
    onClick: () -> Unit
) {
    val cardColor = if (accessibilityState.isHighContrast) {
        MaterialTheme.colorScheme.surface
    } else {
        when(division) {
            Division.CCD -> Color(0xFF006064) 
            Division.CNI -> Color(0xFF33691E) 
            Division.CSH -> Color(0xFF1A237E) 
        }
    }

    val contentColor = if (accessibilityState.isHighContrast) MaterialTheme.colorScheme.onSurface else Color.White

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        border = if (accessibilityState.isHighContrast) androidx.compose.foundation.BorderStroke(2.dp, MaterialTheme.colorScheme.outline) else null
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp)
                    .background(Color.Black.copy(alpha = 0.2f)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = division.siglas,
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Black,
                    fontSize = 32.sp * accessibilityState.textScale,
                    color = contentColor
                )
            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = division.nombreCompleto.uppercase(),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Black,
                        fontSize = 14.sp * accessibilityState.textScale,
                        lineHeight = 18.sp,
                        color = contentColor
                    )
                    Text(
                        text = "Explora las carreras de esta área",
                        style = MaterialTheme.typography.bodySmall,
                        fontSize = 12.sp * accessibilityState.textScale,
                        color = contentColor.copy(alpha = 0.8f)
                    )
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "VER CARRERAS",
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 13.sp * accessibilityState.textScale,
                        color = if (accessibilityState.isHighContrast) contentColor else Color(0xFFFFB74D)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = if (accessibilityState.isHighContrast) contentColor else Color(0xFFFFB74D)
                    )
                }
            }
        }
    }
}
