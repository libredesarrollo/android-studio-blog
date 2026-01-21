package com.example.myproyectandroid


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myproyectandroid.ui.theme.MyProyectAndroidTheme

class ListGridActivity : ComponentActivity() {
    val messages = (1..500).map { "Elemento $it" };
    override fun onCreate(savedInstanceState: Bundle?) {



        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyProyectAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MessageGrid(messages = messages, modifier = Modifier.padding(innerPadding))
//                    /*MessageInefficientList*/MessageList(
////                        messages = (1..50).map { "Elemento $it" },
//                        messages = messages,
//                        modifier = Modifier.padding(innerPadding)
//                    )
                }
            }
        }
    }
}

@Composable
fun MessageInefficientList(messages: List<String>, modifier: Modifier = Modifier) {
    /*Row*/Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {

        messages.forEach { message ->
            Text(
                text = message
            )
        }
    }
}

@Composable
fun MessageList(messages: List<String>, modifier: Modifier = Modifier) {
    /*LazyRow*/LazyColumn(modifier = modifier) {
        items(messages) { message ->
            Text(
                text = message
            )
        }
    }
}

@Composable
fun MessageGrid(messages: List<String>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        //modifier = Modifier.height(200.dp),
        columns = GridCells.Adaptive(minSize = 200.dp)
    ) {
        items(messages) { message ->
//            Text(
//                text = message
//            )
            CardBonito()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GridListPreview() {
    val messages = (1..50).map { "Elemento $it" };
    MyProyectAndroidTheme {
        MessageGrid(messages = messages,
            modifier = Modifier.padding(all = 16.dp))
//        /*MessageInefficientList*/MessageList(messages = messages,
//            modifier = Modifier.padding(all = 16.dp))
    }
}