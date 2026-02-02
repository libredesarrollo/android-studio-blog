package com.example.myproyectandroid

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myproyectandroid.ui.theme.MyProyectAndroidTheme


data class Stat(
    val nombre: String,
    val valor: Int,
    val color: Color
)

val misStats = listOf(
    Stat("HP", 45, Color.Green),
    Stat("Ataque", 49, Color.Red),
    Stat("Defensa", 49, Color.Blue),
    Stat("Velocidad", 65, Color.Yellow)
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MyProyectAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        //MiDibujoPersonalizado()
                        PokemonScreen()

                    }
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier.padding(8.dp)
    )
}

@Composable
fun MiDibujoPersonalizado() {
    Canvas(
        modifier = Modifier
            .size(300.dp)
            .padding(16.dp)
    ) {
        // 1. Dibujar el rostro (Círculo amarillo)
        drawCircle(
            color = Color.Yellow,
            radius = size.minDimension / 2,
            center = center // 'center' es una propiedad automática del Canvas
        )

        // 2. Dibujar el contorno del rostro
        drawCircle(
            color = Color.Black,
            radius = size.minDimension / 2,
            style = Stroke(width = 5f) // Solo el borde
        )

        // 3. Dibujar los ojos
        val eyeRadius = 20f
        drawCircle(
            color = Color.Black,
            radius = eyeRadius,
            center = Offset(center.x - 60f, center.y - 50f)
        )
        drawCircle(
            color = Color.Black,
            radius = eyeRadius,
            center = Offset(center.x + 60f, center.y - 50f)
        )

        // 4. Dibujar la sonrisa (un arco)
        drawArc(
            color = Color.Black,
            startAngle = 0f,    // Empieza en la derecha (3 en un reloj)
            sweepAngle = 180f,  // Gira 180 grados hacia abajo
            useCenter = false,  // Si es true, cierra el arco hacia el centro (como un Pacman)
            topLeft = Offset(center.x - 70f, center.y - 20f),
            size = Size(140f, 100f),
            style = Stroke(width = 10f, cap = StrokeCap.Round)
        )
    }
}




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyProyectAndroidTheme {
        PokemonScreen()
    }
}
