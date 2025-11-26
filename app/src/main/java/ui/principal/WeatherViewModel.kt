package ui.principal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.remote.dto.CurrentWeather
import data.repository.WeatherRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class WeatherUiState(
    val loading: Boolean = true,
    val data: CurrentWeather? = null,
    val error: String? = null
)

class WeatherViewModel(
    private val repo: WeatherRepository = WeatherRepository()
) : ViewModel() {

    private val _ui = MutableStateFlow(WeatherUiState())
    val ui: StateFlow<WeatherUiState> = _ui

    init {
        loadWeather()
    }

    private fun loadWeather() {
        viewModelScope.launch {
            try {
                val weather = repo.getSantiagoWeather().current_weather
                _ui.value = WeatherUiState(
                    loading = false,
                    data = weather
                )
            } catch (e: Exception) {
                _ui.value = WeatherUiState(
                    loading = false,
                    error = e.message
                )
            }
        }
    }
}
