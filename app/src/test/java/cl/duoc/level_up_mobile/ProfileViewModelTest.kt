package cl.duoc.level_up_mobile

import android.content.Context
import android.net.Uri
import com.google.firebase.auth.FirebaseUser
import data.media.MediaRepository
import repository.auth.FirebaseAuthDataSource
import ui.profile.ProfileViewModel
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import org.junit.Assert.*

class ProfileViewModelTest {

    @Test
    fun init_debe_cargar_los_datos_del_usuario_actual_en_el_estado() {
        // given
        val fakeUser = mockk<FirebaseUser> {
            every { uid } returns "uid123"
            every { email } returns "test@correo.com"
            every { displayName } returns "Usuario Test"
        }

        val authRepo = mockk<FirebaseAuthDataSource> {
            every { currentUser() } returns fakeUser
        }
        val mediaRepo = mockk<MediaRepository>(relaxed = true)

        // when
        val vm = ProfileViewModel(authRepo, mediaRepo)

        // then
        val state = vm.ui.value
        assertEquals("uid123", state.uid)
        assertEquals("test@correo.com", state.email)
        assertEquals("Usuario Test", state.displayName)
    }

    @Test
    fun setLastSavedPhoto_debe_actualizar_el_uri_en_el_estado() {
        val authRepo = mockk<FirebaseAuthDataSource> {
            every { currentUser() } returns null
        }
        val mediaRepo = mockk<MediaRepository>(relaxed = true)

        val vm = ProfileViewModel(authRepo, mediaRepo)

        val fakeUri = mockk<Uri>()
        vm.setLastSavedPhoto(fakeUri)

        assertEquals(fakeUri, vm.ui.value.lastSavedPhoto)
    }

    @Test
    fun setError_debe_actualizar_el_mensaje_de_error() {
        val authRepo = mockk<FirebaseAuthDataSource> {
            every { currentUser() } returns null
        }
        val mediaRepo = mockk<MediaRepository>(relaxed = true)

        val vm = ProfileViewModel(authRepo, mediaRepo)

        vm.setError("Algo salió mal")

        assertEquals("Algo salió mal", vm.ui.value.error)
    }

    @Test
    fun createDestinationUriForCurrentUser_debe_devolver_null_si_no_hay_uid() {
        val authRepo = mockk<FirebaseAuthDataSource> {
            every { currentUser() } returns null
        }
        val mediaRepo = mockk<MediaRepository>(relaxed = true)

        val vm = ProfileViewModel(authRepo, mediaRepo)
        val context = mockk<Context>(relaxed = true)

        val result = vm.createDestinationUriForCurrentUser(context)

        assertNull(result)
    }

    @Test
    fun createDestinationUriForCurrentUser_debe_usar_mediaRepo_cuando_hay_uid() {
        val fakeUser = mockk<FirebaseUser> {
            every { uid } returns "uid123"
            every { email } returns "test@correo.com"
            every { displayName } returns "Usuario Test"
        }

        val authRepo = mockk<FirebaseAuthDataSource> {
            every { currentUser() } returns fakeUser
        }

        val expectedUri = mockk<Uri>()
        val mediaRepo = mockk<MediaRepository> {
            every { createImageUriForUser(any(), "uid123") } returns expectedUri
        }

        val vm = ProfileViewModel(authRepo, mediaRepo)
        val context = mockk<Context>(relaxed = true)

        val result = vm.createDestinationUriForCurrentUser(context)

        assertEquals(expectedUri, result)
    }
}
