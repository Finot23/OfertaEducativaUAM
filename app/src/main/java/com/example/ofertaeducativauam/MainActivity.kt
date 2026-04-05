package com.example.ofertaeducativauam

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ofertaeducativauam.data.Division
import com.example.ofertaeducativauam.data.UamRepository
import com.example.ofertaeducativauam.ui.screens.*
import com.example.ofertaeducativauam.ui.theme.OfertaEducativaUAMTheme
import com.example.ofertaeducativauam.ui.viewmodel.AccessibilityViewModel
import com.example.ofertaeducativauam.ui.viewmodel.ColorFilterType
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val accessibilityViewModel by viewModels<AccessibilityViewModel>()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val accessibilityState by accessibilityViewModel.uiState.collectAsState()
            val darkTheme = accessibilityState.isDarkMode || isSystemInDarkTheme()

            OfertaEducativaUAMTheme(
                darkTheme = darkTheme,
                isHighContrast = accessibilityState.isHighContrast,
                lineSpacingExtra = accessibilityState.lineSpacingExtra
            ) {
                val matrixValues = remember(accessibilityState.colorFilter) {
                    when (accessibilityState.colorFilter) {
                        ColorFilterType.PROTANOPIA -> floatArrayOf(
                            0.567f, 0.433f, 0.000f, 0.000f, 0.000f,
                            0.558f, 0.442f, 0.000f, 0.000f, 0.000f,
                            0.000f, 0.242f, 0.758f, 0.000f, 0.000f,
                            0.000f, 0.000f, 0.000f, 1.000f, 0.000f
                        )
                        ColorFilterType.DEUTERANOPIA -> floatArrayOf(
                            0.625f, 0.375f, 0.000f, 0.000f, 0.000f,
                            0.700f, 0.300f, 0.000f, 0.000f, 0.000f,
                            0.000f, 0.300f, 0.700f, 0.000f, 0.000f,
                            0.000f, 0.000f, 0.000f, 1.000f, 0.000f
                        )
                        ColorFilterType.TRITANOPIA -> floatArrayOf(
                            0.950f, 0.050f, 0.000f, 0.000f, 0.000f,
                            0.000f, 0.433f, 0.567f, 0.000f, 0.000f,
                            0.000f, 0.475f, 0.525f, 0.000f, 0.000f,
                            0.000f, 0.000f, 0.000f, 1.000f, 0.000f
                        )
                        else -> floatArrayOf(
                            1f, 0f, 0f, 0f, 0f,
                            0f, 1f, 0f, 0f, 0f,
                            0f, 0f, 1f, 0f, 0f,
                            0f, 0f, 0f, 1f, 0f
                        )
                    }
                }

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S && 
                                accessibilityState.colorFilter != ColorFilterType.STANDARD) {
                                val filter = android.graphics.ColorMatrixColorFilter(matrixValues)
                                renderEffect = android.graphics.RenderEffect.createColorFilterEffect(filter).asComposeRenderEffect()
                            }
                        },
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val scope = rememberCoroutineScope()

                    ModalNavigationDrawer(
                        drawerState = drawerState,
                        drawerContent = {
                            ModalDrawerSheet {
                                DrawerContent(
                                    onNavigate = { route ->
                                        navController.navigate(route) {
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                        scope.launch { drawerState.close() }
                                    },
                                    onDivisionClick = { siglas ->
                                        navController.navigate("licenciaturas/$siglas")
                                        scope.launch { drawerState.close() }
                                    }
                                )
                            }
                        }
                    ) {
                        val currentBackStack by navController.currentBackStackEntryAsState()
                        val currentRoute = currentBackStack?.destination?.route

                        Scaffold(
                            topBar = {
                                CenterAlignedTopAppBar(
                                    title = { 
                                        // ICONO EN LA BARRA SIN FONDO BLANCO
                                        Image(
                                            painter = painterResource(id = R.drawable.icon),
                                            contentDescription = "Logo",
                                            modifier = Modifier
                                                .height(45.dp)
                                                .clip(RoundedCornerShape(8.dp)),
                                            contentScale = ContentScale.Fit
                                        )
                                    },
                                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                        containerColor = MaterialTheme.colorScheme.primary,
                                        titleContentColor = MaterialTheme.colorScheme.onPrimary
                                    ),
                                    navigationIcon = {
                                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                                            Icon(Icons.Default.Menu, contentDescription = "Menú", tint = MaterialTheme.colorScheme.onPrimary)
                                        }
                                    },
                                    actions = {
                                        if (currentRoute != "home" && currentRoute != null) {
                                            IconButton(onClick = { navController.navigateUp() }) {
                                                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Regresar", tint = MaterialTheme.colorScheme.onPrimary)
                                            }
                                        }
                                    }
                                )
                            }
                        ) { innerPadding ->
                            AppNavigation(
                                navController = navController,
                                viewModel = accessibilityViewModel,
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun DrawerContent(
    onNavigate: (String) -> Unit,
    onDivisionClick: (String) -> Unit
) {
    var divisionesExpandidas by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // ICONO EN EL MENÚ SIN FONDO BLANCO
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.icon),
                contentDescription = "Logo",
                modifier = Modifier
                    .height(80.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Fit
            )
        }
        
        HorizontalDivider()
        Spacer(modifier = Modifier.height(16.dp))
        
        DrawerItem(label = "INICIO", icon = Icons.Default.Home) { onNavigate("home") }
        
        Column {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.small
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable { onNavigate("divisiones") },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.AutoMirrored.Filled.List, contentDescription = null)
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(text = "DIVISIONES", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
                    }
                    IconButton(onClick = { divisionesExpandidas = !divisionesExpandidas }) {
                        Icon(imageVector = if (divisionesExpandidas) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown, contentDescription = null)
                    }
                }
            }
            if (divisionesExpandidas) {
                Division.entries.forEach { division ->
                    Text(text = division.nombreCompleto, modifier = Modifier.fillMaxWidth().padding(start = 48.dp, top = 12.dp, bottom = 12.dp).clickable { onDivisionClick(division.siglas) }, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
        DrawerItem(label = "CONTACTO", icon = Icons.Default.Email) { onNavigate("contacto") }
        DrawerItem(label = "ACCESIBILIDAD", icon = Icons.Default.Settings) { onNavigate("accesibilidad") }
    }
}

@Composable
fun DrawerItem(label: String, icon: ImageVector, onClick: () -> Unit) {
    NavigationDrawerItem(label = { Text(label, fontWeight = FontWeight.Bold) }, selected = false, onClick = onClick, icon = { Icon(icon, contentDescription = null) }, modifier = Modifier.padding(vertical = 4.dp))
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: AccessibilityViewModel,
    modifier: Modifier = Modifier
) {
    val accessibilityState by viewModel.uiState.collectAsState()

    NavHost(navController = navController, startDestination = "home", modifier = modifier) {
        composable("home") { HomeScreen(accessibilityState = accessibilityState) { navController.navigate("divisiones") } }
        composable("divisiones") { DivisionesScreen(accessibilityState = accessibilityState) { division -> navController.navigate("licenciaturas/${division.siglas}") } }
        composable("licenciaturas/{divisionSiglas}") { backStackEntry ->
            val siglas = backStackEntry.arguments?.getString("divisionSiglas") ?: ""
            LicenciaturasListScreen(divisionSiglas = siglas, accessibilityState = accessibilityState) { id -> navController.navigate("licenciatura_detalle/$id") }
        }
        composable("licenciatura_detalle/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            val licenciatura = UamRepository.licenciaturas.find { it.id == id } ?: UamRepository.licenciaturas.first()
            LicenciaturaDetailScreen(licenciatura = licenciatura, accessibilityState = accessibilityState)
        }
        composable("contacto") { ContactoScreen(accessibilityState = accessibilityState) }
        composable("accesibilidad") { AccessibilityScreen(viewModel = viewModel) }
    }
}
