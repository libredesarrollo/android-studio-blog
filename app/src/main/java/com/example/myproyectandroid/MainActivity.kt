package com.example.myproyectandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Scaffold

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
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
                    PantallaPrincipal()
                    //FilledCardExample()
                    CardBonito()
                }
            }
        }
    }
}

@Composable
fun Test() {
    // 1. Las variables de ESTADO se colocan AQUÍ (dentro del Composable)

}

@Preview(showBackground = true)
@Composable
fun Preview() {
    MyProyectAndroidTheme {
        PantallaPrincipal()
    }
}