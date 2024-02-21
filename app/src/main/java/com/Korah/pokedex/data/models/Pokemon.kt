package com.Korah.pokedex.data.models

data class Pokemon(
    val id: String,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<TypeArr>,
    val sprites:Sprite,
)

data class TypeArr(val type: Type)
data class Type(val name:String)

data class Sprite(val front_default: String)