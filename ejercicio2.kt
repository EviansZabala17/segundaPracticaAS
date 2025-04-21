package com.example.clase16_4

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VideoCall

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppMeet()
        }
    }
}

@Composable
fun AppMeet() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "inicio") {
        composable("inicio") { PantallaInicio(navController) }
        composable("meet") { PantallaGoogleMeet() }
    }
}

@Composable
fun PantallaInicio(navController: NavController) {
    val context = LocalContext.current
    var codigo by remember { mutableStateOf(TextFieldValue("")) }
    var errorCodigo by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título
        Text(
            text = "Videoconferencias",
            fontSize = 45.sp,
            color = Color.Black
        )
        Text(
            text = " seguras para todos",
            fontSize = 40.sp,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Subtítulo
        Text(
            text = "Conecta, colabora y celebra desde\ncualquier lugar con Google Meet",
            fontSize = 20.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Botón  
        Button(
            onClick = {
                if (codigo.text.trim() == "inf-251") {
                    navController.navigate("meet")
                    errorCodigo = false
                } else {
                    errorCodigo = true
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A73E8)), // estilo Google azul
            shape = RoundedCornerShape(16.dp)
        ) {
            Icon(Icons.Default.VideoCall, contentDescription = "icono", tint = Color.White)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Nueva reunión", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // TextField
        OutlinedTextField(
            value = codigo,
            onValueChange = {
                codigo = it
                errorCodigo = false
            },
            label = { Text("Introduce un código o alias") },
            isError = errorCodigo,
            modifier = Modifier.fillMaxWidth()
        )

        // Mensaje de error si el código es incorrecto
        if (errorCodigo) {
            Text(
                text = "Código incorrecto. Intenta con: inf-251",
                color = Color.Red,
                fontSize = 14.sp,
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        Spacer(modifier = Modifier.height(40.dp))

        Divider(color = Color.Gray.copy(alpha = 0.5f), thickness = 1.dp)

        Spacer(modifier = Modifier.height(20.dp))

        // Simulación de reunión agendada (como "CLASE COM-99")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(
                    color = Color(0xFF1A73E8),
                    shape = RoundedCornerShape(8.dp)
                )
                .clickable {
                    // Redirigir a la sala
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://meet.google.com/"))
                    context.startActivity(intent)
                },
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(
                    text = "19:00",
                    color = Color.White,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(30.dp))
                Text(
                    text = "CLASE COM-99",
                    color = Color.White,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun PantallaGoogleMeet() {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://meet.google.com/"))
        context.startActivity(intent)
    }

    // Pantalla de carga temporal
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Text("Abriendo Google Meet...", fontSize = 22.sp)
    }
}


