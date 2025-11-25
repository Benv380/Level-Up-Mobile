package cl.duoc.level_up_mobile.data.remote

import cl.duoc.level_up_mobile.data.remote.dto.ProductoApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProductoClient {

    // 🔥 Usa 10.0.2.2 si estás en emulador Android
    private const val BASE_URL = "http://10.0.2.2:8080/"

    private val logger = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val okHttp = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

    val api: ProductoApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductoApiService::class.java)
    }
}
