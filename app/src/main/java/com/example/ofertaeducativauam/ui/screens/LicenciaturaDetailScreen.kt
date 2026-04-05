package com.example.ofertaeducativauam.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ofertaeducativauam.data.Licenciatura
import com.example.ofertaeducativauam.ui.viewmodel.AccessibilityState

@Composable
fun LicenciaturaDetailScreen(
    licenciatura: Licenciatura,
    accessibilityState: AccessibilityState
) {
    var expandirPerfil by remember { mutableStateOf(false) }
    var expandirPlan by remember { mutableStateOf(false) }
    var expandirCampo by remember { mutableStateOf(false) }
    var imagenZoom by remember { mutableStateOf<Int?>(null) }

    val scrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .then(if (imagenZoom != null) Modifier.blur(10.dp) else Modifier)
        ) {
            // envabezado
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(vertical = 24.dp, horizontal = 16.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = licenciatura.nombre.uppercase(),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onPrimary, // Adaptable
                        fontWeight = FontWeight.Black,
                        fontSize = 24.sp * accessibilityState.textScale,
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                    Text(
                        text = "DIVISIÓN DE ${licenciatura.division.siglas}",
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = 0.8f), // Adaptable
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {

                Text(
                    text = "VIDA ACADÉMICA",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Black,
                    fontSize = 20.sp * accessibilityState.textScale
                )
                Spacer(modifier = Modifier.height(12.dp))
                if (licenciatura.imagenes.isNotEmpty()) {
                    CarruselFotos(licenciatura.imagenes)
                }
                
                Spacer(modifier = Modifier.height(32.dp))

                // INFORMACIÓN DE LA CARRERA
                Text(
                    text = "INFORMACIÓN DE LA CARRERA",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Black,
                    fontSize = 20.sp * accessibilityState.textScale
                )
                
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = stringResource(id = licenciatura.infoGeneral),
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 16.sp * accessibilityState.textScale,
                    lineHeight = (22 + accessibilityState.lineSpacingExtra).sp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.8f)
                )

                Spacer(modifier = Modifier.height(24.dp))

                // Acordeones
                AcordeonInfo(
                    titulo = "PERFIL DE INGRESO",
                    contenidoId = licenciatura.perfilIngreso,
                    expandido = expandirPerfil,
                    onToggle = { expandirPerfil = !expandirPerfil },
                    accessibilityState = accessibilityState
                )

                AcordeonInfo(
                    titulo = "PLAN DE ESTUDIOS",
                    contenidoId = licenciatura.planEstudios,
                    expandido = expandirPlan,
                    onToggle = { expandirPlan = !expandirPlan },
                    accessibilityState = accessibilityState,
                    imagenExtra = licenciatura.imagenPlanEstudios,
                    onImagenClick = { imagenZoom = it }
                )

                AcordeonInfo(
                    titulo = "CAMPO LABORAL",
                    contenidoId = licenciatura.campoLaboral,
                    expandido = expandirCampo,
                    onToggle = { expandirCampo = !expandirCampo },
                    accessibilityState = accessibilityState,
                    imagenExtra = licenciatura.imagenCampoLaboral,
                    onImagenClick = { imagenZoom = it }
                )
                
                Spacer(modifier = Modifier.height(32.dp))
            }
        }

        // fotos zoom
        imagenZoom?.let { resId ->
            Dialog(
                onDismissRequest = { imagenZoom = null },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.9f))
                        .clickable { imagenZoom = null },
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = resId),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        contentScale = ContentScale.Fit
                    )
                    IconButton(
                        onClick = { imagenZoom = null },
                        modifier = Modifier.align(Alignment.TopEnd).padding(16.dp)
                    ) {
                        Icon(Icons.Default.Close, contentDescription = "Cerrar", tint = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun AcordeonInfo(
    titulo: String,
    contenidoId: Int,
    expandido: Boolean,
    onToggle: () -> Unit,
    accessibilityState: AccessibilityState,
    imagenExtra: Int? = null,
    onImagenClick: (Int) -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onToggle() },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (expandido) MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f)
                             else MaterialTheme.colorScheme.surface
        ),
        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f))
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
                    fontSize = 16.sp * accessibilityState.textScale,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    imageVector = if (expandido) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            AnimatedVisibility(visible = expandido) {
                Column {
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        text = stringResource(id = contenidoId),
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 15.sp * accessibilityState.textScale,
                        lineHeight = (22 + accessibilityState.lineSpacingExtra).sp
                    )
                    
                    imagenExtra?.let { res ->
                        Spacer(modifier = Modifier.height(16.dp))
                        Image(
                            painter = painterResource(id = res),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .clickable { onImagenClick(res) },
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CarruselFotos(imagenes: List<Int>) {
    val pagerState = rememberPagerState(pageCount = { imagenes.size })

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
        ) { page ->
            Image(
                painter = painterResource(id = imagenes[page]),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))

        Row {
            repeat(imagenes.size) { iteration ->
                val color = if (pagerState.currentPage == iteration) MaterialTheme.colorScheme.primary else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(8.dp)
                )
            }
        }
    }
}
