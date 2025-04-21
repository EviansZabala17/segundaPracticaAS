
package com.example.clase16_4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Mic

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.draw.clip
import androidx.compose.foundation.shape.RoundedCornerShape

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavegadorUI()
        }
    }
}

@Composable
fun NavegadorUI() {
    val context = LocalContext.current
    var appAbierta by remember { mutableStateOf("") }

    val apps = listOf(
        AppItem("Facebook", "https://www.facebook.com", R.drawable.facebook),
        AppItem("YouTube", "https://www.youtube.com", R.drawable.youtube),
        AppItem("Google", "https://www.google.com", R.drawable.google),
        AppItem("WhatsApp", "https://www.whatsapp.com", R.drawable.whatsapp),
        AppItem("GTmetrix", "https://www.gtmetrix.com", R.drawable.gtmetrix),
        AppItem("Acceso directo", "https://www.google.com", R.drawable.plus) // El ícono "+"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Logo Google
        Image(
            painter = painterResource(id = R.drawable.googlegrande),
            contentDescription = "Google Logo",
            modifier = Modifier
                .height(100.dp)
                .padding(vertical = 16.dp),
            contentScale = ContentScale.Fit
        )

        // Barra de búsqueda
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Buscar en Google o escribir una URL") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            enabled = false,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Mic,
                    contentDescription = "Micrófono",
                    tint = Color.Gray
                )
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Texto con la app abierta (más arriba)
        if (appAbierta.isNotEmpty()) {
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Abriendo: $appAbierta",
                color = Color.Blue,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        // Fila horizontal de apps
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            apps.forEach { app ->
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(app.url))
                            context.startActivity(intent)
                            appAbierta = app.name
                        }
                        .padding(4.dp)
                ) {
                    Image(
                        painter = painterResource(id = app.iconId),
                        contentDescription = app.name,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = app.name,
                        fontSize = 12.sp
                    )
                }
            }
        }
    }

}



data class AppItem(val name: String, val url: String, val iconId: Int)

@Preview(showBackground = true)
@Composable
fun PreviewNavegadorUI() {
    NavegadorUI()
}
