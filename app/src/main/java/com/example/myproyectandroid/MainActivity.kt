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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
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
fun ExamplesFAB() {
//    Button(onClick = { /* Acción */ }) {
//        Text("Aceptar")
//    }
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // 1. FAB SMALL
        SmallFloatingActionButton(
            onClick = { },
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.secondary
        ) {
            Icon(Icons.Filled.Add, "Small floating action button.")
        }

        // 2. FAB Large
        LargeFloatingActionButton(
            onClick = { },
//            shape = CutCornerShape(1.dp)
//            shape = RectangleShape
            shape = RoundedCornerShape(15.dp)
//            shape = CircleShape,
        ) {
            Icon(Icons.Filled.Add, "Large floating action button")
        }

        // 3. FAB + TEXT
        ExtendedFloatingActionButton(
            onClick = { },
            icon = { Icon(Icons.Filled.Add, "Extended floating action button.") },
            text = { Text(text = "Extended FAB") },
        )

        // 4. FAB
        FloatingActionButton(
            onClick = {  },
        ) {
            Icon(Icons.Filled.Add, "Floating action button.")
        }

    }
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
//        ExamplesButtons()
        ExamplesFAB()
    }
}