package com.cexup.ui.consumer.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


private val DarkColorPalette = darkColors(
    primary = Primary,
    secondary = Secondary,
    background = BackgroundDark,
    surface = SurfaceDark,
    onPrimary = OnPrimary,
    onSecondary = OnSecondary,
    onBackground = OnBackgroundDark,
    onSurface = OnSurfaceDark,
)

private val LightColorPalette = lightColors(
    primary = Primary,
    secondary = Secondary,
    background = Background,
    surface = SurfaceLight,
    onPrimary = BlueJade,
    onSecondary = TextInactive,
    onBackground = TextActive,
    onSurface = OnSurfaceLight,

)

private val ColorLightPalette = lightColors(
    primary = ColorPrimary,
    onPrimary = ColorOnPrimary,
    secondary = ColorSecondary,
    onSecondary = ColorOnSecondary,
    background = ColorBackground,
    onBackground = ColorOnBackground,
    primaryVariant = ColorPrimaryVariant,
    secondaryVariant = ColorSecondaryVariant,
    error = ColorError,
    onError = ColorOnError ,
    surface = ColorBackground,
    onSurface = ColorOnBackground
)


@Composable
fun ConsumerTheme(
    content: @Composable() () -> Unit
) {
    MaterialTheme(
        colors = ColorLightPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}

