package com.example.myproyectandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
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

class CanvasActivity : ComponentActivity() {

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

