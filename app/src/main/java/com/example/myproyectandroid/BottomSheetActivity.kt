package com.example.myproyectandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myproyectandroid.ui.theme.MyProyectAndroidTheme
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
class BottomSheetActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyProyectAndroidTheme {
                // El Scaffold es el "marco" de la pantalla
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    // USAMOS UNA COLUMN para que los elementos no se encimen
                    Column(
                        modifier = Modifier
                            .padding(innerPadding) // Aplicamos el padding del Scaffold
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp), // Espacio entre elementos
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Greeting(name = "Android")

                        // Aquí llamamos a tu función del BottomSheet
                        EjemploBottomSheet()

                        CardBonito()
                    }
                }
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EjemploBottomSheet() {
    // 1. Estado para controlar la visibilidad
    var showSheet by remember { mutableStateOf(false) }

    // 2. Estado interno del BottomSheet (para animaciones y gestos)
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { showSheet = true }) {
            Text("Abrir Modal")
        }
    }

    // 3. El componente Modal
    if (showSheet) {
        ModalBottomSheet(
            onDismissRequest = { showSheet = false },
            sheetState = sheetState,
            // Opcional: Personalizar el color o la forma
            containerColor = Color.White,
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {
            // Contenido del Bottom Sheet
            SheetContent(
                onClose = {
                    // Para cerrar con animación, usamos la corrutina
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showSheet = false
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun SheetContent(onClose: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("¡Soy un Modal Bottom Sheet!", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))
        Text("Puedo contener cualquier composable aquí dentro.")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onClose) {
            Text("Cerrar")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBS() {
    MyProyectAndroidTheme {
        PantallaPrincipal()
    }
}