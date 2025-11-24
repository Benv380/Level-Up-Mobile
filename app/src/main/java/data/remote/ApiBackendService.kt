package cl.duoc.level_up_mobile

import cl.duoc.level_up_mobile.data.remote.dto.UsuarioDto
import cl.duoc.level_up_mobile.data.remote.dto.UsuarioResp
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody


// Cliente Retrofit que define los endpoints HTTP para comunicar tu app con tu microservicio (tu backend en Spring Boot).
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