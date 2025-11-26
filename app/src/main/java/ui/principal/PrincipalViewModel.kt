package cl.duoc.level_up_mobile.ui.principal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import repository.auth.AuthRepository

data class PrincipalUiState(
    val email: String? = null,
    val loading: Boolean = false,
    val error: String? = null,
    val loggedOut: Boolean = false
)

class PrincipalViewModel(
    private val authRepo: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _ui = MutableStateFlow(PrincipalUiState())
    val ui: StateFlow<PrincipalUiState> = _ui

    init {
        val user = authRepo.currentUser()
        _ui.update { it.copy(email = user?.email) }
    }

    fun logout() {
        viewModelScope.launch {
            _ui.update { it.copy(loading = true, error = null) }
            try {
                authRepo.logout()
                _ui.update { it.copy(loading = false, loggedOut = true) }
            } catch (e: Exception) {
                _ui.update { it.copy(loading = false, error = e.message) }
            }
        }
    }
}
