package com.Korah.pokedex.domain.implementations

import com.Korah.pokedex.data.dto.Result
import com.Korah.pokedex.data.models.Pokemon
import com.Korah.pokedex.data.models.PokemonDTO
import com.Korah.pokedex.domain.repositories.PokemonRepository
import com.Korah.pokedex.domain.services.PokemonService
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

    override suspend fun getPokemonById(id: String): Flow<Result<Pokemon>> {
        return flow{
            val pokemon = try {
                pokemonService.getPokemonById(id);

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
            emit(Result.Success(pokemon))
        }
    }

}