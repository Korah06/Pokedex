package com.Korah.pokedex.domain

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    private val interceptor = HttpLoggingInterceptor().apply {
        this.level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

    val api: PokemonService = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl(PokemonService.BASE_URL)
        .client(client)
        .build()
        .create(PokemonService::class.java)
}