package com.example.myproyectandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myproyectandroid.ui.theme.MyProyectAndroidTheme

class CardActivity : ComponentActivity() {
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
                    //FilledCardExample()
                    CardBonito()
                }
            }
        }
    }
}

@Composable
fun CardMinimalExample() {
    Card() {
        Text(text = "Hello, world!")
    }
}

@Composable
fun FilledCardExample() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .size(width = 240.dp, height = 100.dp)
    ) {
        Text(
            text = "Filled",
            modifier = Modifier
                .padding(16.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun CardPreview() {
    MyProyectAndroidTheme {
//        FilledCardExample()
        CardBonito()
    }
}

@Composable
fun CardBonito(
    count: String = "123",
    date: String = "20/01/2026",
    title: String = "Título de ejemplo",
    direction: String = "Av. Siempre Viva 123",
    mount: String = "$1,500.00"
) {
    // El Box principal actúa como el RelativeLayout contenedor
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // 1. LA CARD (Contenedor principal de datos)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp), // Espacio para el "Mount" que sobresale arriba
            shape = RoundedCornerShape(5.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF6200EE)) // Color de ejemplo
        ) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            ) {
                // Fila Superior (Count y Date) - Equivalente al RelativeLayout interno
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = count, color = Color.White, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodySmall)
                    Text(text = date, color = Color.White, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.bodySmall)
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Título
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium
                )

                // Dirección
                Text(
                    text = direction,
                    color = Color.White,
                    style = MaterialTheme.typography.bodySmall
                )

                // Espacio extra al final (el View vacío del XML)
                Spacer(modifier = Modifier.height(24.dp))
            }
        }

        // 2. EL MONTO (Texto centrado arriba que sobresale)
        Surface(
            modifier = Modifier.align(Alignment.TopCenter),
            color = Color(0xFF3700B3), // Color de fondo del monto
            shape = RoundedCornerShape(8.dp),
            shadowElevation = 4.dp
        ) {
            Text(
                text = mount,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                color = Color.White,
                style = MaterialTheme.typography.headlineMedium,
                fontFamily = FontFamily.SansSerif
            )
        }

        // 3. LOS BOTONES FLOTANTES (FABs en horizontal)
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter) // Los posicionamos abajo al centro
                .offset(y = 20.dp), // Los movemos hacia afuera de la card para el efecto visual
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            SmallFloatingActionButton(
                onClick = { /* Info */ },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Info, contentDescription = "Info")
            }

            SmallFloatingActionButton(
                onClick = { /* Edit */ },
                containerColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(Icons.Default.Edit, contentDescription = "Edit")
            }
        }
    }
}