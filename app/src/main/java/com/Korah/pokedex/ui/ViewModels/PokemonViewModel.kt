package com.Korah.pokedex.ui.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.Korah.pokedex.data.models.Pokemon
import com.Korah.pokedex.domain.repositories.PokemonRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
    private val _pokemons = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemons = _pokemons.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    private var pagination: Int = 1;


    init {
        viewModelScope.launch {
            getPokemonList()
        }
    }

    private fun getPokemonList(){
        viewModelScope.launch {
            val futurePagination = if(pagination!=1)pagination + 20 else 20;
            for (id in pagination..futurePagination){
                val idString = id.toString()
                pokemonRepository.getPokemonById(idString).collectLatest { result ->
                    when (result) {
                        is com.Korah.pokedex.data.dto.Result.Success -> {
                            result.data?.let {
                                println(it);
                                _pokemons.value = _pokemons.value + it
                            }
                        }

                        is com.Korah.pokedex.data.dto.Result.Error -> {
                            _showErrorToastChannel.send(true)
                        }
                    }
                }
            }
            pagination = futurePagination;
        }
    }

}