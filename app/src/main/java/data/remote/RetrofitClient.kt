package data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.Interceptor

/*RetrofitClient es un objeto singleton (una sola instancia global) que:
Configura la conexión base con tu backend (la URL principal).
Crea la instancia de Retrofit, una librería que traduce tus funciones Kotlin (ApiBackendService) en peticiones HTTP reales.
Se encarga de convertir automáticamente tus objetos (UsuarioDto, UsuarioResp, etc.) a JSON y viceversa, usando Gson o Moshi.*/


object RetrofitClient {

    private const val BASE_URL = "https://api.boostr.cl/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(logging)
        .build()

    val api: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    private const val BACKEND_BASE_URL = "https://webtest.daeriquelme.cl/"
    private const val API_KEY = "sk_live_7Fzj1uY3p9xTQv5aR8nE2mL4wC6kD9qH"

    private val apiKeyInterceptor = Interceptor { chain ->
        val req = chain.request().newBuilder()
            .addHeader("X-API-KEY", API_KEY)
            .build()
        chain.proceed(req)
    }

    private val log = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BASIC
    }

    private val okBackend: OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(apiKeyInterceptor)
        .addInterceptor(log)
        .build()

    val retrofitBackend: Retrofit = Retrofit.Builder()
        .baseUrl(BACKEND_BASE_URL)
        .client(okBackend)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}
