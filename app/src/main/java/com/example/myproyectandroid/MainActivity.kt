package com.example.myproyectandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.myproyectandroid.ui.theme.MyProyectAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyProyectAndroidTheme {

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