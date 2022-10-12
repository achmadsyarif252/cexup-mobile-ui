package com.cexup.ui.corporate.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorCorporate = darkColors(
    primary = PrimaryCorporate,
    secondary = SecondaryCorporate,
    background = BackgroundDark,
    surface = SurfaceDark,
    onPrimary = OnPrimary,
    onSecondary = OnSecondary,
    onBackground = OnBackgroundDark,
    onSurface = OnSurfaceDark,
    primaryVariant = Heading,
    secondaryVariant = inactive,
)

private val LightColorCorporate = lightColors(
    primary = PrimaryCorporate,
    secondary = SecondaryCorporate,
    onPrimary = OnPrimary,
    onSecondary = OnSecondary,
    onBackground = OnBackgroundLight,
    onSurface = OnSurfaceLight,
    primaryVariant = Heading,
    secondaryVariant = inactive,
)

@Composable
fun CexupTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorCorporate
    } else {
        LightColorCorporate
    }

    MaterialTheme(
        colors = colors,
        typography = TypographyCorp,
        shapes = Shapes,
        content = content
    )
}