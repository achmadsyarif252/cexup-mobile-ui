package com.cexup.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

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
    primary = Primary,
    secondary = Secondary,
    background = Background,
    surface = SurfaceLight,
    onPrimary = BlueJade,
    onSecondary = TextInactiveOld,
    onBackground = TextActiveNew,
    onSurface = OnSurfaceLight,
    primaryVariant = Heading,
    secondaryVariant = inactive,
)

private val replacementColors = FoundationColors(
    color = ColorsCexup(
        primary = PrimaryColors(
            primaryMain = PrimaryMain,
            primarySurface = PrimarySurface,
            primaryBorder = PrimaryBorder,
            primaryHover = PrimaryHover,
            primaryPressed = PrimaryPressed,
            primaryFocused = PrimaryFocused,
        ),
        danger = DangerColors(
            dangerMain = DangerMain,
            dangerSurface = DangerSurface,
            dangerBorder = DangerBorder,
            dangerHover = DangerHover,
            dangerPressed = DangerPressed,
            dangerFocused = DangerFocused,
        ),
        warning = WarningColors(
            warningMain = WarningMain,
            warningSurface = WarningSurface,
            warningBorder = WarningBorder,
            warningHover = WarningHover,
            warningPressed = WarningPressed,
            warningFocused = WarningFocused,
        ),
        info = InfoColors(
            infoMain = InfoMain,
            infoSurface = InfoSurface,
            infoBorder = InfoBorder,
            infoHover = InfoHover,
            infoPressed = InfoPressed,
            infoFocused = InfoFocused,
        ),
        success = SuccessColors(
            successMain = SuccessMain,
            successSurface = SuccessSurface,
            successBorder = SuccessBorder,
            successHover = SuccessHover,
            successPressed = SuccessPressed,
            successFocused = SuccessFocused,
        ),
        text = TextColors(
            textMain = TextMain,
            textSecondary = TextSecondary,
            textInactive = TextInactive,
        ),
        borderline = BorderlineColors(
            borderline1 = Borderline1,
            borderline2 = Borderline2,
            borderline3 = Borderline3,
        )
    ),
    palette = PaleteCexup(
        primary = PrimaryPalettes(
            primary1 = Primary1,
            primary2 = Primary2,
            primary3 = Primary3,
            primary4 = Primary4,
            primary5 = Primary5,
            primary6 = Primary6,
            primary7 = Primary7,
            primary8 = Primary8,
            primary9 = Primary9,
            primary10 = Primary10,
        ),
        secondary = SecondaryPalettes(
            yellowSecondary1 = YellowSecondary1,
            yellowSecondary2 = YellowSecondary2,
            yellowSecondary3 = YellowSecondary3,
            yellowSecondary4 = YellowSecondary4,
            yellowSecondary5 = YellowSecondary5,
            yellowSecondary6 = YellowSecondary6,
            yellowSecondary7 = YellowSecondary7,
            yellowSecondary8 = YellowSecondary8,
            yellowSecondary9 = YellowSecondary9,
            yellowSecondary10 = YellowSecondary10,
        ),
        tertiary = TertiaryPalettes(
            redTertiary1 = RedTertiary1,
            redTertiary2 = RedTertiary2,
            redTertiary3 = RedTertiary3,
            redTertiary4 = RedTertiary4,
            redTertiary5 = RedTertiary5,
            redTertiary6 = RedTertiary6,
            redTertiary7 = RedTertiary7,
            redTertiary8 = RedTertiary8,
            redTertiary9 = RedTertiary9,
            redTertiary10 = RedTertiary10,
        ),
        neutral = NeutralPalettes(
            neutral1 = Neutral1,
            neutral2 = Neutral2,
            neutral3 = Neutral3,
            neutral4 = Neutral4,
            neutral5 = Neutral5,
            neutral6 = Neutral6,
            neutral7 = Neutral7,
            neutral8 = Neutral8,
            neutral9 = Neutral9,
            neutral10 = Neutral10,
            neutral11 = Neutral11,
            neutral12 = Neutral12,
            neutral13 = Neutral13,
        ),
        grey = GreyPalettes(
            grey1 = Grey1,
            grey2 = Grey2,
            grey3 = Grey3,
            grey4 = Grey4,
            grey5 = Grey5,
            grey6 = Grey6,
            grey7 = Grey7,
            grey8 = Grey8,
            grey9 = Grey9,
            grey10 = Grey10,
        ),
        red = RedPalettes(
            red1 = Red1,
            red2 = Red2,
            red3 = Red3,
            red4 = Red4,
            red5 = Red5,
            red6 = Red6,
            red7 = Red7,
            red8 = Red8,
            red9 = Red9,
            red10 = Red10,
        ),
        blue = BluePalettes(
            blue1 = Blue1,
            blue2 = Blue2,
            blue3 = Blue3,
            blue4 = Blue4,
            blue5 = Blue5,
            blue6 = Blue6,
            blue7 = Blue7,
            blue8 = Blue8,
            blue9 = Blue9,
            blue10 = Blue10,
        ),
        yellow = YellowPalettes(
            yellow1 = Yellow1,
            yellow2 = Yellow2,
            yellow3 = Yellow3,
            yellow4 = Yellow4,
            yellow5 = Yellow5,
            yellow6 = Yellow6,
            yellow7 = Yellow7,
            yellow8 = Yellow8,
            yellow9 = Yellow9,
            yellow10 = Yellow10,
        ),
        green = GreenPalettes(
            green1 = Green1,
            green2 = Green2,
            green3 = Green3,
            green4 = Green4,
            green5 = Green5,
            green6 = Green6,
            green7 = Green7,
            green8 = Green8,
            green9 = Green9,
            green10 = Green10,
        )
    )
)


