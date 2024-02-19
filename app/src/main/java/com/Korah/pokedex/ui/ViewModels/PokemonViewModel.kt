package com.Korah.pokedex.ui.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.Korah.pokedex.data.models.PokemonDTO
import com.Korah.pokedex.domain.PokemonRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class PokemonViewModel(
    private val pokemonRepository: PokemonRepository
): ViewModel(){
    private val _pokemons = MutableStateFlow<PokemonDTO>(PokemonDTO())
    val pokemons = _pokemons.asStateFlow()

    private val _showErrorToastChannel = Channel<Boolean>()
    val showErrorToastChannel = _showErrorToastChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            pokemonRepository.getPokemonList().collectLatest { result ->
                when(result){
                    is com.Korah.pokedex.data.dto.Result.Success -> {
                        result.data?.let {
                            _pokemons.value = it
                        }
                    }
                    is com.Korah.pokedex.data.dto.Result.Error -> {
                        _showErrorToastChannel.send(true)
                    }
                }
            }
        }
    }
}