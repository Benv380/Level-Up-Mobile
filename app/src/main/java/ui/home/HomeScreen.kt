package cl.duoc.level_up_mobile.ui.home

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.duoc.level_up_mobile.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun HomeScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onRecoverClick: () -> Unit
) {
    Scaffold(
        topBar = { InfiniteHorizontalScrollText( "Level-Up") }
    ) { innerPadding ->
        HomeContent(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            onLoginClick = onLoginClick,
            onRegisterClick = onRegisterClick,
            onRecoverClick = onRecoverClick
        )
    }
}

@Composable
fun InfiniteHorizontalScrollText(
    text: String,
    modifier: Modifier = Modifier,
) {
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()

    // El efecto se lanza cuando el texto cambia
    LaunchedEffect(text) {
        coroutineScope.launch {
            while (true) {
                // Desplázate hasta el final
                scrollState.animateScrollTo(
                    value = scrollState.maxValue,
                    animationSpec = tween(
                        durationMillis = 10000, // Ajusta la velocidad
                        easing = LinearEasing
                    )
                )
                // Reinicia al principio instantáneamente para un bucle
                scrollState.scrollTo(0)
            }
        }
    }

    // El contenedor que ocupa todo el ancho
    Box(
        modifier = modifier.fillMaxWidth()
            .height(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.horizontalScroll(scrollState),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Duplica el texto para un desplazamiento continuo
            Text(text = text, style = MaterialTheme.typography.bodyLarge, softWrap = false)
            Text(text = text, style = MaterialTheme.typography.bodyLarge, softWrap = false)
        }
    }
}

@Composable
private fun HomeContent(
    modifier: Modifier = Modifier,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onRecoverClick: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo App",
            modifier = Modifier
                .width(900.dp)
                .height(200.dp),
            contentScale = ContentScale.Fit
        )
        Text(
            text = "¡Bienvenido!" ,
            fontSize = 50.sp)
        Button(onClick = onLoginClick) { Text("Login" ,
            fontSize = 22.sp,
            modifier = Modifier.size(width = 250.dp, height = 40.dp)
                .wrapContentHeight()
                .fillMaxWidth(),
            textAlign = TextAlign.Center
            ) }
        Button(onClick = onRegisterClick) { Text("Register",
            fontSize = 22.sp,
            modifier = Modifier.size(width = 250.dp, height = 40.dp)
                .wrapContentHeight()
                .fillMaxWidth(),
            textAlign = TextAlign.Center) }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            TextButton(onClick = onRecoverClick) { Text("Recuperar contraseña",
                fontSize = 20.sp) }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        onLoginClick = {},
        onRegisterClick = {},
        onRecoverClick = {}
    )
}
