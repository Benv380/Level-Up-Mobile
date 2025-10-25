package ui.profile

import android.Manifest
import android.os.Build
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cl.duoc.level_up_mobile.ProfileViewModel
import coil.compose.rememberAsyncImagePainter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(vm: ProfileViewModel) {
    val ui by vm.ui.collectAsState()
    val context = LocalContext.current

    var hasCamera by remember { mutableStateOf(false) }
    var hasRead by remember { mutableStateOf(false) }

    val cameraPermLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted -> hasCamera = granted }

    val readPerm = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        Manifest.permission.READ_MEDIA_IMAGES else Manifest.permission.READ_EXTERNAL_STORAGE

    val readPermLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted -> hasRead = granted }

    var pendingUri by remember { mutableStateOf<android.net.Uri?>(null) }
    val takePictureLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.TakePicture()
    ) { ok ->
        if (ok && pendingUri != null) {
            vm.setLastSavedPhoto(pendingUri)
            Toast.makeText(context, "Foto guardada en galería", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "No se pudo tomar la foto", Toast.LENGTH_SHORT).show()
        }
        pendingUri = null
    }

    Scaffold(topBar = { TopAppBar(title = { Text("Perfil") }) }) { inner ->
        Column(
            Modifier
                .padding(inner)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text("Correo: ${ui.email ?: "No disponible"}")
            Text("UID: ${ui.uid ?: "No disponible"}")
            Text("Nombre: ${ui.displayName ?: "No disponible"}")

            if (ui.lastSavedPhoto != null) {
                Image(
                    painter = rememberAsyncImagePainter(ui.lastSavedPhoto),
                    contentDescription = "Última foto",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(220.dp)
                )
            }

            OutlinedButton(onClick = {
                if (!hasCamera) cameraPermLauncher.launch(Manifest.permission.CAMERA)
                if (!hasRead) readPermLauncher.launch(readPerm)

                val dest = vm.createDestinationUriForCurrentUser(context)
                if (dest == null) {
                    vm.setError("No se pudo crear destino (UID no disponible)")
                    return@OutlinedButton
                }
                pendingUri = dest
                takePictureLauncher.launch(dest)
            }) {
                Icon(Icons.Outlined.CameraAlt, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text("Tomar foto y guardar en galería")
            }

            ui.error?.let {
                Text(it, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}