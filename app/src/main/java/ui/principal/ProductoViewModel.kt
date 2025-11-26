package cl.duoc.level_up_mobile.ui.principal


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.repository.ProductoRepository
import kotlinx.coroutines.launch
import model.Producto

class ProductoViewModel(
    private val repo: ProductoRepository = ProductoRepository()
) : ViewModel() {

    var productos by mutableStateOf<List<Producto>>(emptyList())
        private set

    var loading by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    fun cargarProductos() {
        viewModelScope.launch {
            try {
                loading = true
                error = null
                productos = repo.getProductos()
            } catch (e: Exception) {
                error = "Error cargando productos"
            } finally {
                loading = false
            }
        }
    }
}
