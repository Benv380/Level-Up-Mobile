package cl.duoc.level_up_mobile

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import cl.duoc.level_up_mobile.ui.home.HomeScreen
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    // Regla de Compose: crea una Activity de prueba donde montamos los composables
    @get:Rule
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    @Test
    fun textos_principales_deben_aparecer_en_pantalla() {
        // Arrange: renderizamos la HomeScreen con callbacks vacíos
        composeRule.setContent {
            HomeScreen(
                onLoginClick = {},
                onRegisterClick = {},
                onRecoverClick = {}
            )
        }

        // Assert: validamos que los textos clave se muestren
        composeRule.onNodeWithText("¡Bienvenido!").assertIsDisplayed()
        composeRule.onNodeWithText("Login").assertIsDisplayed()
        composeRule.onNodeWithText("Register").assertIsDisplayed()
        composeRule.onNodeWithText("Recuperar contraseña").assertIsDisplayed()
    }

    @Test
    fun al_hacer_click_en_login_se_dispara_onLoginClick() {
        var loginClicked = false // Para saber si hubo click

        // Arrange
        composeRule.setContent {
            HomeScreen(
                onLoginClick = { loginClicked = true },
                onRegisterClick = {},
                onRecoverClick = {}
            )
        }

        // Act: hacemos click en el botón "Login"
        composeRule.onNodeWithText("Login").performClick()

        // Assert: verificamos que el callback se ejecutó
        assertTrue(loginClicked)
    }

    @Test
    fun al_hacer_click_en_registrarse_y_recuperar_se_disparan_callbacks() {
        var registerClicked = false
        var recoverClicked = false

        // Arrange
        composeRule.setContent {
            HomeScreen(
                onLoginClick = {},
                onRegisterClick = { registerClicked = true },
                onRecoverClick = { recoverClicked = true }
            )
        }

        // Act
        composeRule.onNodeWithText("Registrarse").performClick()
        composeRule.onNodeWithText("Recuperar contraseña").performClick()

        // Assert
        assertTrue(registerClicked)
        assertTrue(recoverClicked)
    }
}
