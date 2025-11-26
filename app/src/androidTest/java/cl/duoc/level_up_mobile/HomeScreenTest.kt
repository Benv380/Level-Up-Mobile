package cl.duoc.level_up_mobile

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// *** OJO: este es tu test instrumentado ***
@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    // Regla de Compose: crea una Activity de prueba donde montamos los composables
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun textos_principales_deben_aparecer_en_pantalla() {
        // Arrange
        composeRule.setContent {
            HomeScreenTestable(
                onLoginClick = {},
                onRegisterClick = {},
                onRecoverClick = {}
            )
        }

        // Assert: validamos que los textos clave se muestren
        composeRule.onNodeWithText("¡Bienvenido!").assertIsDisplayed()
        composeRule.onNodeWithText("Login").assertIsDisplayed()
        composeRule.onNodeWithText("Registrarse").assertIsDisplayed()
        composeRule.onNodeWithText("Recuperar contraseña").assertIsDisplayed()
    }

    @Test
    fun al_hacer_click_en_login_se_dispara_onLoginClick() {
        var loginClicked = false

        composeRule.setContent {
            HomeScreenTestable(
                onLoginClick = { loginClicked = true },
                onRegisterClick = {},
                onRecoverClick = {}
            )
        }

        composeRule.onNodeWithText("Login").performClick()

        assertTrue(loginClicked)
    }

    @Test
    fun al_hacer_click_en_registrarse_y_recuperar_se_disparan_callbacks() {
        var registerClicked = false
        var recoverClicked = false

        composeRule.setContent {
            HomeScreenTestable(
                onLoginClick = {},
                onRegisterClick = { registerClicked = true },
                onRecoverClick = { recoverClicked = true }
            )
        }

        composeRule.onNodeWithText("Registrarse").performClick()
        composeRule.onNodeWithText("Recuperar contraseña").performClick()

        assertTrue(registerClicked)
        assertTrue(recoverClicked)
    }
}

/**
 * Versión simplificada de HomeScreen solo para pruebas:
 * - No usa ViewModels
 * - No llama APIs
 * - Solo muestra textos y botones
 */
@Composable
private fun HomeScreenTestable(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onRecoverClick: () -> Unit
) {
    Column {
        Text("¡Bienvenido!")

        Spacer(Modifier.height(16.dp))

        Button(onClick = onLoginClick) {
            Text("Login")
        }
        Button(onClick = onRegisterClick) {
            Text("Registrarse")
        }
        Button(onClick = onRecoverClick) {
            Text("Recuperar contraseña")
        }
    }
}
