package com.example.myproyectandroid

import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myproyectandroid.ui.theme.MyProyectAndroidTheme



class MediaPlayerActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyProyectAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),

                ) { innerPadding ->
                    Column() {
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                        ReproductorSimple()
                    }
                }
            }
        }

    }
}

@Composable
fun ReproductorSimple() {
    val context = LocalContext.current

    // 1. Creamos y recordamos el MediaPlayer
    val mediaPlayer = remember {
        MediaPlayer.create(context, R.raw.music)
    }

    // 2. Controlamos la limpieza al salir de la pantalla
    DisposableEffect(Unit) {

        onDispose {
            mediaPlayer.stop()
            mediaPlayer.release() // Libera la memoria
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row {
            Button(onClick = { mediaPlayer.start() }) {
                Icon(Icons.Default.PlayArrow, contentDescription = null)
                Text("Play")
            }
            Spacer(Modifier.width(8.dp))
            Button(onClick = { if (mediaPlayer.isPlaying) mediaPlayer.pause() }) {
                Text("Pause")
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewReproductorSimple() {
    MyProyectAndroidTheme {
        MyLoadingScreen()
    }
}