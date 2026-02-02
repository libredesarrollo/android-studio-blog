package com.example.myproyectandroid

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import coil.compose.AsyncImage
import com.example.myproyectandroid.ui.theme.MyProyectAndroidTheme
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

// Representa la respuesta principal
data class PokemonResponse(
    val id: Int,
    val name: String,
    val sprites: PokemonSprites?
)

// Representa el objeto de imágenes dentro de la respuesta
data class PokemonSprites(
    val front_default: String? // URL de la imagen frontal
)

sealed class PokemonUiState {
    object Idle : PokemonUiState()
    object Loading : PokemonUiState()
    data class Success(val pokemon: PokemonResponse) : PokemonUiState()
    data class Error(val message: String) : PokemonUiState()
}


object RetrofitClient {
    private const val BASE_URL = "https://pokeapi.co/api/v2/"

    val instance: PokeApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()) // Convierte JSON a nuestras Data Classes
            .build()

        retrofit.create(PokeApiService::class.java)
    }
}

interface PokeApiService {
    @GET("pokemon/{name}")
    suspend fun getPokemonInfo(
        @Path("name") name: String
    ): PokemonResponse
}

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MyProyectAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        PokemonScreen()
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonScreen() {
    val scope = rememberCoroutineScope()

    // Estados de la interfaz
    var pokemonName by remember { mutableStateOf("") }
    var uiState by remember { mutableStateOf<PokemonUiState>(PokemonUiState.Idle) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Campo de búsqueda
        OutlinedTextField(
            value = pokemonName,
            onValueChange = { pokemonName = it },
            label = { Text("Nombre del Pokémon") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                // Ejecutamos la búsqueda
                uiState = PokemonUiState.Loading
                scope.launch {
                    try {
                        val response = RetrofitClient.instance.getPokemonInfo(pokemonName.lowercase())
                        uiState = PokemonUiState.Success(response)
                    } catch (e: Exception) {
                        Log.e("ERROR",e.toString())
                        uiState = PokemonUiState.Error("No se encontró el Pokémon")
                    }
                }
            },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Buscar")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // --- PINTAR EL RESULTADO SEGÚN EL ESTADO ---
        when (val state = uiState) {
            is PokemonUiState.Loading -> CircularProgressIndicator()
            is PokemonUiState.Success -> PokemonDetailCard(state.pokemon)
            is PokemonUiState.Error -> Text(state.message, color = Color.Red)
            is PokemonUiState.Idle -> Text("Escribe un nombre para empezar")
        }
    }
}

@Composable
fun PokemonDetailCard(pokemon: PokemonResponse) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Carga la imagen de la API automáticamente
            AsyncImage(
                model = pokemon.sprites?.front_default,
                contentDescription = "Imagen de ${pokemon.name}",
                modifier = Modifier.size(150.dp)
            )

            Text(
                text = "ID: #${pokemon.id}",
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = pokemon.name.uppercase(),
                style = MaterialTheme.typography.headlineMedium
            )
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
