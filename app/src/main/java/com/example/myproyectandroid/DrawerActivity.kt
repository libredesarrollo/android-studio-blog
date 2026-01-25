package com.example.myproyectandroid

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Help
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myproyectandroid.ui.theme.MyProyectAndroidTheme
import kotlinx.coroutines.launch

class DrawerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyProyectAndroidTheme {
                // El Scaffold es el "marco" de la pantalla
                //Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    // USAMOS UNA COLUMN para que los elementos no se encimen
//                    Column(
//                        modifier = Modifier
//                            .padding(innerPadding) // Aplicamos el padding del Scaffold
//                            .fillMaxSize(),
//                        verticalArrangement = Arrangement.spacedBy(16.dp), // Espacio entre elementos
//                        horizontalAlignment = Alignment.CenterHorizontally
//                    ) {
                        //Greeting(name = "Android")
                EjemploDrawerCompleto()
                // Aquí llamamos a tu función del BottomSheet
                        /*DetailedDrawerExample { paddingValues ->
                            // Este es el "content" que pide tu función
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(paddingValues) // IMPORTANTE: Usar los paddings que vienen del Scaffold interno
                            ) {
                                Text("¡Hola! Estoy dentro del Drawer")
                                Button (onClick = { /* Acción */ }) {
                                    Text("Botón de prueba")
                                }
                            }
                        }*/

                      //  CardBonito()
//                    }
               // }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EjemploDrawerCompleto() {
    // 1. Estado para abrir/cerrar el Drawer
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    // Estado para saber qué ítem está seleccionado
    var selectedItem by remember { mutableIntStateOf(0) }
    val items = listOf("Inicio", "Perfil", "Ajustes")

    // 2. El contenedor principal del Drawer
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // Contenido visual del menú lateral
            ModalDrawerSheet {
                Spacer(Modifier.height(12.dp))
                Text("Mi App", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
                HorizontalDivider()

                items.forEachIndexed { index, label ->
                    NavigationDrawerItem(
                        label = { Text(label) },
                        selected = index == selectedItem,
                        onClick = {
                            selectedItem = index
                            // Cerramos el drawer después de clickear
                            scope.launch { drawerState.close() }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        }
    ) {
        // 3. Contenido de la pantalla principal
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Pantalla de ${items[selectedItem]}") },
                    navigationIcon = {
                        IconButton(onClick = {
                            // Abrimos el drawer al pulsar el icono de menú
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Abrir menú")
                        }
                    }
                )
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Contenido de ${items[selectedItem]}")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailedDrawerExample(
    content: @Composable (PaddingValues) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(12.dp))
                    Text("Drawer Title", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)
                    HorizontalDivider()

                    Text("Section 1", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
                    NavigationDrawerItem(
                        label = { Text("Item 1") },
                        selected = false,
                        onClick = { /* Handle click */ }
                    )
                    NavigationDrawerItem(
                        label = { Text("Item 2") },
                        selected = false,
                        onClick = { /* Handle click */ }
                    )

                    HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))

                    Text("Section 2", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleMedium)
                    NavigationDrawerItem(
                        label = { Text("Settings") },
                        selected = false,
                        icon = { Icon(Icons.Outlined.Settings, contentDescription = null) },
                        badge = { Text("20") }, // Placeholder
                        onClick = { /* Handle click */ }
                    )
                    NavigationDrawerItem(
                        label = { Text("Help and feedback") },
                        selected = false,
                        icon = { Icon(Icons.AutoMirrored.Outlined.Help, contentDescription = null) },
                        onClick = { /* Handle click */ },
                    )
                    Spacer(Modifier.height(12.dp))
                }
            }
        },
        drawerState = drawerState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Navigation Drawer Example") },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")
                        }
                    }
                )
            }
        ) { innerPadding ->
            content(innerPadding)
        }
    }
}