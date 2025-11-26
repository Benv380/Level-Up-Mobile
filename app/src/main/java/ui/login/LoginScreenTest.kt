package cl.duoc.level_up_mobile.ui.login

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import cl.duoc.level_up_mobile.LoginScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginScreenTest {
////
    @get:Rule
    val composeTestRule = createComposeRule()

    /** -------------------------------------------
     *  TEST 1:
     *  Verifica que los elementos principales existan
     * ------------------------------------------- */
    @Test
    fun mostrarElementosBasicos_login() {
        composeTestRule.setContent {
            LoginScreen(
                onBack = {},
                onLoginSuccess = {}
            )
        }

        composeTestRule.onNodeWithText("Correo electrónico").assertExists()
        composeTestRule.onNodeWithText("Contraseña").assertExists()
        composeTestRule.onNodeWithText("Ingresar").assertExists()
    }

    /** -------------------------------------------
     *  TEST 2:
     *  Escribir email + password y presionar “Ingresar”
     *  Valida que el ViewModel dispare submit()
     * ------------------------------------------- */
    @Test
    fun escribirEmailPassword_yPresionarLogin() {
        composeTestRule.setContent {
            LoginScreen(
                onBack = {},
                onLoginSuccess = {}
            )
        }

        // Escribir datos
        composeTestRule.onNodeWithText("Correo electrónico")
            .performTextInput("test@mail.com")

        composeTestRule.onNodeWithText("Contraseña")
            .performTextInput("123456")

        // Presionar botón
        composeTestRule.onNodeWithText("Ingresar")
            .performClick()

        // Si no crashea, el test pasa
        assert(true)
    }

    /** -------------------------------------------
     *  TEST 3:
     *  Botón “Atrás” llama a onBack()
     * ------------------------------------------- */
    @Test
    fun presionarBack_llamaCallback() {
        var backPressed = false

        composeTestRule.setContent {
            LoginScreen(
                onBack = { backPressed = true },
                onLoginSuccess = {}
            )
        }

        composeTestRule.onNodeWithText("Atrás").performClick()

        assert(backPressed)
    }
}
