package com.example.myproyectandroid

import android.Manifest
import android.R
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.myproyectandroid.ui.theme.MyProyectAndroidTheme

class NotificationActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        createNotificationChannel(context = this);

        enableEdgeToEdge()
        setContent {
            MyProyectAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Greeting(
                            name = "Android",
                            modifier = Modifier.padding(innerPadding)
                        )
                        NotificationButton()
                    }
                }
            }
        }
    }

    private fun createNotificationChannel(context: Context) {
        // Solo es necesario para API 26+ (Android 8.0)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notificaciones de Mi App"
            val descriptionText = "Este canal se usa para alertas generales"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            // EL ID DEBE SER EL MISMO QUE USAS EN EL BUILDER ("CHANNEL_ID_EJEMPLO")
            val channel = NotificationChannel("CHANNEL_ID_EJEMPLO", name, importance).apply {
                description = descriptionText
            }

            // Registrar el canal con el sistema
            val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}

@Composable
fun NotificationButton() {
    val context = LocalContext.current

    // 1. Configuramos el "pedidor" de permisos
    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // El usuario dijo que sí, lanzamos la notificación
            showSimpleNotification(context)
        } else {
            // El usuario dijo que no, podrías mostrar un Toast informativo
        }
    }

    Button(onClick = {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // 2. Antes de lanzar, comprobamos si ya lo tenemos
            val hasPermission = ContextCompat.checkSelfPermission(
                context, Manifest.permission.POST_NOTIFICATIONS
            ) == PackageManager.PERMISSION_GRANTED
            showSimpleNotification(context)

            if (hasPermission) {
                 showSimpleNotificationOpenActivity(context)
                //showSimpleNotification(context)
            } else {
                // 3. Si no, disparamos el diálogo del sistema
                permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
            }
        } else {
            showSimpleNotificationOpenActivity(context)
            //showSimpleNotification(context)
        }
    }) {
        Text("Notificar con seguridad")
    }

}

fun showSimpleNotification(context: Context) {
    val builder = NotificationCompat.Builder(context, "CHANNEL_ID_EJEMPLO")
        .setSmallIcon(R.drawable.ic_dialog_info) // Icono obligatorio
        .setContentTitle("¡Hola!")
        .setContentText("Esta es una notificación desde Jetpack Compose")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setAutoCancel(true) // Se cierra al tocarla

    with(NotificationManagerCompat.from(context)) {
        // El ID 101 es único para esta notificación (puedes usarlo para actualizarla luego)
        try {
            with(NotificationManagerCompat.from(context)) {
                notify(101, builder.build())
            }
        } catch (e: SecurityException) {
            // Manejar el error: registrarlo o avisar al usuario
            Log.e("Notificación", "Error de seguridad: falta permiso", e)
        }
    }
}


fun showSimpleNotificationOpenActivity(context: Context) {
    // 1. EL DESTINO: Aquí es donde especificas MenuActivity
    val intent = Intent(context, MenuActivity::class.java).apply {
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }

    // 2. EL PENDING INTENT: El "permiso" para que el sistema abra la actividad
    val pendingIntent = PendingIntent.getActivity(
        context,
        0,
        intent,
        PendingIntent.FLAG_IMMUTABLE // Obligatorio en Android moderno
    )

    // 3. CONSTRUIR LA NOTIFICACIÓN
    val builder = NotificationCompat.Builder(context, "CHANNEL_ID_EJEMPLO")
        .setSmallIcon(R.drawable.ic_dialog_info)
        .setContentTitle("Acceso al Menú")
        .setContentText("Haz clic aquí para ir a MenuActivity")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setContentIntent(pendingIntent) // <--- Vincular el click con el destino
        .setAutoCancel(true) // Se borra al tocarla

    // 4. LANZAR (Con chequeo de permiso para evitar errores)
    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
        NotificationManagerCompat.from(context).notify(101, builder.build())
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultNotificationPreview() {
    MyProyectAndroidTheme {
        Column {
            Greeting("Android")
            YoutubeVideoPlayer(videoId = "dQw4w9WgXcQ")
        }
    }
}
