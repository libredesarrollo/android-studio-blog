package com.example.myproyectandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myproyectandroid.ui.theme.MyProyectAndroidTheme


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyProyectAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),

                    topBar = {
                        TopAppBar(
                            title = { Text("Mi Aplicación") },
                            actions = {
                                // Simplemente llamas a tu función aquí
                                MyMenu(
                                    onEdit = { println("¡Abriendo editor!") },
                                    onDelete = { println("¡Borrando elemento!") }
                                )
                            }
                        )
                    }
                ) { innerPadding ->
                    Column() {
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                        TaskItem()
                    }
                }
            }
        }

    }
}

// Ejemplo de ViewPager en Compose


@Composable
fun MyMenu(
    onEdit: () -> Unit,    // Acción para editar
    onDelete: () -> Unit   // Acción para eliminar
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.wrapContentSize(Alignment.TopEnd)) {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Default.MoreVert, contentDescription = "Menú")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Editar") },
                leadingIcon = { Icon(Icons.Default.Edit, contentDescription = null) },
                onClick = {
                    expanded = false // Cerramos el menú
                    onEdit()        // Ejecutamos la función que nos pasaron
                }
            )
            DropdownMenuItem(
                text = { Text("Eliminar") },
                leadingIcon = { Icon(Icons.Default.Delete, contentDescription = null) },
                onClick = {
                    expanded = false
                    onDelete()
                }
            )
        }
    }
}

@Composable
fun TaskItem() {
    Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Test", modifier = Modifier.weight(1f))

            // El menú aparecerá pegado al icono dentro de la tarjeta
            MyMenu(
                onEdit = { println("¡Abriendo editor!") },
                onDelete = { println("¡Borrando elemento!") }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMenu() {
    MyProyectAndroidTheme {
        TaskItem()
    }
}