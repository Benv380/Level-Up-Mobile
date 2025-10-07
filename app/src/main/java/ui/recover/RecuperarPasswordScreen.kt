package cl.daeriquelme.appduoc_profe.ui

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecuperarPasswordScreen(
    onBack: () -> Unit,
    onSent: () -> Unit
) {
    val context = LocalContext.current
    val auth = remember { FirebaseAuth.getInstance() }

    var email by rememberSaveable { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    var errorMsg by remember { mutableStateOf<String?>(null) }

    fun validar(): Boolean {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            errorMsg = "Email inválido"; return false
        }
        errorMsg = null; return true
    }

    fun sendReset() {
        if (!validar()) return
        loading = true
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                loading = false
                if (task.isSuccessful) {
                    Toast.makeText(context, "Correo de recuperación enviado.", Toast.LENGTH_SHORT).show()
                    onSent()
                } else {
                    errorMsg = task.exception?.localizedMessage ?: "No se pudo enviar el correo"
                }
            }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Recuperar contraseña") },
                navigationIcon = { TextButton(onClick = onBack) { Text("Atrás") } }
            )
        }
    ) { inner ->
        Box(
            modifier = Modifier
                .padding(inner)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.TopCenter),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedTextField(
                    value = email, onValueChange = { email = it },
                    label = { Text("Correo electrónico") }, singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    modifier = Modifier.fillMaxWidth()
                )

                if (errorMsg != null) {
                    Text(errorMsg!!, color = MaterialTheme.colorScheme.error)
                }

                Button(
                    onClick = { sendReset() },
                    enabled = !loading,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (loading) "Enviando..." else "Enviar correo de recuperación")
                }
            }

            if (loading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center).size(48.dp)
                )
            }
        }
    }
}
