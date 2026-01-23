package com.example.myproyectandroid

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myproyectandroid.ui.theme.MyProyectAndroidTheme
import kotlinx.coroutines.launch

import com.example.myproyectandroid.CardActivity

class MySnackbarActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyProyectAndroidTheme {
                PantallaPrincipal()
            }
        }
    }
}

@Composable
fun PantallaPrincipal() {
    // 1. Las variables de ESTADO se colocan AQUÍ (dentro del Composable)

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // 2. El Scaffold organiza la estructura visual
    Scaffold(
        snackbarHost = {
            // El Host es el "escenario" donde aparecerá el Snackbar
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { paddingValues ->
        // 3. El contenido principal
        // Usamos paddingValues para que el contenido no quede debajo de la TopBar o Snackbar

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Button(onClick = {
                // 4. Lanzamos la corrutina (el "hilo" asíncrono)
                scope.launch {
                    val result = snackbarHostState.showSnackbar(
                        message = "Elemento eliminado",
                        actionLabel = "Deshacer",
                        duration = SnackbarDuration.Short
                    )

                    // Opcional: Manejar la acción del botón "Deshacer"
                   when (result) {
                        SnackbarResult.ActionPerformed -> {
                            /* Aquí pondrías la lógica para restaurar el item */
                            print("ActionPerformed")
                            Log.d("MI_APP", "ActionPerformed")
                        }
                        SnackbarResult.Dismissed -> { /* Se cerró solo */
                            print("Dismissed")
                            Log.e("MI_APP", "Dismissed")
                        }
                    }
                }
            }) {
                Text("Mostrar Snackbar")
            }

        }
        CardBonito()
    }
}

@Preview(showBackground = true)
@Composable
fun SnackbarPreview() {
    MyProyectAndroidTheme {
        PantallaPrincipal()
    }
}