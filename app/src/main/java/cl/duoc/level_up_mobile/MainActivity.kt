package cl.duoc.level_up_mobile

import AppNavHost
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.Crossfade
import androidx.compose.runtime.*
import kotlinx.coroutines.delay
import cl.duoc.level_up_mobile.ui.AnimatedLogo
import cl.duoc.level_up_mobile.ui.theme.AppDuoc_Level_up_Theme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppDuoc_Level_up_Theme {
                // Estado que controla si aún mostramos el splash
                var showSplash by remember { mutableStateOf(true) }

                // Espera y cambia a la Home (NavHost)
                LaunchedEffect(Unit) {
                    // Ajusta el tiempo si quieres (ms)
                    delay(1500)
                    showSplash = false
                }

                Crossfade(targetState = showSplash, label = "splash-crossfade") { stillSplash ->
                    if (stillSplash) {
                        // Pantalla SOLO de logo
                        AnimatedLogo()
                    } else {
                        // Tu navegación/composición normal
                        AppNavHost()
                    }
                }
            }
        }
    }
}