@Composable
fun CexupTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit
) {
    val ctx = LocalConfiguration.current
//    val colors = if (darkTheme) {
//        DarkColorCorporate
//    } else {
//        LightColorCorporate
//    }
    val colors = LightColorCorporate
    val colorTheme = replacementColors
    val typographyTheme = replacementTypography(ctx)
    val elevationTheme = replacementElevation(ctx)
    CompositionLocalProvider(
        ExtendedColors provides colorTheme,
        ExtendedTypography provides typographyTheme,
        ExtendedElevation provides elevationTheme,
    ) {
        MaterialTheme(
            colors = colors,
            typography = TypographyCorp,
            shapes = Shapes,
            content = content
        )
    }
}

@Immutable
data class FoundationColors(
    val color: ColorsCexup,
    val palette: PaleteCexup,
)

/** Colors **/
@Immutable
data class ColorsCexup(
    val primary: PrimaryColors,
    val danger: DangerColors,
    val warning: WarningColors,
    val info: InfoColors,
    val success: SuccessColors,
    val text: TextColors,
    val borderline: BorderlineColors
)

@Immutable
data class PaleteCexup(
    val primary: PrimaryPalettes,
    val secondary: SecondaryPalettes,
    val tertiary: TertiaryPalettes,
    val neutral: NeutralPalettes,
    val grey: GreyPalettes,
    val red: RedPalettes,
    val blue: BluePalettes,
    val yellow: YellowPalettes,
    val green: GreenPalettes
)

@Immutable
data class PrimaryColors(
    val primaryMain: Color,
    val primarySurface: Color,
    val primaryBorder: Color,
    val primaryHover: Color,
    val primaryPressed: Color,
    val primaryFocused: Color,
)

@Immutable
data class DangerColors(
    val dangerMain: Color,
    val dangerSurface: Color,
    val dangerBorder: Color,
    val dangerHover: Color,
    val dangerPressed: Color,
    val dangerFocused: Color,
)

@Immutable
data class WarningColors(
    val warningMain: Color,
    val warningSurface: Color,
    val warningBorder: Color,
    val warningHover: Color,
    val warningPressed: Color,
    val warningFocused: Color,
)

