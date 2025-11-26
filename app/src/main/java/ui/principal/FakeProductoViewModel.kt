package cl.duoc.level_up_mobile.ui.principal

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import cl.duoc.level_up_mobile.ui.model.ProductoUi

class FakeProductoViewModel : ViewModel() {

    val productos = mutableStateListOf(
        ProductoUi(
            id = 1,
            titulo = "PS5",
            descripcion = "Consola PlayStation 5",
            precio = 550000.0,
            imagenRes = 0
        ),
        ProductoUi(
            id = 2,
            titulo = "Catan",
            descripcion = "Juego de mesa Catan",
            precio = 25990.0,
            imagenRes = 0
        )
    )

    val loading = false
    val error: String? = null

    fun cargarProductos() {
        // No hace nada en este test
    }
}
