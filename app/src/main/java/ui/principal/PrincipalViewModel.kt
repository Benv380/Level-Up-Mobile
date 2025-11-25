package cl.duoc.level_up_mobile.ui.principal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duoc.level_up_mobile.data.repository.ProductoRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import model.Producto
import repository.auth.AuthRepository

data class PrincipalUiState(
    val email: String? = null,
    val loading: Boolean = false,
    val error: String? = null,
    val loggedOut: Boolean = false
)

class PrincipalViewModel(
    private val authRepo: AuthRepository = AuthRepository(),
    private val productoRepo: ProductoRepository = ProductoRepository()
) : ViewModel() {

    // ======= UI STATE (LOGIN Y SESIÓN) =======
    private val _ui = MutableStateFlow(PrincipalUiState())
    val ui: StateFlow<PrincipalUiState> = _ui

    init {
        val user = authRepo.currentUser()
        _ui.update { it.copy(email = user?.email) }
        cargarProductos()   // 🔥 carga productos REALES al iniciar
    }

    fun logout() {
        viewModelScope.launch {
            _ui.update { it.copy(loading = true, error = null) }
            try {
                authRepo.logout()
                _ui.update { it.copy(loading = false, loggedOut = true) }
            } catch (e: Exception) {
                _ui.update { it.copy(loading = false, error = e.message ?: "Error al cerrar sesión") }
            }
        }
    }

    // ======= PRODUCTOS REAL DEL BACKEND =======
    private val _productos = MutableStateFlow<List<Producto>>(emptyList())
    val productos: StateFlow<List<Producto>> = _productos

    private val _loadingProductos = MutableStateFlow(false)
    val loadingProductos: StateFlow<Boolean> = _loadingProductos

    private val _errorProductos = MutableStateFlow<String?>(null)
    val errorProductos: StateFlow<String?> = _errorProductos

    fun cargarProductos() {
        viewModelScope.launch {
            try {
                _loadingProductos.value = true
                _errorProductos.value = null
                _productos.value = productoRepo.getProductos()
            } catch (e: Exception) {
                _errorProductos.value = "Error al cargar productos"
            } finally {
                _loadingProductos.value = false
            }
        }
    }

    // ======= CATEGORÍAS =======
    private val _categoriaSel = MutableStateFlow("Todos")
    val categoriaSel: StateFlow<String> = _categoriaSel

    // 🔥 categorías dinámicas desde backend
    val categorias: StateFlow<List<String>> = productos
        .map { productos ->
            listOf("Todos") + productos
                .map { it.descripcion } // o it.categoria si la agregas a BD
                .distinct()
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, listOf("Todos"))

    fun setCategoria(cat: String) {
        _categoriaSel.value = cat
    }

    // ======= PRODUCTOS FILTRADOS =======
    val productosFiltrados: StateFlow<List<Producto>> =
        combine(productos, categoriaSel) { lista, cat ->
            if (cat == "Todos") lista
            else lista.filter { it.descripcion.contains(cat, ignoreCase = true) }
        }.stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            emptyList()
        )
}
