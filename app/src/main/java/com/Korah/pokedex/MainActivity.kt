package com.Korah.pokedex

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.Korah.pokedex.data.models.Pokemon
import com.Korah.pokedex.ui.ViewModels.PokemonViewModel
import com.Korah.pokedex.ui.theme.PokedexTheme
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.getViewModel

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme(darkTheme = true, dynamicColor = false) {

                val viewModel = getViewModel<PokemonViewModel>()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val pokemonsList: List<Pokemon> = viewModel.pokemons.collectAsState().value
                    val context = LocalContext.current
                    var currentPagination = 20;

                    LaunchedEffect(key1 = viewModel.showErrorToastChannel) {
                        viewModel.showErrorToastChannel.collectLatest { show ->
                            if (show) {
                                Toast.makeText(
                                    context,
                                    "No se encontrarón Pokemons en la hierba alta...",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                    }

                    if (pokemonsList.size < currentPagination) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            CircularProgressIndicator(modifier = Modifier.size(100.dp))
                            Text(
                                text = "Buscando Pokemon en la Hierba Alta...",
                                modifier = Modifier.padding(16.dp),
                                fontSize = 20.sp
                            )
                        }
                    } else {
                        currentPagination += 20
                        LazyColumn {
                            items(pokemonsList.size) {
                                Text(text = pokemonsList[it].name)
                            }
                        }
                        Box(){
                            Button(
                                onClick = { /*TODO*/ },
                                modifier = Modifier
                                    .width(300.dp)
                                    .height(200.dp)
                            ) {
                                Text(text = "Cargar más Pokemons")
                            }
                        }

                    }

                }
            }
        }
    }
}
