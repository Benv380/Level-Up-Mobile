package ui.principal.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.remote.dto.CurrentWeather

@Composable
fun WeatherCard(weather: CurrentWeather?) {
    if (weather == null) return

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Clima en Santiago", style = MaterialTheme.typography.titleMedium)
            Spacer(Modifier.height(4.dp))
            Text("🌡 Temperatura: ${weather.temperature}°C")
            Text("💨 Viento: ${weather.windspeed} km/h")
        }
    }
}
