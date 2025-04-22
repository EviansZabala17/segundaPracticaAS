 
package com.example.clase16_4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import kotlin.system.exitProcess

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface(color = MaterialTheme.colorScheme.background) {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") { MenuScreen(navController) }
        composable("presentacion") { PresentacionScreen(navController) }
        composable("documentos") { DocumentosScreen(navController) }
        composable("imagenes") { ImagenesScreen(navController) }
        composable("configuracion") { ConfiguracionScreen(navController) }
        composable("salir") { ExitScreen() }
    }
}

@Composable
fun MenuScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { navController.navigate("presentacion") }
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.mi_foto),
                contentDescription = "Foto de perfil",
                modifier = Modifier.size(30.dp).clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text("Evians Zabala", fontSize = 20.sp, fontWeight = FontWeight.Medium)
        }
        MenuItem("Documentos", Icons.Default.Description) {
            navController.navigate("documentos")
        }
        MenuItem("Imágenes", Icons.Default.Image) {
            navController.navigate("imagenes")
        }
        MenuItem("Configuración", Icons.Default.Settings) {
            navController.navigate("configuracion")
        }
        MenuItem("Inicio/Apagado", Icons.Default.PowerSettingsNew) {
            navController.navigate("salir")
        }
    }
}

@Composable
fun MenuItem(text: String, icon: ImageVector, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(30.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, fontSize = 20.sp, fontWeight = FontWeight.Medium)
    }
}

@Composable
fun PresentacionScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.mi_foto),
            contentDescription = null,
            modifier = Modifier.size(150.dp).clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text("UNIVERSIDAD MAYOR DE SAN ANDRES")
        Text("Materia: Programación de disposistivos II")
        Text("Nombre: Evians Zabala")
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { navController.navigate("menu") },
            colors = ButtonDefaults.buttonColors(containerColor = androidx.compose.ui.graphics.Color(0xFFFF7F50))
        ) {
            Text("Regresar al Menú")
        }
    }
}

@Composable
fun DocumentosScreen(navController: NavController) {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Ver documentos en Google Drive:")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            uriHandler.openUri("https://drive.google.com/drive/folders/1WOuP6ChSL821T9nG4VBbF42ZEU9Z7ie4?usp=drive_link")
        }) {
            Text("Abrir Drive")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { navController.navigate("menu") },
            colors = ButtonDefaults.buttonColors(containerColor = androidx.compose.ui.graphics.Color(0xFFFF7F50))
        ) {
            Text("Regresar al Menú")
        }
    }
}

@Composable
fun ImagenesScreen(navController: NavController) {
    val imagenes = listOf(
        R.drawable.practica1,
        R.drawable.practica2,
        R.drawable.practica3,
        R.drawable.practica1,
        R.drawable.practica2,
        R.drawable.practica3,
        R.drawable.practica1,
        R.drawable.practica2,


        )

    Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.weight(1f)) {
            items(imagenes) { img ->
                Image(
                    painter = painterResource(id = img),
                    contentDescription = null,
                    modifier = Modifier.padding(8.dp).size(150.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("menu") },
            colors = ButtonDefaults.buttonColors(containerColor = androidx.compose.ui.graphics.Color(0xFFFF7F50))) {
            Text("Regresar al Menú")
        }
    }
}

@Composable
fun ConfiguracionScreen(navController: NavController) {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Accede a la ayuda de Android Studio")
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = {
            uriHandler.openUri("https://developer.android.com/studio/intro")
        }) {
            Text("Ir a la ayuda")
        }
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { navController.navigate("menu") },
            colors = ButtonDefaults.buttonColors(containerColor = androidx.compose.ui.graphics.Color(0xFFFF7F50))
        ) {
            Text("Regresar al Menú")
        }
    }
}

@Composable
fun ExitScreen() {
    LaunchedEffect(Unit) {
        exitProcess(0)
    }
}
