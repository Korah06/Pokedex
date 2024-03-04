package com.Korah.pokedex.data.statics

sealed class Routes(val route: String) {
    data object Home : Routes("home")
    data object Detail : Routes("detail")
}