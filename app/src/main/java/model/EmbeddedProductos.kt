package cl.duoc.level_up_mobile.data.model

import com.google.gson.annotations.SerializedName
import model.Producto

data class EmbeddedProductos(
    @SerializedName("productList")
    val productos: List<Producto>
)
