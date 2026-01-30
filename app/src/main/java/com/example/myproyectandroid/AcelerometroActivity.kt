package com.example.myproyectandroid

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.myproyectandroid.ui.theme.MyProyectAndroidTheme

class AcelerometroActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyProyectAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),

                ) { innerPadding ->
                    Column() {
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                        SensorDataScreen()
                    }
                }
            }
        }

    }
}
@Composable
fun SensorDataScreen() {
    val context = LocalContext.current
    // Estados para guardar los ejes X, Y, Z
    var x by remember { mutableFloatStateOf(0f) }
    var y by remember { mutableFloatStateOf(0f) }
    var z by remember { mutableFloatStateOf(0f) }

    // 1. Obtenemos el SensorManager y el acelerometro (faltaria verificar si NO es null, dispositivos baratos pueden NO traer acelerometro
//    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
//    val accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    // mas eficiente, para evitar que cada vez que se recarga la pagina se cree el sensor
    val sensorManager = remember {
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }
    val accelerometer = remember {
        sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    // 2. Gestionamos el ciclo de vida del sensor
    DisposableEffect(Unit) {
        val listener = object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
                    // Actualizamos los estados con los valores del sensor
                    x = event.values[0]
                    y = event.values[1]
                    z = event.values[2]
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {}
        }

        // Registramos el sensor al iniciar
        sensorManager.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_UI)

        // IMPORTANTE: Al salir, liberamos el sensor para ahorrar batería
        onDispose {
            sensorManager.unregisterListener(listener)
        }
    }

    // 3. UI para mostrar los datos
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Acelerómetro", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Eje X: ${"%.2f".format(x)}")
        Text(text = "Eje Y: ${"%.2f".format(y)}")
        Text(text = "Eje Z: ${"%.2f".format(z)}")

        // Un pequeño truco visual: una caja que se mueve según el sensor
        Box(
            Modifier
                .offset(x = (-x * 10).dp, y = (y * 10).dp)
                .size(50.dp)
                .background(Color.Red, CircleShape)
        )
    }
}
