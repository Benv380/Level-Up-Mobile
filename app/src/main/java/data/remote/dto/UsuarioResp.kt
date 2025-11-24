package cl.duoc.level_up_mobile.data.remote.dto

// Modelo de respuesta que representa lo que el backend devuelve al cliente
data class UsuarioResp(
    val rut: String,
    val nombre: String,
    val mail: String,
    val idrol: Int,
    val idfirebase: String?,
    val imagen: String?
)
