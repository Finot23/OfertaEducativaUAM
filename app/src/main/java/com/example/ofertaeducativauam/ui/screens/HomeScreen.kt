package com.example.ofertaeducativauam.ui.screens

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
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.ofertaeducativauam.R
import com.example.ofertaeducativauam.ui.viewmodel.AccessibilityState

@Composable
fun HomeScreen(
    accessibilityState: AccessibilityState,
    onExploreClick: () -> Unit
) {
    val scrollState = rememberScrollState()
    var imagenZoom by remember { mutableStateOf<Int?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .then(if (imagenZoom != null) Modifier.blur(10.dp) else Modifier)
        ) {
            // Imagen principal de la Unidad
            Box(modifier = Modifier.fillMaxWidth().height(250.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.home_uam),
                    contentDescription = "UAM Cuajimalpa",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Surface(
                    modifier = Modifier.align(Alignment.BottomStart).padding(16.dp),
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = "UNIDAD CUAJIMALPA",
                        modifier = Modifier.padding(8.dp),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp * accessibilityState.textScale
                    )
                }
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "SOMOS UAM CUAJIMALPA",
                    style = MaterialTheme.typography.headlineMedium,
                    fontSize = 26.sp * accessibilityState.textScale,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "UAM Cuajimalpa es una unidad académica de la Universidad Autónoma Metropolitana, comprometida con la excelencia y la innovación. Ofrecemos una oferta educativa de vanguardia dividida en tres grandes áreas del conocimiento.",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 16.sp * accessibilityState.textScale,
                    lineHeight = (22 + accessibilityState.lineSpacingExtra).sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = onExploreClick,
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = MaterialTheme.shapes.medium
                ) {
                    Text("NUESTRAS LICENCIATURAS", fontSize = 18.sp * accessibilityState.textScale)
                }

                Spacer(modifier = Modifier.height(32.dp))

                HomeCarousel(
                    imagenes = listOf(R.drawable.home_1, R.drawable.home_2, R.drawable.home_3),
                    onImagenClick = { imagenZoom = it }
                )
                
                Spacer(modifier = Modifier.height(24.dp))

                // Nuevo bloque de texto
                Text(
                    text = "Compromiso con el Futuro",
                    style = MaterialTheme.typography.headlineSmall,
                    fontSize = 22.sp * accessibilityState.textScale,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "En la Unidad Cuajimalpa, no solo formamos profesionales, sino ciudadanos críticos y creativos. Nuestras licenciaturas están diseñadas para responder a las necesidades cambiantes del mundo laboral y social.",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 16.sp * accessibilityState.textScale,
                    lineHeight = (22 + accessibilityState.lineSpacingExtra).sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Segundo Carrusel (home_4, home_5)
                HomeCarousel(
                    imagenes = listOf(R.drawable.home_4, R.drawable.home_5),
                    onImagenClick = { imagenZoom = it }
                )

                Spacer(modifier = Modifier.height(32.dp))
            }
        }

        // Dialog de Zoom (Reutilizado del detalle)
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
fun HomeCarousel(imagenes: List<Int>, onImagenClick: (Int) -> Unit) {
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
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onImagenClick(imagenes[page]) }
            )
        }
        
        Spacer(modifier = Modifier.height(8.dp))

        // Puntos indicadores
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
