package ui.principal

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.google.firebase.auth.FirebaseAuth
import cl.daeriquelme.appduoc_profe.ui.theme.AppDuoc_ProfeTheme

// Items del Bottom Navigation
sealed class BottomItem(
    val route: String,
    val title: String,
    val icon: ImageVector,
    val badge: Int? = null
) {
    data object Home : BottomItem("home", "Inicio", Icons.Outlined.Home)
    data object Favs : BottomItem("favs", "Favoritos", Icons.Outlined.FavoriteBorder)
    data object Cart : BottomItem("cart", "Carrito", Icons.Outlined.ShoppingCart, badge = 3)
    data object Clips : BottomItem("clips", "Clips", Icons.Outlined.PlayArrow)
    data object More : BottomItem("more", "Más", Icons.Outlined.Menu)
}

private val bottomItems = listOf(
    BottomItem.Home, BottomItem.Favs, BottomItem.Cart, BottomItem.Clips, BottomItem.More
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrincipalScreen(
    onLogout: () -> Unit = {}
) {
    val user = FirebaseAuth.getInstance().currentUser
    val saludo = "Hola ${user?.email ?: "usuario"}"
    var expanded by remember { mutableStateOf(false) }
    val tabsNav = rememberNavController()

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
                                FirebaseAuth.getInstance().signOut()
                                onLogout()
                            }
                        )
                    }
                }
            )
        },
        bottomBar = { BottomBar(tabsNav) }
    ) { inner ->
        // NavHost de pestañas
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
                    Text(saludo, style = MaterialTheme.typography.headlineSmall)
                    Text("Bienvenido a tu pantalla principal.")
                }
            }

            composable(BottomItem.Favs.route) {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) { Text("Favoritos") }
            }

            composable(BottomItem.Cart.route) {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) { Text("Carrito") }
            }

            composable(BottomItem.Clips.route) {
                Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) { Text("Clips") }
            }

            composable(BottomItem.More.route) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
                ) {
                    Text("Más opciones")
                    Button(onClick = {
                        FirebaseAuth.getInstance().signOut()
                        onLogout()
                    }) {
                        Icon(Icons.Outlined.Close, contentDescription = null)
                        Spacer(Modifier.width(8.dp))
                        Text("Cerrar sesión")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrincipalScreenPreview() {
    AppDuoc_ProfeTheme {
        PrincipalScreen(onLogout = {})
    }
}