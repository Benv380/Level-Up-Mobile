package ui.profile

import android.net.Uri
import androidx.lifecycle.ViewModel
import data.media.MediaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import ui.repository.auth.FirebaseAuthDataSource

data class ProfileUiState(
    val uid: String? = null,
    val email: String? = null,
    val displayName: String? = null,
    val lastSavedPhoto: Uri? = null,
    val error: String? = null
)

class ProfileViewModel(
    private val authRepo: FirebaseAuthDataSource,
    private val mediaRepo: MediaRepository
) : ViewModel() {

    private val _ui = MutableStateFlow(ProfileUiState())
    val ui: StateFlow<ProfileUiState> = _ui

    init {
        val u = authRepo.currentUser()
        _ui.update {
            it.copy(uid = u?.uid, email = u?.email, displayName = u?.displayName)
        }
    }

    fun setLastSavedPhoto(uri: Uri?) {
        _ui.update { it.copy(lastSavedPhoto = uri) }
    }

    fun setError(message: String?) {
        _ui.update { it.copy(error = message) }
    }

    fun createDestinationUriForCurrentUser(context: android.content.Context): Uri? {
        val uid = _ui.value.uid ?: return null
        return mediaRepo.createImageUriForUser(context, uid)
    }
}