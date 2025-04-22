package com.example.ejer4practica2


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.platform.LocalUriHandler

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent  {
                MyApp()
            }
        }
    }



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") { MenuScreen(navController) }
        composable("perfil") { PerfilScreen(navController) }
        composable("calificaciones") { CalificacionesScreen(navController) }
        composable("calendario") { CalendarioScreen(navController) }
        composable("archivos") { ArchivosPrivadosScreen(navController) }
        composable("informes") { InformesScreen(navController) }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarWithBackButton(title: String, navController: NavHostController) {
    TopAppBar(
        title = { Text(title, color = Color.White) },
        navigationIcon = {
            IconButton(onClick = { navController.navigate("menu") }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Volver", tint = Color.White)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF00356B),
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        )
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("") },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF00356B), // Yale Blue
                titleContentColor = Color.White
            ),
            actions = {
                Icon(
                    Icons.Default.Notifications,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Icon(
                    Icons.Default.Chat,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Box {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable { expanded = true }
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = Color.LightGray,
                            modifier = Modifier.size(36.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                                Text("EZ", fontWeight = FontWeight.Bold)
                            }
                        }
                        Icon(
                            imageVector = Icons.Default.ArrowDropDown,
                            contentDescription = "Desplegar menú",
                            tint = Color.White
                        )
                    }

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(text = { Text("Perfil") }, onClick = {
                            expanded = false
                            navController.navigate("perfil")
                        })
                        DropdownMenuItem(text = { Text("Calificaciones") }, onClick = {
                            expanded = false
                            navController.navigate("calificaciones")
                        })
                        DropdownMenuItem(text = { Text("Calendario") }, onClick = {
                            expanded = false
                            navController.navigate("calendario")
                        })
                        DropdownMenuItem(text = { Text("Archivos privados") }, onClick = {
                            expanded = false
                            navController.navigate("archivos")
                        })
                        DropdownMenuItem(text = { Text("Informes") }, onClick = {
                            expanded = false
                            navController.navigate("informes")
                        })
                    }
                }
            }
        )
    }
}




@Composable
fun PerfilScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBarWithBackButton("Perfil", navController)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.mi_foto),
                contentDescription = null,
                modifier = Modifier.size(150.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text("UNIVERSIDAD MAYOR DE SAN ANDRES")
            Text("Materia: Dispositivos Moviles II")
            Text("Nombre: Evians Zabala")
        }
    }
}


@Composable
fun CalificacionesScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBarWithBackButton("Calificaciones", navController)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Ponderaciones de la Materia", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.ponderaciones),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
        }
    }
}


@Composable
fun CalendarioScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBarWithBackButton("Calendario", navController)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(5.dp))
            Image(
                painter = painterResource(id = R.drawable.calendario),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().padding(0.55.dp)
            )
        }
    }
}

@Composable
fun ArchivosPrivadosScreen(navController: NavHostController) {
    val uriHandler = LocalUriHandler.current
    Column(modifier = Modifier.fillMaxSize()) {
        TopBarWithBackButton("Archivos Privados", navController)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Archivos de práctica en OneDrive")
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = {
                uriHandler.openUri("https://drive.google.com/drive/folders/1Jt1HUy5vKUTyZVAJLxaDi7uyWzV9iLRN?usp=sharing")
            }) {
                Text("Abrir OneDrive")
            }
        }
    }
}


@Composable
fun InformesScreen(navController: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        TopBarWithBackButton("Informes", navController)
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Guía de Prácticas")
            Spacer(modifier = Modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.practica1),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().padding(8.dp)
            )
        }
    }
}
