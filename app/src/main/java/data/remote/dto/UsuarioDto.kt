package cl.duoc.level_up_mobile.data.remote.dto

// Modelo intermedio para enviar datos entre la app Android y tu backend
data class UsuarioDto(
    val rut: String,
    val nombre: String,
    val mail: String,
    val password: String,
    val idrol: Int,
    val idfirebase: String
)

