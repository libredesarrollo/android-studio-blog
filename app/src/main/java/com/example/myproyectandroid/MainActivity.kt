package com.example.myproyectandroid


import android.app.AlertDialog
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
                    //EjemploDialogoCompose()
                    EjemploDialogoListCompose()
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
    fun EjemploDialogoCompose() {
        // 1. Definimos el estado (¿se muestra el diálogo?)
        var showDialog by remember { mutableStateOf(false) }

        // Botón que activa el diálogo
        Button(onClick = { showDialog = true }) {
            Text("Eliminar elemento")
        }

        // 2. Declaramos el Diálogo
        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    // Se ejecuta cuando el usuario toca fuera del diálogo o pulsa "Atrás"
                    showDialog = false
                },
                title = {
                    Text(text = "Confirmar eliminación")
                },
                text = {
                    Text(text = "¿Estás seguro de que deseas borrar este archivo permanentemente?")
                },
                confirmButton = {
                    TextButton(onClick = {
                        showDialog = false
                        // Aquí iría tu lógica de borrado
                    }) {
                        Text("Confirmar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancelar")
                    }
                }
            )
        }
    }

@Composable
fun EjemploDialogoListCompose() {
    // 1. Definimos el estado (¿se muestra el diálogo?)
    var showDialog by remember { mutableStateOf(false) }

    val items = listOf("Opción 1", "Opción 2", "Opción 3")

    // Botón que activa el diálogo
    Button(onClick = { showDialog = true }) {
        Text("Eliminar elemento")
    }

    // 2. Declaramos el Diálogo
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                // Se ejecuta cuando el usuario toca fuera del diálogo o pulsa "Atrás"
                showDialog = false
            },
            title = {
                Text(text = "Confirmar eliminación")
            },
            text = {
                LazyColumn {
                    items(items) { item ->
                        Text(
                            text = item,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    // Acción al seleccionar un item
                                    println("$item seleccionado")
                                }
                                .padding(vertical = 12.dp)
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    // Aquí iría tu lógica de borrado
                }) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}



@Preview(showBackground = true)
@Composable
fun Preview() {
    MyProyectAndroidTheme {
        EjemploDialogoCompose()
    }
}