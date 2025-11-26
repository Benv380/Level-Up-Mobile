package cl.duoc.level_up_mobile.data.remote.dto

import com.google.gson.annotations.SerializedName
import model.Producto

data class ProductosResponse(
    @SerializedName("_embedded")
    val embedded: EmbeddedProductos
)

data class EmbeddedProductos(
    @SerializedName("productList")
    val productList: List<Producto>
)
