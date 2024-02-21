package com.Korah.pokedex.domain.repositories

import com.Korah.pokedex.data.dto.Result
import com.Korah.pokedex.data.models.Pokemon
import com.Korah.pokedex.data.models.PokemonDTO
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {
    suspend fun getPokemonList(): Flow<Result<PokemonDTO>>
    suspend fun getPokemonById(id: String): Flow<Result<Pokemon>>
}