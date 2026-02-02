package com.example.myproyectandroid

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.myproyectandroid.ui.theme.MyProyectAndroidTheme

class WebViewActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyProyectAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                        MyWebView(url = "https://www.desarrollolibre.net/")
                    }
                }
            }
        }
    }
}



@Composable
fun MyWebView(url: String) {
    AndroidView(
        modifier = Modifier.fillMaxSize(), // Ocupa toda la pantalla
        factory = { context ->
            WebView(context).apply {
                // Configuración necesaria
                settings.javaScriptEnabled = true // Permite que funcionen sitios modernos
                webViewClient =
                    WebViewClient() // Abre los enlaces dentro de la app, no en el navegador

                loadUrl(url)
            }
        },
        update = { webView ->
            // Si la URL cambia, la cargamos aquí
            webView.loadUrl(url)
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MyWebViewPreview() {
    MyProyectAndroidTheme {
        Column {
            Greeting("Android")
            MyWebView(url = "https://www.desarrollolibre.net/")
        }
    }
}
