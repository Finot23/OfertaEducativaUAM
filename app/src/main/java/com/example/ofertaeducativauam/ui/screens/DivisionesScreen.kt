package com.example.ofertaeducativauam.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ofertaeducativauam.data.Division
import com.example.ofertaeducativauam.ui.viewmodel.AccessibilityState

@Composable
fun DivisionesScreen(
    accessibilityState: AccessibilityState,
    onDivisionSelected: (Division) -> Unit
) {
    val divisiones = Division.values()

    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = "Divisiones Académicas UAM-C",
            style = MaterialTheme.typography.headlineMedium,
            fontSize = 24.sp * accessibilityState.textScale
        )
        Spacer(modifier = Modifier.height(16.dp))

        // LazyColumn es ideal para mostrar listas de manera eficiente en Compose
        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(divisiones) { division ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onDivisionSelected(division) },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = division.siglas,
                            style = MaterialTheme.typography.titleLarge,
                            fontSize = 20.sp * accessibilityState.textScale
                        )
                        Text(
                            text = division.nombreCompleto,
                            style = MaterialTheme.typography.bodyMedium,
                            fontSize = 14.sp * accessibilityState.textScale
                        )
                    }
                }
            }
        }
    }
}