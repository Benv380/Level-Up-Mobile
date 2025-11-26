package cl.duoc.level_up_mobile.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ProductoDto(
    @SerializedName("id_producto")
    val id: Long,
    val nombre: String,
    val descripcion: String,
    val precio: Double
)
