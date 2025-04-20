package com.example.clase16_4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ATBScreen()
        }
    }
}

@Composable
fun ATBScreen() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp), // aseguramos que no haya espacio extra
        verticalArrangement = Arrangement.spacedBy(0.dp) // quita espacio entre elementos
    ) {
        // Logo
        Image(
            painter = painterResource(id = R.drawable.logo1),
            contentDescription = "ATB Logo",
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.18f)
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.atb.com.bo/"))
                    context.startActivity(intent)
                },
            contentScale = ContentScale.Crop
        )

        // Imagen de la noticia
        Image(
            painter = painterResource(id = R.drawable.noticia_imagen),
            contentDescription = "Imagen Noticia",
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.25f), // le bajamos un poquito para dar más espacio al texto
            contentScale = ContentScale.Crop
        )

        // Texto inferior con fondo degradado
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.30f)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF330099),
                            Color(0xFF000066)
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "TRANSPORTE LIBRE DE\nLA PAZ SE PRONUNCIA\nANTE EL\nCONGELAMIENTO\nDE LOS PASAJES",
                color = Color.Cyan,
                fontSize = 20.sp,
                lineHeight = 26.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewATBScreen() {
    ATBScreen()
}
