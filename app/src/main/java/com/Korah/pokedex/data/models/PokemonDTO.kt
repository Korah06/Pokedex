package com.Korah.pokedex.data.models

data class PokemonDTO(
    val count: Long? = null,
    val next: String? = null,
    val previous: Any? = null,
    val results: List<PokemonGetDTO>? = null
)
