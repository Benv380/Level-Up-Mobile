package cl.duoc.level_up_mobile

import AppNavHost
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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