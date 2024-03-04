package com.Korah.pokedex.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.Korah.pokedex.data.models.Pokemon
import com.Korah.pokedex.ui.ViewModels.PokemonViewModel
import com.Korah.pokedex.ui.widgets.PokeballCard
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.getViewModel

@Composable
fun GridScreen(navController: NavHostController) {
    val viewModel = getViewModel<PokemonViewModel>()

    val pokemonsList: List<Pokemon> = viewModel.pokemons.collectAsState().value
    val context = LocalContext.current
    var currentPagination = 20

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
                fontSize = 15.sp
            )
        }
    } else {
        currentPagination += 20
        LazyVerticalGrid(columns = GridCells.Fixed(2), content = {
            items(pokemonsList.size) {
                PokeballCard(pokemon = pokemonsList[it], navController)
            }
        })
    }
}