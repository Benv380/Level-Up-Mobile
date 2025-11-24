package cl.duoc.level_up_mobile;

import android.net.Uri
import cl.duoc.level_up_mobile.data.media.MediaRepository
import cl.duoc.level_up_mobile.data.repository.UsuarioRepository
import cl.duoc.level_up_mobile.repository.auth.FirebaseAuthDataSource
import cl.duoc.level_up_mobile.ui.profile.ProfileViewModel
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import kotlin.OptIn;

@OptIn(io.kotest.common.ExperimentalKotest::class) // habilita función experimental de Kotest y acepto los riesgos.
class ProfileViewModelTest : StringSpec({ // define que cada test se nombra con un string

    // mocks

    //llama a authRepo.currentUser(), finge que no hay usuario logueado
    val authRepo = mockk<FirebaseAuthDataSource> {
            every { currentUser() } returns null
    }


    val mediaRepo = mockk<MediaRepository>(relaxed = true) // relaxed =  true MockK no hace nada pero tampoco falla
    val userRepo = mockk<UsuarioRepository>(relaxed = true)

    "guardarNombre debe copiar editingNombre a nombre y setear el msg" { //Nombre Test
        val vm = ProfileViewModel(authRepo, mediaRepo, userRepo) // Se llama al viewmodel con mocks
        vm.onNombreEdit("Nuevo Nombre") //Actualiza el campo nombre
        vm.guardarNombre() //Ejecuta la funcion para guardar nombre

        val state = vm.ui.value //Obtiene el estado actual del nombre
        state.nombre shouldBe "Nuevo Nombre" //assert: Verificamos el nombre con el esperado
        state.msg shouldBe "Nombre actualizado (local)" //assert: aparezca el mensaje actualizado
    }

    "clearMsg debe dejar msg en null" {
        val vm = ProfileViewModel(authRepo, mediaRepo, userRepo)
        vm.onNombreEdit("Hola")
        vm.guardarNombre()
        vm.clearMsg()

        vm.ui.value.msg shouldBe null //Limpia msg valida que este null
    }

    "setLastSavedPhoto debe actualizar el uri en el estado" {
        val vm = ProfileViewModel(authRepo, mediaRepo, userRepo)
        val fakeUri = mockk<Uri>()
        vm.setLastSavedPhoto(fakeUri)
        vm.ui.value.lastSavedPhoto shouldBe fakeUri //verifica que la foto sea fakeUri
    }
})

