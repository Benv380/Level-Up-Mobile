package cl.duoc.level_up_mobile.ui.principal

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import cl.duoc.level_up_mobile.ui.theme.PrincipalScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PrincipalScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun muestraSaludoYProductos() {

        composeTestRule.setContent {
            PrincipalScreen(
                onLogout = {},
                vm = PrincipalViewModel().apply {
                    ui.value = ui.value.copy(email = "test@duoc.cl")
                },
                productoVM = FakeProductoViewModel()
            )
        }

        composeTestRule.onNodeWithText("Hola test@duoc.cl").assertExists()
        composeTestRule.onNodeWithText("PS5").assertExists()
        composeTestRule.onNodeWithText("Catan").assertExists()
    }

    @Test
    fun agregarProductoAlCarrito_disparaEvento() {

        composeTestRule.setContent {
            PrincipalScreen(
                onLogout = {},
                vm = PrincipalViewModel(),
                productoVM = FakeProductoViewModel()
            )
        }

        // Cuando haya un evento real, aquí se verificará.
        composeTestRule.onAllNodesWithText("Agregar al carrito")[0].performClick()
    }
}
