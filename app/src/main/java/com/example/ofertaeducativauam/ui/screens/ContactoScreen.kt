package com.example.ofertaeducativauam.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ofertaeducativauam.R
import com.example.ofertaeducativauam.ui.viewmodel.AccessibilityState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ContactoScreen(accessibilityState: AccessibilityState) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    var nombre by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var mensaje by remember { mutableStateOf("") }
    
    var errorNombre by remember { mutableStateOf(false) }
    var errorEmail by remember { mutableStateOf(false) }
    var errorMensaje by remember { mutableStateOf(false) }
    var cargando by remember { mutableStateOf(false) }

    var expandirFormulario by remember { mutableStateOf(false) }
    val rotationState by animateFloatAsState(targetValue = if (expandirFormulario) 180f else 0f)

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = MaterialTheme.colorScheme.background
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(padding)
        ) {
            // 1. ENCABEZADO UNIFICADO
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(vertical = 32.dp, horizontal = 16.dp)
            ) {
                Text(
                    text = "CONTACTO",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontWeight = FontWeight.Black,
                    fontSize = 26.sp * accessibilityState.textScale,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {
                
                // 2. UBICACIÓN Y MAPA
                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.LocationOn, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Ubicación", fontWeight = FontWeight.Bold, fontSize = 18.sp * accessibilityState.textScale)
                        }
                        Text(
                            text = stringResource(id = R.string.contacto_ubicacion_detalle),
                            fontSize = 14.sp * accessibilityState.textScale,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                        Box(
                            modifier = Modifier.fillMaxWidth().height(150.dp).clip(RoundedCornerShape(12.dp))
                                .clickable {
                                    val gmmIntentUri = Uri.parse("geo:19.3586,-99.2763?q=UAM+Cuajimalpa")
                                    context.startActivity(Intent(Intent.ACTION_VIEW, gmmIntentUri).apply { setPackage("com.google.android.apps.maps") })
                                }
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.mapa_estatico), 
                                contentDescription = null, modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop
                            )
                            Box(Modifier.fillMaxSize().background(Color.Black.copy(alpha = 0.4f)), contentAlignment = Alignment.Center) {
                                Text("TOCA PARA ABRIR EN MAPAS", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 11.sp)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // 3. TELÉFONO Y HORARIO
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.2f)),
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.3f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.Phone, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(text = "Teléfono", fontWeight = FontWeight.Bold, fontSize = 14.sp * accessibilityState.textScale)
                                Text(
                                    text = "(+52) 55 5814 6500", 
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.clickable {
                                        context.startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:5558146500")))
                                    }
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(12.dp))
                        HorizontalDivider(color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f))
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(text = "Horario de atención", fontWeight = FontWeight.Bold, fontSize = 14.sp * accessibilityState.textScale)
                        Text(text = "Lunes a viernes de 9am a 5pm.", fontSize = 14.sp * accessibilityState.textScale)
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // 4. FORMULARIO ACORDEÓN
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f))
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth().clickable { expandirFormulario = !expandirFormulario },
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "ENVÍANOS UN MENSAJE", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
                            Icon(Icons.Default.KeyboardArrowDown, contentDescription = null, modifier = Modifier.rotate(rotationState), tint = MaterialTheme.colorScheme.primary)
                        }

                        AnimatedVisibility(visible = expandirFormulario) {
                            Column {
                                Spacer(modifier = Modifier.height(16.dp))
                                OutlinedTextField(
                                    value = nombre, 
                                    onValueChange = { nombre = it; errorNombre = false }, 
                                    label = { Text("Nombre") }, 
                                    modifier = Modifier.fillMaxWidth(), 
                                    isError = errorNombre,
                                    supportingText = { if (errorNombre) Text("El nombre es obligatorio", color = MaterialTheme.colorScheme.error) },
                                    shape = RoundedCornerShape(12.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                OutlinedTextField(
                                    value = email, 
                                    onValueChange = { email = it; errorEmail = false }, 
                                    label = { Text("Correo") }, 
                                    modifier = Modifier.fillMaxWidth(), 
                                    isError = errorEmail,
                                    supportingText = { if (errorEmail) Text("Ingresa un correo válido", color = MaterialTheme.colorScheme.error) },
                                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                OutlinedTextField(
                                    value = mensaje, 
                                    onValueChange = { mensaje = it; errorMensaje = false }, 
                                    label = { Text("Mensaje") }, 
                                    modifier = Modifier.fillMaxWidth().height(120.dp), 
                                    isError = errorMensaje,
                                    supportingText = { if (errorMensaje) Text("El mensaje no puede estar vacío", color = MaterialTheme.colorScheme.error) },
                                    shape = RoundedCornerShape(12.dp)
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Button(
                                    onClick = {
                                        val isEmailValid = email.contains("@") && email.contains(".")
                                        if (nombre.isNotBlank() && isEmailValid && mensaje.isNotBlank()) {
                                            scope.launch { 
                                                cargando = true
                                                delay(1500) 
                                                cargando = false
                                                nombre = ""; email = ""; mensaje = ""
                                                expandirFormulario = false
                                                snackbarHostState.showSnackbar("¡Enviado, te responderemos pronto!") 
                                            }
                                        } else { 
                                            errorNombre = nombre.isBlank()
                                            errorEmail = !isEmailValid
                                            errorMensaje = mensaje.isBlank()
                                        }
                                    },
                                    modifier = Modifier.fillMaxWidth().height(56.dp), 
                                    enabled = !cargando, 
                                    shape = RoundedCornerShape(12.dp)
                                ) {
                                    if (cargando) CircularProgressIndicator(color = Color.White, modifier = Modifier.size(20.dp))
                                    else {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text("ENVIAR", fontWeight = FontWeight.Bold)
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Icon(Icons.AutoMirrored.Filled.Send, contentDescription = null)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // 5. REDES SOCIALES
                Text(text = "REDES SOCIALES", fontWeight = FontWeight.Bold, fontSize = 18.sp * accessibilityState.textScale)
                Spacer(modifier = Modifier.height(12.dp))
                Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    RedSocialCard(nombre = "X (Twitter)", usuario = "@uamcuajimalpa", iconRes = R.drawable.logo_x) {
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://x.com/uamcuajimalpa")))
                    }
                    RedSocialCard(nombre = "Facebook", usuario = "UAM Unidad Cuajimalpa", iconRes = R.drawable.logo_fb) {
                        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/UAMUnidadCuajimalpa1/?locale=es_LA")))
                    }
                }
                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}

@Composable
fun RedSocialCard(nombre: String, usuario: String, iconRes: Int, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.5f)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = iconRes), contentDescription = null, modifier = Modifier.size(40.dp).clip(CircleShape), contentScale = ContentScale.Fit)
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(text = nombre, fontWeight = FontWeight.Bold, fontSize = 15.sp)
                Text(text = usuario, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.primary)
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.Gray)
        }
    }
}
