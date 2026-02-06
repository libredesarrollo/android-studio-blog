package com.example.myproyectandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.myproyectandroid.ui.theme.MyProyectAndroidTheme



class AnimacionesActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MyProyectAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScrollableColumnExample(innerPadding)
                }
            }
        }
    }
}

@Composable
fun ScrollableColumnExample(innerPadding: PaddingValues) {
    // 1. Creamos el estado del scroll
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            // 2. Le aplicamos el modificador verticalScroll
            .verticalScroll(scrollState)
    ) {
        AnimatedVisibilityExample()
        VisibilityExpansionExample()
        ColorTransitionExample()
        ComplexStateAnimation()

    }
}

@Composable
fun AnimatedVisibilityExample() {
    var visible by remember { mutableStateOf(true) }
    val alpha: Float by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )

    Column {
        Button(onClick = { visible = !visible }) {
            Text("Toggle Visibility")
        }
        Box(
            modifier = Modifier
                .size(100.dp)
                .graphicsLayer(alpha = alpha)
                .background(Color.Blue)
        )
    }
}

@Composable
fun VisibilityExpansionExample() {
    var isExpanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(16.dp)) {
        Button(onClick = { isExpanded = !isExpanded }) {
            Text(if (isExpanded) "Ocultar Detalles" else "Mostrar Detalles")
        }
        // AnimatedVisibility maneja el fade y el slide por ti
        AnimatedVisibility(
            visible = isExpanded,
            enter = slideInVertically() + fadeIn(),
            exit = slideOutVertically() + fadeOut()
        ) {
            Text(
                "¡Sorpresa! Este texto aparece con un deslizamiento y desvanecimiento suaves.",
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun ColorTransitionExample() {
    var isSelected by remember { mutableStateOf(false) }

    // El color cambiará suavemente de Gris a Verde en 500ms
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) Color(0xFF4CAF50) else Color.LightGray,
        animationSpec = tween(durationMillis = 500)
    )

    Box(
        modifier = Modifier
            .size(150.dp)
            .background(backgroundColor, shape = RoundedCornerShape(16.dp))
            .clickable { isSelected = !isSelected },
        contentAlignment = Alignment.Center
    ) {
        Text(if (isSelected) "Activo" else "Inactivo", color = Color.White)
    }
}
@Composable
fun ComplexStateAnimation() {
    var currentState by remember { mutableStateOf(BoxState.Small) }
    val transition = updateTransition(targetState = currentState, label = "BoxTransition")

    // Definimos cómo cambian varias propiedades a la vez
    val size by transition.animateDp(label = "Size") { state ->
        if (state == BoxState.Small) 100.dp else 200.dp
    }

    val rotation by transition.animateFloat(label = "Rotation") { state ->
        if (state == BoxState.Small) 0f else 45f
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {
            currentState = if (currentState == BoxState.Small) BoxState.Large else BoxState.Small
        }) {
            Text("Cambiar Tamaño y Rotación")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .size(size)
                .graphicsLayer(rotationZ = rotation)
                .background(Color.Magenta, RoundedCornerShape(8.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultAnimationPreview() {
    MyProyectAndroidTheme {
        ComplexStateAnimation()
    }
}