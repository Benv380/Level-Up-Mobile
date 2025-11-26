package cl.duoc.level_up_mobile.ui.principal

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import model.Producto

class FakeProductoViewModel : ViewModel() {

    val productos = mutableStateListOf(
        Producto(1, "PS5", 550000.0, "playstation5"),
        Producto(2, "Catan", 25990.0, "catan")
    )

    val loading = false
    val error: String? = null

    fun cargarProductos() {
        // No hace nada en este test
    }
}
