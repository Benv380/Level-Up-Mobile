package cl.duoc.level_up_mobile.ui

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cl.duoc.level_up_mobile.R

@Composable
fun AnimatedLogo() {
    // Estado para controlar la animación de entrada (escala y opacidad)
    var visible by remember { mutableStateOf(false) }

    // Efecto de "pop" inicial
    val scale by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 1200, easing = FastOutSlowInEasing)
    )

    // Efecto de latido (loop suave)
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val pulse by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = ""
    )

    // Cuando se compone por primera vez, activa la animación de entrada
    LaunchedEffect(Unit) {
        visible = true
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logolevelup), // tu logo
            contentDescription = "Logo LevelUp",
            modifier = Modifier
                .size(160.dp)
                .scale(scale * pulse),
            colorFilter = null // puedes aplicar ColorFilter.tint(Color.White) si tu logo es vector
        )
    }
}
