package ui.feriados

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cl.duoc.level_up_mobile.model.Feriado

@Composable
fun FeriadoScreen(viewModel: FeriadoViewModel) {
    val state by viewModel.uiState.collectAsState()

    when {
        state.loading -> CargandoBox()
        state.error != null -> ErrorBox(mensaje = state.error ?: "Error") {
            viewModel.cargarFeriados()
        }
        else -> ListaFeriados(state.items)
    }
}

@Composable
private fun CargandoBox() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ErrorBox(mensaje: String, onReintentar: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Ocurrió un problema:\n$mensaje")
            Spacer(Modifier.height(12.dp))
            Button(onClick = onReintentar) { Text("Reintentar") }
        }
    }
}

@Composable
private fun ListaFeriados(items: List<Feriado>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items) { f -> TarjetaFeriado(f) }
    }
}

@Composable
private fun TarjetaFeriado(f: Feriado) {
    Card {
        Column(Modifier.padding(16.dp)) {
            Text(f.title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(4.dp))
            Text("Fecha: ${f.date}")
            Text("Tipo: ${f.type}")
            if (f.inalienable) {
                AssistChip(label = { Text("Irrenunciable") }, onClick = {}, modifier = Modifier.padding(top = 6.dp))
            }
            f.extra?.let {
                if (it.isNotBlank()) {
                    Spacer(Modifier.height(6.dp))
                    Text(it, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}
