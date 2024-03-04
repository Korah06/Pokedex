package com.Korah.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.Korah.pokedex.data.models.Pokemon
import com.Korah.pokedex.data.statics.Routes
import com.Korah.pokedex.ui.ViewModels.PokemonViewModel
import com.Korah.pokedex.ui.screens.GridScreen
import com.Korah.pokedex.ui.screens.PokemonDetailScreen
import com.Korah.pokedex.ui.theme.PokedexTheme
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokedexTheme(darkTheme = true, dynamicColor = false) {
                val viewModel = getViewModel<PokemonViewModel>()

                val pokemonsList: List<Pokemon> = viewModel.pokemons.collectAsState().value
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = Routes.Home.route) {
                        composable(Routes.Home.route) {
                            GridScreen(navController = navController)
                        }
                        composable(
                            Routes.Detail.route + "/{id}", arguments = listOf(
                                navArgument("id") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val pokemonId = backStackEntry.arguments?.getString("id")
                            val pokemon = pokemonsList.find { it.id == pokemonId }

                            PokemonDetailScreen(pokemon = pokemon!!, navController = navController)
                        }
                    }
                }
            }
        }
    }
}
