package cl.duoc.level_up_mobile

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import cl.duoc.level_up_mobile.ui.app.AppNavHost
import cl.duoc.level_up_mobile.ui.theme.AppDuoc_Level_up_Theme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppDuoc_Level_up_Theme {
                AppNavHost()
            }
        }
    }
}