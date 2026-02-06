package com.example.myproyectandroid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import androidx.room.Dao
//import androidx.room.Database
//import androidx.room.Delete
//import androidx.room.Entity
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.PrimaryKey
//import androidx.room.Query
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import androidx.room.Update
import com.example.myproyectandroid.ui.theme.MyProyectAndroidTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

//@Entity(tableName = "tasks")
//data class Task(
//    @PrimaryKey(autoGenerate = true) val id: Int = 0,
//    val title: String,
//    val isDone: Boolean = false
//)
//
//@Dao
//interface TaskDao {
//    @Query("SELECT * FROM tasks")
//    fun getAllTasks(): Flow<List<Task>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertTask(task: Task)
//
//    @Update
//    suspend fun updateTask(task: Task)
//
//    @Delete
//    suspend fun deleteTask(task: Task)
//}
//
//@Database(entities = [Task::class], version = 1)
//abstract class AppDatabase : RoomDatabase() {
//    abstract fun taskDao(): TaskDao
//}
//
//class TaskViewModel(private val dao: TaskDao) : ViewModel() {
//    val tasks = dao.getAllTasks().stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
//
//    fun addTask(title: String) {
//        viewModelScope.launch { dao.insertTask(Task(title = title)) }
//    }
//
//    fun toggleTask(task: Task) {
//        viewModelScope.launch { dao.updateTask(task.copy(isDone = !task.isDone)) }
//    }
//
//    fun deleteTask(task: Task) {
//        viewModelScope.launch { dao.deleteTask(task) }
//    }
//}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val db = Room.databaseBuilder(
//            applicationContext,
//            AppDatabase::class.java, "tasks-db"
//        ).build()
//
//        val dao = db.taskDao()
//
//        val viewModel = TaskViewModel(dao)

        enableEdgeToEdge()
        setContent {
            MyProyectAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
//                        TaskScreen(viewModel)
                        MyMap()
//                        PantallaQR("Hola mundo")
                    }
                }
            }
        }
    }
}

//@Composable
//fun TaskScreen(viewModel: TaskViewModel) {
//    val tasks by viewModel.tasks.collectAsState()
//    var text by remember { mutableStateOf("") }
//
//    Column(modifier = Modifier.padding(16.dp)) {
//        Row {
//            OutlinedTextField(value = text, onValueChange = { text = it }, modifier = Modifier.weight(1f))
//            Button(onClick = { viewModel.addTask(text); text = "" }) { Text("Añadir") }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        LazyColumn {
//            items(tasks) { task ->
//                Row(verticalAlignment = Alignment.CenterVertically) {
//                    Checkbox(checked = task.isDone, onCheckedChange = { viewModel.toggleTask(task) })
//                    Text(task.title, modifier = Modifier.weight(1f))
//
//                    IconButton(onClick = { viewModel.deleteTask(task) }) {
//                        Icon(Icons.Default.Delete, contentDescription = "Borrar")
//                    }
//                }
//            }
//        }
//    }
//}

@Composable
fun MyMap() {
    // Definimos dónde queremos que mire la cámara (ej. Madrid)
    val madrid = LatLng(40.4167, -3.7037)
    val marker2 = LatLng(41.4167, -3.7037)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(madrid, 10f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        // Añadimos un marcador (un pin)
        Marker(
            state = MarkerState(position = madrid),
            title = "Mi oficina",
            snippet = "Aquí vivo"
        )
        Marker(
            state = MarkerState(position = marker2),
            title = "Mi carcelero",
            snippet = "Aqui estoy secuestrado, ayuda!"
        )
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
        // You can create a mock view model for preview
        // val mockDao = ...
        // val viewModel = TaskViewModel(mockDao)
        // TaskScreen(viewModel)
    }
}