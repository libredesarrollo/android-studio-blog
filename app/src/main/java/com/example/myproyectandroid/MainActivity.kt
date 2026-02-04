package com.example.myproyectandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myproyectandroid.ui.theme.MyProyectAndroidTheme

import android.graphics.Bitmap
import android.graphics.Color
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.asImageBitmap
import androidx.core.graphics.set
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import androidx.core.graphics.createBitmap

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MyProyectAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        //MiDibujoPersonalizado()
                        //PokemonScreen()
                        PantallaQR("Hola mundo")
                    }
                }
            }
        }
    }


}
fun generarQR(datos: String, size: Int = 512): Bitmap? {
    return try {
        val writer = QRCodeWriter()
        // Crea la matriz de puntos (BitMatrix)
        val bitMatrix = writer.encode(datos, BarcodeFormat.QR_CODE, size, size)
        val width = bitMatrix.width
        val height = bitMatrix.height
//        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        val bitmap = createBitmap(width, height, Bitmap.Config.RGB_565)

        // Recorremos la matriz para pintar píxel por píxel
        for (x in 0 until width) {
            for (y in 0 until height) {
                // bitmap[x, y] = if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE
                bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
            }
        }
        bitmap
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}

@Composable
fun PantallaQR(textoParaQR: String) {
    // Generamos el bitmap y lo recordamos
    val qrBitmap = remember(textoParaQR) {
        generarQR(textoParaQR)
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (qrBitmap != null) {
            Image(
                bitmap = qrBitmap.asImageBitmap(),
                contentDescription = "Código QR de $textoParaQR",
                modifier = Modifier.size(250.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Escanea para ver el contenido", style = MaterialTheme.typography.bodyMedium)
        } else {
            Text("Error al generar el QR")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier.padding(8.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyProyectAndroidTheme {
        PokemonScreen()
    }
}
