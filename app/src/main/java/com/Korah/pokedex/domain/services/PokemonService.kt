package com.Korah.pokedex.domain.services

import com.Korah.pokedex.data.models.Pokemon
import com.Korah.pokedex.data.models.PokemonDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {
    @GET("pokemon")
    suspend fun getPokemonList(): PokemonDTO

    @GET("pokemon/{id}/")
    suspend fun getPokemonById(@Path("id") id: String): Pokemon
}