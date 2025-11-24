package ui.feriados

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.duoc.level_up_mobile.data.repository.FeriadoRepository
import cl.duoc.level_up_mobile.model.Feriado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class FeriadoUiState(
    val loading: Boolean = false,
    val items: List<Feriado> = emptyList(),
    val error: String? = null
)

class FeriadoViewModel(
    private val repo: FeriadoRepository = FeriadoRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(FeriadoUiState(loading = true))
    val uiState: StateFlow<FeriadoUiState> = _uiState

    init {
        cargarFeriados()
    }

    fun cargarFeriados() {
        _uiState.value = FeriadoUiState(loading = true)
        viewModelScope.launch {
            try {
                val data = repo.obtenerFeriados()
                _uiState.value = FeriadoUiState(items = data)
            } catch (e: Exception) {
                _uiState.value = FeriadoUiState(error = e.message ?: "Error desconocido")
            }
        }
    }
}
