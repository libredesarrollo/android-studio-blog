package com.example.myproyectandroid


import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myproyectandroid.ui.theme.MyProyectAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyProyectAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun ExamplesButtons() {
//    Button(onClick = { /* Acción */ }) {
//        Text("Aceptar")
//    }
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // 1. Botón Principal (Contained)
        Button(onClick = { /* Acción */ }) {
            Text("Aceptar")
        }

        // 2. Botón con Tono (Menos intenso)
        FilledTonalButton(onClick = { /* Acción */ }) {
            Text("Guardar Borrador")
        }

        // 3. Botón con Borde (Outlined)
        OutlinedButton(onClick = { /* Acción */ }) {
            Text("Editar Perfil")
        }

        // 4. Botón de Texto
        TextButton(onClick = { /* Acción */ }) {
            Text("Cancelar")
        }

        // 5. Botón con Icono
        Button(onClick = { /* Acción */ }) {
            Icon(Icons.Default.Add, contentDescription = null)
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Cancelar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyProyectAndroidTheme {
       // Greeting("Andasasasroid")
        ExamplesButtons()
    }
}