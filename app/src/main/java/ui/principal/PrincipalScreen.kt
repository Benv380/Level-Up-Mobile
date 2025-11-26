package cl.duoc.level_up_mobile.ui.theme

import UiProductosCard
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import cl.duoc.level_up_mobile.ui.principal.PrincipalViewModel
import ui.principal.ProductoViewModel


// Items del Bottom Navigation
// --- Bottom items ---
sealed class BottomItem(
    val route: String,
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val badge: Int? = null
) {
    data object Home : BottomItem("home", "Inicio", Icons.Outlined.Home)
    data object Favs : BottomItem("favs", "Favoritos", Icons.Outlined.FavoriteBorder)
    data object Cart : BottomItem("cart", "Carrito", Icons.Outlined.ShoppingCart, badge = 3)
    //data object Purchases : BottomItem("cart", "Mis compras", Icons.Outlined.PlayArrow)
    data object Agenda : BottomItem("agenda", "Agenda", Icons.Outlined.PlayArrow)
    data object More : BottomItem("more", "Más", Icons.Outlined.Menu)
}

private val bottomItems = listOf(
    BottomItem.Home, BottomItem.Favs, BottomItem.Cart, BottomItem.Agenda, BottomItem.More
)

@Composable
private fun BottomBar(navController: NavHostController) {
    val backStack by navController.currentBackStackEntryAsState()
    val currentRoute = backStack?.destination?.route
    NavigationBar {
        bottomItems.forEach { item ->
            NavigationBarItem(
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    if ((item.badge ?: 0) > 0) {
                        BadgedBox(badge = { Badge { Text("${item.badge}") } }) {
                            Icon(item.icon, contentDescription = item.title)
                        }
                    } else {
                        Icon(item.icon, contentDescription = item.title)
                    }
                },
                label = { Text(item.title) }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PrincipalScreen(
    onLogout: () -> Unit = {},
    vm: PrincipalViewModel = viewModel(),
    productoVM: ProductoViewModel = viewModel()   // <<--- AGREGADO
) {
    val state by vm.ui.collectAsState()

    val productos = productoVM.productos           // <<--- AHORA VIENE DEL PRODUCTO VM
    val loadingProductos = productoVM.loading
    val errorProductos = productoVM.error

    LaunchedEffect(Unit) {
        productoVM.cargarProductos()
    }

    var expanded by remember { mutableStateOf(false) }
    val tabsNav = rememberNavController()

    // logout reactivo
    LaunchedEffect(state.loggedOut) {
        if (state.loggedOut) onLogout()
    }

    // Snackbar para errores
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(state.error) {
        state.error?.let { snackbarHostState.showSnackbar(it) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Principal") },
                actions = {
                    IconButton(onClick = { expanded = true }) {
                        Icon(Icons.Outlined.MoreVert, contentDescription = "Menú")
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Perfil") },
                            onClick = { expanded = false },
                            leadingIcon = { Icon(Icons.Outlined.Info, contentDescription = null) }
                        )
                        DropdownMenuItem(
                            text = { Text("Configuración") },
                            onClick = { expanded = false },
                            leadingIcon = { Icon(Icons.Outlined.Settings, contentDescription = null) }
                        )
                        DropdownMenuItem(
                            text = { Text("Logout") },
                            onClick = {
                                expanded = false
                                vm.logout()
                            }
                        )
                    }
                }
            )
        },
        bottomBar = { BottomBar(tabsNav) },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { inner ->
        NavHost(
            navController = tabsNav,
            startDestination = BottomItem.Home.route,
            modifier = Modifier.padding(inner)
        ) {
            composable(BottomItem.Home.route) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    val saludo = "Hola ${state.email ?: "usuario"}"
                    Text(saludo, style = MaterialTheme.typography.headlineSmall)
                    Text("Bienvenido a tu pantalla principal.")

                    // Cargando productos
                    if (loadingProductos) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                        return@composable
                    }

                    // Error al cargar
                    errorProductos?.let { err ->
                        Text("⚠ $err", color = MaterialTheme.colorScheme.error)
                    }

                    // Mostrar productos reales
                    LazyVerticalGrid(
                        columns = GridCells.Adaptive(minSize = 180.dp),
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(top = 8.dp, bottom = 80.dp)
                    ) {
                        items(productos, key = { it.id }) { producto ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .aspectRatio(0.6f)
                            ) {
                                UiProductosCard(
                                    producto = producto,
                                    onAgregar = {}
                                )
                            }
                        }
                    }
                }
            }

            // --- resto igual ---
            composable(BottomItem.Favs.route) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Favoritos") }
            }
            composable(BottomItem.Cart.route) {
                Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) { Text("Carrito") }
            }
            composable(BottomItem.Agenda.route) {
                val uid = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser?.uid
                if (uid == null) {
                    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("Debes iniciar sesión para ver tus recordatorios.")
                    }
                } else {
                    val context = LocalContext.current
                    val factory = remember(uid) { cl.duoc.level_up_mobile.ui.vmfactory.RecordatorioVMFactory(context, uid) }
                    val rvm: cl.duoc.level_up_mobile.ui.recordatorio.RecordatorioViewModel =
                        androidx.lifecycle.viewmodel.compose.viewModel(factory = factory)
                    cl.duoc.level_up_mobile.ui.recordatorio.RecordatorioScreen(rvm)
                }
            }
            composable(BottomItem.More.route) {
                val context = LocalContext.current
                val prefs = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
                var darkMode by remember { mutableStateOf(prefs.getBoolean("dark_mode", false)) }

                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically)
                ) {
                    Text("Más opciones", style = MaterialTheme.typography.headlineSmall)

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text("Modo oscuro", style = MaterialTheme.typography.bodyLarge)
                        Switch(
                            checked = darkMode,
                            onCheckedChange = { isChecked ->
                                darkMode = isChecked
                                prefs.edit().putBoolean("dark_mode", isChecked).apply()
                                if (isChecked)
                                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                                else
                                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                            }
                        )
                    }

                    Divider(thickness = 1.dp, color = MaterialTheme.colorScheme.outline)

                    Button(onClick = { vm.logout() }) {
                        Icon(Icons.Outlined.Close, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text(if (state.loading) "Cerrando..." else "Cerrar sesión")
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrincipalScreenPreview() {
    AppDuoc_Level_up_Theme {
        PrincipalScreen(onLogout = {})
    }
}