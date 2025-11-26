package data.remote

import cl.duoc.level_up_mobile.data.remote.dto.UsuarioDto
import cl.duoc.level_up_mobile.data.remote.dto.UsuarioResp
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.Response

interface ApiBackendService {

    // POST: crear usuario SIN imagen
    @POST("api/v1/Usuarios")
    suspend fun crearUsuario(@Body body: UsuarioDto): Response<ResponseBody>

    // PUT: subir/actualizar imagen
    @Multipart
    @PUT("api/v1/Usuarios/{rut}")
    suspend fun actualizarUsuarioConImagen(
        @Path("rut") rut: String,
        @Part("idFirebase") idFirebase: RequestBody?,
        @Part imagen: MultipartBody.Part?
    ): Response<ResponseBody>

    // GET: buscar por idFirebase
    @GET("api/v1/Usuarios/firebase/{idfirebase}")
    suspend fun getByFirebase(@Path("idfirebase") idfirebase: String): Response<UsuarioResp>

    // Obtener imagen por rut
    @GET("api/v1/Usuarios/{rut}/imagen")
    suspend fun getImageByRut(@Path("rut") rut: String): Response<ResponseBody>

    // Actualizar nombre
    @PUT("api/v1/Usuarios/{rut}/nombre")
    suspend fun updateNombre(
        @Path("rut") rut: String,
        @Body body: Map<String, String>
    ): Response<UsuarioResp>
}