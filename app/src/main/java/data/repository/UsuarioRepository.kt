package cl.duoc.level_up_mobile.data.repository


import cl.duoc.level_up_mobile.data.remote.dto.UsuarioDto
import cl.duoc.level_up_mobile.data.remote.dto.UsuarioResp
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response
import java.io.File
import retrofit2.HttpException
import kotlin.jvm.java
import kotlin.to

class UsuarioRepository(
    private val api: ApiBackendService =
        RetrofitClient.retrofitBackend.create(ApiBackendService::class.java)
) {

    suspend fun crearUsuario(dto: UsuarioDto): Boolean {
        val r = api.crearUsuario(dto)
        return r.isSuccessful
    }

    suspend fun subirImagen(rut: String, idFirebase: String, file: File): Boolean {
        val idPart = idFirebase.toRequestBody("text/plain".toMediaTypeOrNull())
        val imgPart = MultipartBody.Part.createFormData(
            name = "imagen",
            filename = file.name,
            body = file.asRequestBody("image/*".toMediaTypeOrNull())
        )
        val r = api.actualizarUsuarioConImagen(rut, idPart, imgPart)
        if (!r.isSuccessful) throw HttpException(r)
        // si quieres, puedes leer el texto: r.body()?.string() == "Usuario actualizado"
        return true
    }

    suspend fun buscarPorFirebase(uid: String): UsuarioResp? =
        api.getByFirebase(uid).body()

    // Actualizar nombre (requiere endpoint backend /{rut}/nombre)
    suspend fun actualizarNombre(rut: String, nuevoNombre: String): UsuarioResp? {
        val r = api.updateNombre(rut, mapOf("nombre" to nuevoNombre))
        if (!r.isSuccessful) throw HttpException(r)
        return r.body()
    }

    // Cargar usuario por UID de Firebase
    suspend fun cargarPorFirebase(uid: String): UsuarioResp? {
        val r = api.getByFirebase(uid)
        if (!r.isSuccessful) throw HttpException(r)
        return r.body()
    }

    suspend fun obtenerUsuarioPorFirebase(idFirebase: String): UsuarioResp? {
        val res = api.getByFirebase(idFirebase)
        return if (res.isSuccessful) res.body() else null
    }
}
