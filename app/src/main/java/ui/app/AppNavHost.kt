import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cl.duoc.level_up_mobile.LoginScreen
import cl.duoc.level_up_mobile.ui.RecuperarPasswordScreen
import cl.duoc.level_up_mobile.ui.RegistrarseScreen
import cl.duoc.level_up_mobile.ui.app.Route
import cl.duoc.level_up_mobile.ui.home.HomeScreen
import cl.duoc.level_up_mobile.ui.theme.PrincipalScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavHost() {
    val nav = rememberNavController()

    NavHost(
        navController = nav,
        startDestination = "splash"
    ) {

        composable("splash") {
            SplashScreen(
                onNavToPrincipal = {
                    nav.navigate(Route.Principal.path) {
                        popUpTo("splash") { inclusive = true }
                    }
                },
                onNavToHomeRoot = {
                    nav.navigate(Route.HomeRoot.path) {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }

        composable(Route.HomeRoot.path) {
            HomeScreen(
                onLoginClick = { nav.navigate(Route.Login.path) },
                onRegisterClick = { nav.navigate(Route.Register.path) },
                onRecoverClick = { nav.navigate(Route.RecoverPassword.path) },
            )
        }

        composable(Route.Login.path) {
            LoginScreen(
                onBack = { nav.popBackStack() },
                onLoginSuccess = {
                    nav.navigate(Route.Principal.path) {
                        popUpTo(Route.HomeRoot.path) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Route.Principal.path) {
            PrincipalScreen(
                onLogout = {
                    nav.navigate(Route.HomeRoot.path) {
                        popUpTo(Route.Principal.path) { inclusive = true }
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
