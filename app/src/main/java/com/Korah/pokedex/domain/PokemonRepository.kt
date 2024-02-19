package com.Korah.pokedex.domain

import com.Korah.pokedex.data.dto.Result
import com.Korah.pokedex.data.models.PokemonDTO
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonList(): Flow<Result<PokemonDTO>>
}