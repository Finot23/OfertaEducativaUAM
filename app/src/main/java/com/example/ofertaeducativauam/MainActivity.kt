package com.example.ofertaeducativauam

//import android.os.Bundle
//import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ofertaeducativauam.ui.theme.OfertaEducativaUAMTheme
//import com.example.ofertaeducativauam.ui.viewmodel.AccessibilityViewModel
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.ofertaeducativauam.data.UamRepository
import com.example.ofertaeducativauam.ui.screens.AccessibilityScreen
import com.example.ofertaeducativauam.ui.screens.DivisionesScreen
import com.example.ofertaeducativauam.ui.screens.LicenciaturaDetailScreen
import com.example.ofertaeducativauam.ui.viewmodel.AccessibilityViewModel
class MainActivity : ComponentActivity() {

    private val accessibilityViewModel by viewModels<AccessibilityViewModel>()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // Observamos el estado reactivo del ViewModel
            val accessibilityState by accessibilityViewModel.uiState.collectAsState()

            // Jetpack Compose maneja el modo oscuro por defecto; aquí lo forzamos si el usuario lo activa en el panel
            val darkTheme = accessibilityState.isDarkMode || isSystemInDarkTheme()

            MaterialTheme(
                colorScheme = if (darkTheme) darkColorScheme() else lightColorScheme()
            ) {
                val navController = rememberNavController()
                val currentBackStack by navController.currentBackStackEntryAsState()
                val currentRoute = currentBackStack?.destination?.route

                // Usamos Scaffold para tener una estructura base con Barra Superior (TopAppBar)
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text("Oferta Educativa UAM-C") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            ),
                            navigationIcon = {
                                // Botón de regreso, visible solo si no estamos en la pantalla principal
                                if (currentRoute != "divisiones") {
                                    IconButton(onClick = { navController.navigateUp() }) {
                                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                                    }
                                }
                            },
                            actions = {
                                // Botón para ir al panel de accesibilidad
                                if (currentRoute != "accesibilidad") {
                                    IconButton(onClick = { navController.navigate("accesibilidad") }) {
                                        Icon(Icons.Default.Settings, contentDescription = "Accesibilidad")
                                    }
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    // Configuración del NavHost para gestionar las rutas de la app
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

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: AccessibilityViewModel,
    modifier: Modifier = Modifier
) {
    val accessibilityState by viewModel.uiState.collectAsState()

    NavHost(navController = navController, startDestination = "divisiones", modifier = modifier) {

        // Ruta 1: Pantalla Principal (Divisiones)
        composable("divisiones") {
            DivisionesScreen(accessibilityState = accessibilityState) { divisionSeleccionada ->
                // En un caso real, aquí filtrarías las licenciaturas por división.
                // Para este prototipo, navegaremos directamente al detalle de una carrera de ejemplo.
                navController.navigate("licenciatura_detalle/LTS")
            }
        }

        // Ruta 2: Detalles de la Licenciatura
        composable("licenciatura_detalle/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            // Buscamos la carrera en nuestro Repositorio Offline
            val licenciatura = UamRepository.licenciaturas.find { it.id == id }
                ?: UamRepository.licenciaturas.first() // Fallback a la primera si no se encuentra

            LicenciaturaDetailScreen(
                licenciatura = licenciatura,
                accessibilityState = accessibilityState
            )
        }

        // Ruta 3: Panel de Accesibilidad
        composable("accesibilidad") {
            AccessibilityScreen(viewModel = viewModel)
        }
    }
}
/*
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OfertaEducativaUAMTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    OfertaEducativaUAMTheme {
        Greeting("Android")
    }
}

 */