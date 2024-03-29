package com.Korah.pokedex.ui.widgets

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.Korah.pokedex.data.models.Pokemon
import com.Korah.pokedex.data.models.Type
import com.Korah.pokedex.data.statics.Routes
import com.Korah.pokedex.ui.theme.background

@Composable
fun PokeballCard(pokemon: Pokemon, navController: NavHostController, isDetail: Boolean = false) {

    val infiniteTransition = rememberInfiniteTransition(label = "pokeball")
    val rotationAnimation = infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(5000, easing = LinearEasing),
        ), label = "pokeball"
    )


    val colors = pokeballColors(pokemon)
    val upColor: Color
    val downColor: Color

    if (colors.size == 1) {
        upColor = colors[0]
        downColor = colors[0]
    } else {
        upColor = colors[0]
        downColor = colors[1]
    }

    val modifier = if (!isDetail) Modifier
        .padding(20.dp)
        .size(150.dp)
        .clickable {
            navController.navigate(Routes.Detail.route + "/" + pokemon.id)
        }
        .drawBehind {
            val radius = size.width / 2
            drawArc(
                color = upColor,
                startAngle = 180f,
                sweepAngle = 180f,
                useCenter = false,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2),
            )
            drawArc(
                color = downColor,
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = false,
                topLeft = Offset(center.x - radius, center.y - radius),
                size = Size(radius * 2, radius * 2),
            )
            drawCircle(
                color = background, center = center, radius = radius / 3
            )
            val thinnerHeight = radius / 8.0f
            val topLeftY =
                center.y - thinnerHeight / 2
            drawRect(
                color = background,
                topLeft = Offset(center.x - radius, topLeftY),
                size = Size(radius * 2, thinnerHeight)
            )
        }
    else Modifier
        .padding(20.dp)
        .size(250.dp)
        .drawBehind {
            val radius = size.width / 2

            rotate(rotationAnimation.value) {
                drawArc(
                    color = upColor,
                    startAngle = 180f,
                    sweepAngle = 180f,
                    useCenter = false,
                    topLeft = Offset(center.x - radius, center.y - radius),
                    size = Size(radius * 2, radius * 2),
                )
                drawArc(
                    color = downColor,
                    startAngle = 0f,
                    sweepAngle = 180f,
                    useCenter = false,
                    topLeft = Offset(center.x - radius, center.y - radius),
                    size = Size(radius * 2, radius * 2),
                )
                drawCircle(
                    color = background, center = center, radius = radius / 3
                )
                val thinnerHeight = radius / 8.0f
                val topLeftY =
                    center.y - thinnerHeight / 2
                drawRect(
                    color = background,
                    topLeft = Offset(center.x - radius, topLeftY),
                    size = Size(radius * 2, thinnerHeight)
                )
            }
        }

    AsyncImage(
        model = pokemon.sprites.front_default,
        contentDescription = pokemon.name,
        modifier = modifier,
    )

}


fun pokeballColors(pokemon: Pokemon): List<Color> {
    val colors = mutableListOf<Color>()

    if (pokemon.types.size == 1) {
        colors.add(colorType(pokemon.types[0].type))
    } else {
        colors.add(colorType(pokemon.types[0].type))
        colors.add(colorType(pokemon.types[1].type))
    }

    return colors
}

fun colorType(type: Type): Color {

    if (type.name == "normal") return Color(0xFFA8A77A)
    if (type.name == "fire") return Color(0xFFEE8130)
    if (type.name == "water") return Color(0xFF6390F0)
    if (type.name == "electric") return Color(0xFFF7D02C)
    if (type.name == "grass") return Color(0xFF7AC74C)
    if (type.name == "ice") return Color(0xFF96D9D6)
    if (type.name == "fighting") return Color(0xFFC22E28)
    if (type.name == "poison") return Color(0xFFA33EA1)
    if (type.name == "ground") return Color(0xFFE2BF65)
    if (type.name == "flying") return Color(0xFFA98FF3)
    if (type.name == "psychic") return Color(0xFFF95587)
    if (type.name == "bug") return Color(0xFFA6B91A)
    if (type.name == "rock") return Color(0xFFB6A136)
    if (type.name == "ghost") return Color(0xFF735797)
    if (type.name == "dragon") return Color(0xFF6F35FC)
    if (type.name == "dark") return Color(0xFF705746)
    if (type.name == "steel") return Color(0xFFB7B7CE)
    if (type.name == "fairy") return Color(0xFFD685AD)

    return Color(0xFF000000)
}