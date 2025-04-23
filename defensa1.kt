package com.example.defensa_de_practica2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.platform.LocalUriHandler

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector


import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            AppContent(navController)
        }
    }
}

@Composable
fun AppContent(navController: NavHostController) {
    NavHost(navController, startDestination = "menu") {
        composable("menu") { MainMenu(navController) }
        composable("beca/{tipo}") { backStack ->
            val tipo = backStack.arguments?.getString("tipo") ?: ""
            BecaScreen(tipo)
        }
    }
}  
@Composable
fun MainMenu(navController: NavHostController) {
    val azulYale = Color(0xFF0F4D92)
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Fila superior (2 cuadros)
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            MenuCard(icon = Icons.Default.School, label = "Oferta Académica", color = azulYale)
            MenuCard(icon = Icons.Default.Public, label = "Servicios Virtuales", color = azulYale)
        }

        // Fila inferior (3 cuadros)
        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxWidth()) {
            MenuCard(icon = Icons.Default.Person, label = "Convocatorias\nPregrado", color = azulYale)
            Box {
                MenuCard(
                    icon = Icons.Default.School,
                    label = "Becas\nPregrado",
                    color = azulYale,
                    onClick = { expanded = !expanded }
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(Color.White)
                ) {
                    listOf("Beca Comedor", "Beca Deportes", "Beca Académica", "Beca Convenio").forEach { beca ->
                        DropdownMenuItem(
                            text = { Text(beca) },
                            onClick = {
                                expanded = false
                                navController.navigate("beca/${beca.replace(" ", "").lowercase()}")
                            }
                        )
                    }
                }
            }
            MenuCard(icon = Icons.Default.Description, label = "Convocatoria\nDocente", color = azulYale)
        }
    }
}

@Composable
fun MenuCard(icon: ImageVector, label: String, color: Color, onClick: () -> Unit = {}) {
    Box(
        modifier = Modifier
            .size(110.dp)
            .background(color, shape = RoundedCornerShape(16.dp))
            .clickable { onClick() }
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Icon(imageVector = icon, contentDescription = label, tint = Color.White, modifier = Modifier.size(32.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = label, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun BecaScreen(tipo: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Información de: ${tipo.replaceFirstChar { it.uppercase() }}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(id = R.drawable.umsa),
            contentDescription = "UMSA",
            modifier = Modifier.size(200.dp)
        )
    }
}
