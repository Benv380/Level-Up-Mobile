package model

import com.google.gson.annotations.SerializedName

data class Producto(
    @SerializedName("id_producto")
    val id: Long,

    val nombre: String,
    val descripcion: String,
    val precio: Double,

    // este campo lo agregaste en Oracle (VARCHAR2)
    val imagen: String
)
