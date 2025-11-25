package cl.duoc.level_up_mobile.data.model

import com.google.gson.annotations.SerializedName

data class ProductoResponse(
    @SerializedName("_embedded")
    val embedded: EmbeddedProductos
)
