package data.repository

import data.remote.RetrofitWeatherClient
import data.remote.dto.WeatherResponse

class WeatherRepository {

    private val api = RetrofitWeatherClient.api

    suspend fun getSantiagoWeather(): WeatherResponse {
        return api.getWeather(
            lat = -33.45,
            lon = -70.66
        )
    }
}
