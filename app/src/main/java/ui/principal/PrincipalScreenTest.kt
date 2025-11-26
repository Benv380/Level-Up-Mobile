package cl.duoc.level_up_mobile.ui.principal

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import cl.duoc.level_up_mobile.ui.theme.PrincipalScreen
import cl.duoc.level_up_mobile.ui.principal.PrincipalViewModel
import cl.duoc.level_up_mobile.ui.principal.ProductoViewModel
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PrincipalScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun muestraSaludoYProductos() {

        val vm = PrincipalViewModel().apply {
            setEmailForTest("test@duoc.cl")
        }

        composeTestRule.setContent {
            PrincipalScreen(
                onLogout = {},
                vm = vm,
                productoVM = ProductoViewModel()
            )
        }

        composeTestRule.onNodeWithText("Hola test@duoc.cl").assertExists()
    }

    @Test
    fun agregarProductoAlCarrito_disparaEvento() {

        composeTestRule.setContent {
            PrincipalScreen(
                onLogout = {},
                vm = PrincipalViewModel(),
                productoVM = ProductoViewModel()
            )
        }

        composeTestRule.onAllNodesWithText("Agregar al carrito")[0].performClick()
    }
}
