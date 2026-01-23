package com.example.myproyectandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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


@Preview(showBackground = true)
@Composable
fun Preview() {
    MyProyectAndroidTheme {
        PantallaPrincipal()
    }
}