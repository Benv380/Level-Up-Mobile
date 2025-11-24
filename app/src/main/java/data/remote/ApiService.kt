package cl.duoc.level_up_mobile.data.remote.dto.FeriadoResponse

interface ApiService {
    @GET("holidays.json")
    suspend fun getFeriados(): FeriadoResponse

}