package ui.principal

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.repository.ProductoRepository
import kotlinx.coroutines.launch
import model.Producto

class ProductoViewModel : ViewModel() {

    private val repository = ProductoRepository()

    var productos by mutableStateOf<List<Producto>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMensaje by mutableStateOf<String?>(null)
        private set

    init {
        cargarProductos()
    }

    fun cargarProductos() {
        viewModelScope.launch {
            try {
                isLoading = true
                errorMensaje = null
                productos = repository.getProductos()
            } catch (e: Exception) {
                e.printStackTrace()
                errorMensaje = "Error al cargar productos"
            } finally {
                isLoading = false
            }
        }
    }

    fun crear(producto: Producto) {
        viewModelScope.launch {
            try {
                repository.crearProducto(producto)
                cargarProductos()
            } catch (e: Exception) {
                errorMensaje = "No se pudo crear el producto"
            }
        }
    }

    fun actualizar(id: Long, producto: Producto) {
        viewModelScope.launch {
            try {
                repository.actualizarProducto(id, producto)
                cargarProductos()
            } catch (e: Exception) {
                errorMensaje = "No se pudo actualizar el producto"
            }
        }
    }

    fun eliminar(id: Long) {
        viewModelScope.launch {
            try {
                repository.eliminarProducto(id)
                cargarProductos()
            } catch (e: Exception) {
                errorMensaje = "No se pudo eliminar el producto"
            }
        }
    }
}