@Immutable
data class InfoColors(
    val infoMain: Color,
    val infoSurface: Color,
    val infoBorder: Color,
    val infoHover: Color,
    val infoPressed: Color,
    val infoFocused: Color,
)

@Immutable
data class SuccessColors(
    val successMain: Color,
    val successSurface: Color,
    val successBorder: Color,
    val successHover: Color,
    val successPressed: Color,
    val successFocused: Color,
)

@Immutable
data class TextColors(
    val textMain: Color,
    val textSecondary: Color,
    val textInactive: Color,
)

@Immutable
data class BorderlineColors(
    val borderline1: Color,
    val borderline2: Color,
    val borderline3: Color,
)

@Immutable
data class NeutralPalettes(
    val neutral1: Color,
    val neutral2: Color,
    val neutral3: Color,
    val neutral4: Color,
    val neutral5: Color,
    val neutral6: Color,
    val neutral7: Color,
    val neutral8: Color,
    val neutral9: Color,
    val neutral10: Color,
    val neutral11: Color,
    val neutral12: Color,
    val neutral13: Color,
)

@Immutable
data class GreyPalettes(
    val grey1: Color,
    val grey2: Color,
    val grey3: Color,
    val grey4: Color,
    val grey5: Color,
    val grey6: Color,
    val grey7: Color,
    val grey8: Color,
    val grey9: Color,
    val grey10: Color,
)

@Immutable
data class RedPalettes(
    val red1: Color,
    val red2: Color,
    val red3: Color,
    val red4: Color,
    val red5: Color,
    val red6: Color,
    val red7: Color,
    val red8: Color,
    val red9: Color,
    val red10: Color,
)

@Immutable
data class BluePalettes(
    val blue1: Color,
    val blue2: Color,
    val blue3: Color,
    val blue4: Color,
    val blue5: Color,
    val blue6: Color,
    val blue7: Color,
    val blue8: Color,
    val blue9: Color,
    val blue10: Color,
)

@Immutable
data class YellowPalettes(
    val yellow1: Color,
    val yellow2: Color,
    val yellow3: Color,
    val yellow4: Color,
    val yellow5: Color,
    val yellow6: Color,
    val yellow7: Color,
    val yellow8: Color,
    val yellow9: Color,
    val yellow10: Color,
)

@Immutable
data class GreenPalettes(
    val green1: Color,
    val green2: Color,
    val green3: Color,
    val green4: Color,
    val green5: Color,
    val green6: Color,
    val green7: Color,
    val green8: Color,
    val green9: Color,
    val green10: Color,
)

@Immutable
data class PrimaryPalettes(
    val primary1: Color,
    val primary2: Color,
    val primary3: Color,
    val primary4: Color,
    val primary5: Color,
    val primary6: Color,
    val primary7: Color,
    val primary8: Color,
    val primary9: Color,
    val primary10: Color,
)

@Immutable
data class SecondaryPalettes(
    val yellowSecondary1: Color,
    val yellowSecondary2: Color,
    val yellowSecondary3: Color,
    val yellowSecondary4: Color,
    val yellowSecondary5: Color,
    val yellowSecondary6: Color,
    val yellowSecondary7: Color,
    val yellowSecondary8: Color,
    val yellowSecondary9: Color,
    val yellowSecondary10: Color,
)

@Immutable
data class TertiaryPalettes(
    val redTertiary1: Color,
    val redTertiary2: Color,
    val redTertiary3: Color,
    val redTertiary4: Color,
    val redTertiary5: Color,
    val redTertiary6: Color,
    val redTertiary7: Color,
    val redTertiary8: Color,
    val redTertiary9: Color,
    val redTertiary10: Color,
)

/** Typography **/
@Immutable
data class TypographyCexup(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val h5: TextStyle,
    val h6: TextStyle,
    val hh1: TextStyle,
    val hh2: TextStyle,
    val hh3: TextStyle,
    val hh4: TextStyle,
    val hh5: TextStyle,
    val hh6: TextStyle,
    val button: TextStyle,
    val textButton1: TextStyle,
    val textButton2: TextStyle,
    val textButton3: TextStyle,
)

