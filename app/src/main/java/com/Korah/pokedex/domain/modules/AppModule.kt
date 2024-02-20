package com.Korah.pokedex.domain.modules

import com.Korah.pokedex.domain.PokemonRepository
import com.Korah.pokedex.domain.PokemonRepositoryImpl
import com.Korah.pokedex.domain.PokemonService
import com.Korah.pokedex.ui.ViewModels.PokemonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single {
        Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/").addConverterFactory(
            GsonConverterFactory.create()
        ).build().create(PokemonService::class.java)
    }

    single<PokemonRepository> {
        PokemonRepositoryImpl(get())
    }

    viewModel{
        PokemonViewModel(get())
    }
}