package cl.daeriquelme.appduoc_profe.ui.app

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.daeriquelme.appduoc_profe.ui.home.HomeScreen
import cl.daeriquelme.appduoc_profe.ui.login.LoginScreen
import cl.daeriquelme.appduoc_profe.ui.principal.PrincipalScreen
import cl.daeriquelme.appduoc_profe.ui.register.RegistrarseScreen
import cl.daeriquelme.appduoc_profe.ui.recover.RecuperarPasswordScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost() {
    val nav = rememberNavController()

    NavHost(navController = nav, startDestination = Route.HomeRoot.path) {

        composable(Route.HomeRoot.path) {
            HomeScreen(
                onLoginClick = { nav.navigate(Route.Login.path) },
                onRegisterClick = { nav.navigate(Route.Register.path) },
                onRecoverClick = { nav.navigate(Route.RecoverPassword.path) }
            )
        }

        composable(Route.Login.path) {
            LoginScreen(
                onBack = { nav.popBackStack() },
                onLoginSuccess = {
                    nav.navigate(Route.Principal.path) {
                        // si quieres limpiar hacia atrás:
                        // popUpTo(Route.HomeRoot.path) { inclusive = false }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Route.Principal.path) {
            PrincipalScreen(
                onLogout = {
                    nav.navigate(Route.HomeRoot.path) {
                        popUpTo(Route.HomeRoot.path) { inclusive = true } // limpia back stack
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Route.Register.path) {
            RegistrarseScreen(
                onBack = { nav.popBackStack() },
                onRegistered = {
                    nav.navigate(Route.Login.path) {
                        popUpTo(Route.HomeRoot.path) { inclusive = false }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Route.RecoverPassword.path) {
            RecuperarPasswordScreen(
                onBack = { nav.popBackStack() },
                onSent = {
                    nav.navigate(Route.Login.path) {
                        popUpTo(Route.HomeRoot.path) { inclusive = false }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
