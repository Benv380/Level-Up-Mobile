package cl.duoc.level_up_mobile.ui.vmfactory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import data.media.MediaRepository
import ui.profile.ProfileViewModel
import ui.repository.auth.FirebaseAuthDataSource


class ProfileVMFactory(
    private val authDs: FirebaseAuthDataSource,
    private val mediaRepo: MediaRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val vm = when (modelClass) {
            ProfileViewModel::class.java -> ProfileViewModel(authDs, mediaRepo)
            else -> error("VM no soportado: $modelClass")
        }
        @Suppress("UNCHECKED_CAST")
        return vm as T
    }
}