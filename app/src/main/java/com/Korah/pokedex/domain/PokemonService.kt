package com.Korah.pokedex.domain

import com.Korah.pokedex.data.models.PokemonDTO
import retrofit2.http.GET

interface PokemonService {

    @GET("pokemon")
    suspend fun getPokemonList(): PokemonDTO

    companion object{
        const val BASE_URL = "https://pokeapi.co/api/v2/"
    }
}