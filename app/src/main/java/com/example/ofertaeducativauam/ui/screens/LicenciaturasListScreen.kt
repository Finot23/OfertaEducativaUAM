package com.example.ofertaeducativauam.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ofertaeducativauam.data.Division
import com.example.ofertaeducativauam.data.UamRepository
import com.example.ofertaeducativauam.ui.viewmodel.AccessibilityState

@Composable
fun LicenciaturasListScreen(
    divisionSiglas: String,
    accessibilityState: AccessibilityState,
    onLicenciaturaSelected: (String) -> Unit
) {
    val division = Division.entries.find { it.siglas == divisionSiglas }
    val licenciaturas = UamRepository.licenciaturas.filter { it.division.siglas == divisionSiglas }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(MaterialTheme.colorScheme.background)
    ) {
        // 1. ENCABEZADO DE DIVISIÓN
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(vertical = 24.dp, horizontal = 16.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "DIVISIÓN ${division?.siglas}",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Black,
                    fontSize = 28.sp * accessibilityState.textScale
                )
                Text(
                    text = division?.nombreCompleto?.uppercase() ?: "",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.9f),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp * accessibilityState.textScale,
                    letterSpacing = 1.sp
                )
            }
        }

        // 2. CARRUSEL DE IMÁGENES
        if (division != null && division.imagenes.isNotEmpty()) {
            Box(modifier = Modifier.padding(bottom = 8.dp)) {
                CarruselFotos(imagenes = division.imagenes)
            }
        }

        // 3. SECCIÓN SOBRE LA DIVISIÓN
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "SOBRE LA DIVISIÓN",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black,
                fontSize = 20.sp * accessibilityState.textScale,
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (division != null) stringResource(id = division.descripcion) else "",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp * accessibilityState.textScale,
                lineHeight = (22 + accessibilityState.lineSpacingExtra).sp
            )
            
            Spacer(modifier = Modifier.height(24.dp))

            // 4. CARRERAS DISPONIBLES
            Text(
                text = "CARRERAS DISPONIBLES",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Black,
                fontSize = 20.sp * accessibilityState.textScale
            )
            Spacer(modifier = Modifier.height(12.dp))

            licenciaturas.forEach { licenciatura ->
                CarreraButton(
                    nombre = licenciatura.nombre,
                    icon = licenciatura.icon,
                    accessibilityState = accessibilityState,
                    onClick = { onLicenciaturaSelected(licenciatura.id) }
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun CarreraButton(
    nombre: String,
    icon: Int, // Cambiado de ImageVector a Int
    accessibilityState: AccessibilityState,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = "Licenciatura en",
                        style = MaterialTheme.typography.labelSmall,
                        color = Color.Gray,
                        fontSize = 11.sp * accessibilityState.textScale
                    )
                    Text(
                        text = nombre.uppercase(),
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp * accessibilityState.textScale,
                        lineHeight = 18.sp
                    )
                }
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    }
}
