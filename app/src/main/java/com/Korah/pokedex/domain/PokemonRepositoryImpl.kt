package com.Korah.pokedex.domain

import com.Korah.pokedex.data.dto.Result
import com.Korah.pokedex.data.models.PokemonDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class PokemonRepositoryImpl(private val pokemonService: PokemonService) : PokemonRepository {
    override suspend fun getPokemonList(): Flow<Result<PokemonDTO>> {
        return flow {
            val pokemonsFromApi = try {
                pokemonService.getPokemonList()
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Result.Error("Network Error"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Result.Error("Network Error"))
                return@flow
            }catch (e: Exception){
                e.printStackTrace()
                emit(Result.Error("Unknown Error"))
                return@flow
            }
            emit(Result.Success(pokemonsFromApi))
        }
    }

}