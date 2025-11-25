package data.remote

import cl.duoc.level_up_mobile.data.remote.dto.FeriadoResponse
import retrofit2.http.GET

interface ApiService {
    @GET("holidays.json")
    suspend fun getFeriados(): FeriadoResponse

}