package model

import com.google.gson.annotations.SerializedName

data class Producto(
    @SerializedName("id_producto")
    val id: Long,
    val nombre: String,
    val descripcion: String,
    val precio: Double,
    val imagen: String? = null // opcional si no viene desde la API
)
