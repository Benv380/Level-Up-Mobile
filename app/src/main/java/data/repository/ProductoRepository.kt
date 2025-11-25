package cl.duoc.level_up_mobile.data.repository

import cl.duoc.level_up_mobile.data.remote.RetrofitProductoClient
import model.Producto

class ProductoRepository {

    private val api = RetrofitProductoClient.api

    suspend fun getProductos(): List<Producto> {
        val response = api.getProductos()
        return response.embedded.productos
    }
}