/** Elevation **/
@Immutable
data class ElevationCexup(
    val none: Dp,
    val skim: Dp,
    val lifted: Dp,
    val raised: Dp,
    val floating: Dp,
)

val ExtendedColors = staticCompositionLocalOf {
    FoundationColors(
        color = ColorsCexup(
            primary = PrimaryColors(
                primaryMain = Color.Unspecified,
                primarySurface = Color.Unspecified,
                primaryBorder = Color.Unspecified,
                primaryHover = Color.Unspecified,
                primaryPressed = Color.Unspecified,
                primaryFocused = Color.Unspecified,
            ), danger = DangerColors(
                dangerMain = Color.Unspecified,
                dangerSurface = Color.Unspecified,
                dangerBorder = Color.Unspecified,
                dangerHover = Color.Unspecified,
                dangerPressed = Color.Unspecified,
                dangerFocused = Color.Unspecified,
            ), warning = WarningColors(
                warningMain = Color.Unspecified,
                warningSurface = Color.Unspecified,
                warningBorder = Color.Unspecified,
                warningHover = Color.Unspecified,
                warningPressed = Color.Unspecified,
                warningFocused = Color.Unspecified,
            ), info = InfoColors(
                infoMain = Color.Unspecified,
                infoSurface = Color.Unspecified,
                infoBorder = Color.Unspecified,
                infoHover = Color.Unspecified,
                infoPressed = Color.Unspecified,
                infoFocused = Color.Unspecified,
            ), success = SuccessColors(
                successMain = Color.Unspecified,
                successSurface = Color.Unspecified,
                successBorder = Color.Unspecified,
                successHover = Color.Unspecified,
                successPressed = Color.Unspecified,
                successFocused = Color.Unspecified,
            ), text = TextColors(
                textMain = Color.Unspecified,
                textSecondary = Color.Unspecified,
                textInactive = Color.Unspecified,
            ), borderline = BorderlineColors(
                borderline1 = Color.Unspecified,
                borderline2 = Color.Unspecified,
                borderline3 = Color.Unspecified,
            )
        ),
        palette = PaleteCexup(
            primary = PrimaryPalettes(
                primary1 = Color.Unspecified,
                primary2 = Color.Unspecified,
                primary3 = Color.Unspecified,
                primary4 = Color.Unspecified,
                primary5 = Color.Unspecified,
                primary6 = Color.Unspecified,
                primary7 = Color.Unspecified,
                primary8 = Color.Unspecified,
                primary9 = Color.Unspecified,
                primary10 = Color.Unspecified,
            ),
            secondary = SecondaryPalettes(
                yellowSecondary1 = Color.Unspecified,
                yellowSecondary2 = Color.Unspecified,
                yellowSecondary3 = Color.Unspecified,
                yellowSecondary4 = Color.Unspecified,
                yellowSecondary5 = Color.Unspecified,
                yellowSecondary6 = Color.Unspecified,
                yellowSecondary7 = Color.Unspecified,
                yellowSecondary8 = Color.Unspecified,
                yellowSecondary9 = Color.Unspecified,
                yellowSecondary10 = Color.Unspecified,
            ),
            tertiary = TertiaryPalettes(
                redTertiary1 = Color.Unspecified,
                redTertiary2 = Color.Unspecified,
                redTertiary3 = Color.Unspecified,
                redTertiary4 = Color.Unspecified,
                redTertiary5 = Color.Unspecified,
                redTertiary6 = Color.Unspecified,
                redTertiary7 = Color.Unspecified,
                redTertiary8 = Color.Unspecified,
                redTertiary9 = Color.Unspecified,
                redTertiary10 = Color.Unspecified,
            ),
            neutral = NeutralPalettes(
                neutral1 = Color.Unspecified,
                neutral2 = Color.Unspecified,
                neutral3 = Color.Unspecified,
                neutral4 = Color.Unspecified,
                neutral5 = Color.Unspecified,
                neutral6 = Color.Unspecified,
                neutral7 = Color.Unspecified,
                neutral8 = Color.Unspecified,
                neutral9 = Color.Unspecified,
                neutral10 = Color.Unspecified,
                neutral11 = Color.Unspecified,
                neutral12 = Color.Unspecified,
                neutral13 = Color.Unspecified,
            ),
            grey = GreyPalettes(
                grey1 = Color.Unspecified,
                grey2 = Color.Unspecified,
                grey3 = Color.Unspecified,
                grey4 = Color.Unspecified,
                grey5 = Color.Unspecified,
                grey6 = Color.Unspecified,
                grey7 = Color.Unspecified,
                grey8 = Color.Unspecified,
                grey9 = Color.Unspecified,
                grey10 = Color.Unspecified,
            ),
            red = RedPalettes(
                red1 = Color.Unspecified,
                red2 = Color.Unspecified,
                red3 = Color.Unspecified,
                red4 = Color.Unspecified,
                red5 = Color.Unspecified,
                red6 = Color.Unspecified,
                red7 = Color.Unspecified,
                red8 = Color.Unspecified,
                red9 = Color.Unspecified,
                red10 = Color.Unspecified,
            ),
            blue = BluePalettes(
                blue1 = Color.Unspecified,
                blue2 = Color.Unspecified,
                blue3 = Color.Unspecified,
                blue4 = Color.Unspecified,
                blue5 = Color.Unspecified,
                blue6 = Color.Unspecified,
                blue7 = Color.Unspecified,
                blue8 = Color.Unspecified,
                blue9 = Color.Unspecified,
                blue10 = Color.Unspecified,
            ),
            yellow = YellowPalettes(
                yellow1 = Color.Unspecified,
                yellow2 = Color.Unspecified,
                yellow3 = Color.Unspecified,
                yellow4 = Color.Unspecified,
                yellow5 = Color.Unspecified,
                yellow6 = Color.Unspecified,
                yellow7 = Color.Unspecified,
                yellow8 = Color.Unspecified,
                yellow9 = Color.Unspecified,
                yellow10 = Color.Unspecified,
            ),
            green = GreenPalettes(
                green1 = Color.Unspecified,
                green2 = Color.Unspecified,
                green3 = Color.Unspecified,
                green4 = Color.Unspecified,
                green5 = Color.Unspecified,
                green6 = Color.Unspecified,
                green7 = Color.Unspecified,
                green8 = Color.Unspecified,
                green9 = Color.Unspecified,
                green10 = Color.Unspecified,
            )
        )
    )
}

val ExtendedTypography = staticCompositionLocalOf {
    TypographyCexup(
        h1 = TextStyle.Default,
        h2 = TextStyle.Default,
        h3 = TextStyle.Default,
        h4 = TextStyle.Default,
        h5 = TextStyle.Default,
        h6 = TextStyle.Default,
        hh1 = TextStyle.Default,
        hh2 = TextStyle.Default,
        hh3 = TextStyle.Default,
        hh4 = TextStyle.Default,
        hh5 = TextStyle.Default,
        hh6 = TextStyle.Default,
        button = TextStyle.Default,
        textButton1 = TextStyle.Default,
        textButton2 = TextStyle.Default,
        textButton3 = TextStyle.Default,
    )
}

val ExtendedElevation = staticCompositionLocalOf {
    ElevationCexup(
        none = Dp.Unspecified,
        skim = Dp.Unspecified,
        lifted = Dp.Unspecified,
        raised = Dp.Unspecified,
        floating = Dp.Unspecified
    )
}


object MaterialThemeCexup {
    val colors: FoundationColors
        @Composable get() = ExtendedColors.current
    val typography: TypographyCexup
        @Composable get() = ExtendedTypography.current
    val elevation: ElevationCexup
        @Composable get() = ExtendedElevation.current
}