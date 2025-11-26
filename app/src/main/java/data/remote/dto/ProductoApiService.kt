package cl.duoc.level_up_mobile.data.remote.dto

import retrofit2.http.GET

interface ProductoApiService {
    @GET("catalogo")
    suspend fun getProductos(): ProductosResponse
}
