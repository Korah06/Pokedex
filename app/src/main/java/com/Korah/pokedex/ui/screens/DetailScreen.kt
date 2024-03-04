package com.Korah.pokedex.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.Korah.pokedex.data.models.Pokemon
import com.Korah.pokedex.data.models.TypeArr
import com.Korah.pokedex.data.statics.Routes
import com.Korah.pokedex.ui.widgets.PokeballCard
import com.Korah.pokedex.ui.widgets.colorType
import java.util.Locale

@Composable
fun PokemonDetailScreen(pokemon: Pokemon, navController: NavHostController) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        PokeballCard(pokemon = pokemon, navController = navController, isDetail = true)
        Text(
            text = pokemon.name.toUpperCase(Locale.ROOT), modifier = Modifier
                .padding(16.dp), fontSize = 28.sp, fontWeight = FontWeight.Bold
        )
        TypesCards(pokemon.types)
        Card(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Row(modifier = Modifier) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Weight: ${pokemon.weight}")
                }
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Height: ${pokemon.height}")
                }
            }
        }

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Button(
                modifier = Modifier.padding(16.dp),
                onClick = { navController.navigate(Routes.Detail.route + "/${pokemon.id.toInt() - 1}") }) {
                Text(text = "Previous Pokemon")
            }

            Button(
                modifier = Modifier.padding(16.dp),
                onClick = { navController.navigate(Routes.Detail.route + "/${pokemon.id.toInt() + 1}") }) {
                Text(text = "Next Pokemon")
            }
        }
    }
}

@Composable
fun TypesCards(types: List<TypeArr>) {

    Row {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(16.dp)
                .height(50.dp)
                .width(100.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(colorType(types[0].type))


        ) {
            Text(
                text = types[0].type.name,
                style = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    shadow = Shadow(
                        color = Color.Black,
                        blurRadius = 30f
                    )
                ),
                modifier = Modifier
                    .padding(8.dp)
            )
        }

        if (types.size > 1) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .height(50.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(colorType(types[1].type))


            ) {
                Text(
                    text = types[1].type.name,
                    style = TextStyle(
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        shadow = Shadow(
                            color = Color.Black,
                            blurRadius = 30f
                        )
                    ),
                    modifier = Modifier
                        .padding(8.dp)
                )
            }

        }
    }

}