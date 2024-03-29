package com.Korah.pokedex.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

private val PokeColorScheme = darkColorScheme(
//    primary = pokeBackground,
//    secondary = pokeBackgroundAccent,
//    tertiary = pokeYellow,
//    background = pokeRed,
//    surface = pokeBlue,
//    onPrimary = pokeYellow,
//    onSecondary = pokeYellow,
//    onTertiary = pokeBackground,
//    onBackground = pokeYellow,
//    onSurface = pokeYellow,
    primary = primary,
    secondary = secondary,
    tertiary = tertiary,
    background = background,
    surface = accent,
    onPrimary = tertiary,
    onSecondary = tertiary,
    onTertiary = primary,
    onBackground = accent,
    onSurface = tertiary,
    primaryContainer = primary,
    secondaryContainer = secondary,
    tertiaryContainer = tertiary,
    onPrimaryContainer = tertiary,
    onSecondaryContainer = tertiary,
    onTertiaryContainer = primary,
    surfaceTint = accent,
    error = pokeRed,
    onError = pokeYellow,
    errorContainer = pokeRed,
    onErrorContainer = pokeYellow,
    inverseOnSurface = tertiary,
    inversePrimary = primary,
    inverseSurface = accent,
    onSurfaceVariant = tertiary,
    outline = pokeYellow,
    outlineVariant = pokeRed,
    scrim = pokeBackground,
    surfaceVariant = pokeBackgroundAccent,
)

@Composable
fun PokedexTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> PokeColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}