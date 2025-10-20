package cl.duoc.level_up_mobile.ui.recordatorio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ui.model.Recordatorio
import ui.repository.auth.RecordatorioRepository
import java.text.SimpleDateFormat
import java.util.*

data class RecordatorioUiState(
    val uid: String = "",
    val items: List<Recordatorio> = emptyList(),
    val editingId: Long? = null,
    val mensaje: String = "",
    val fechaCreacion: String = hoy(), // dd/MM/yyyy
    val loading: Boolean = false,
    val error: String? = null,
) {
    companion object {
        fun hoy(): String = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
    }
}

class RecordatorioViewModel(
    private val repo: RecordatorioRepository,
    private val uid: String
) : ViewModel() {

    private val _ui = MutableStateFlow(RecordatorioUiState(uid = uid))
    val ui: StateFlow<RecordatorioUiState> = _ui.asStateFlow()

    init {
        observar()
    }

    private fun observar() {
        viewModelScope.launch {
            repo.observe(uid).collect { list ->
                _ui.update { it.copy(items = list) }
            }
        }
    }

    fun onMensajeChange(v: String) = _ui.update { it.copy(mensaje = v) }

    fun onNuevo() = _ui.update {
        it.copy(
            editingId = null,
            mensaje = "",
            fechaCreacion = RecordatorioUiState.hoy(),
            error = null
        )
    }

    fun onEditar(recordatorio: Recordatorio) = _ui.update {
        it.copy(
            editingId = recordatorio.id,
            mensaje = recordatorio.message,
            fechaCreacion = recordatorio.createdAt,
            error = null
        )
    }

    fun guardar() {
        val s = _ui.value
        val msg = s.mensaje.trim()
        if (msg.isEmpty()) {
            _ui.update { it.copy(error = "El mensaje no puede estar vacío") }
            return
        }
        viewModelScope.launch {
            _ui.update { it.copy(loading = true, error = null) }
            try {
                if (s.editingId == null) {
                    // Create
                    repo.insert(
                        Recordatorio(
                            uid = uid,
                            createdAt = s.fechaCreacion,
                            message = msg
                        )
                    )
                } else {
                    // Update
                    repo.update(
                        Recordatorio(
                            id = s.editingId,
                            uid = uid,
                            createdAt = s.fechaCreacion,
                            message = msg
                        )
                    )
                }
                onNuevo() // limpiar formulario
            } catch (e: Exception) {
                _ui.update { it.copy(error = e.message ?: "Error guardando") }
            } finally {
                _ui.update { it.copy(loading = false) }
            }
        }
    }

    fun eliminar(recordatorio: Recordatorio) {
        viewModelScope.launch {
            _ui.update { it.copy(loading = true, error = null) }
            try {
                repo.delete(recordatorio)
                if (_ui.value.editingId == recordatorio.id) onNuevo()
            } catch (e: Exception) {
                _ui.update { it.copy(error = e.message ?: "Error eliminando") }
            } finally {
                _ui.update { it.copy(loading = false) }
            }
        }
    }
}
