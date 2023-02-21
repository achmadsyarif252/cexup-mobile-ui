package com.cexup.ui.corporate.theme

import android.content.Context
import android.content.res.Configuration
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.utils.mediaquery.from

val fonts =
    FontFamily(
        Font(R.font.bold,FontWeight.Bold),
        Font(R.font.black,FontWeight.Black),
        Font(R.font.extrabold, FontWeight.ExtraBold),
        Font(R.font.extralight, FontWeight.ExtraLight),
        Font(R.font.light,FontWeight.Light),
        Font(R.font.medium,FontWeight.Medium),
        Font(R.font.regular,FontWeight.Normal),
        Font(R.font.semibold,FontWeight.SemiBold),
        Font(R.font.thin, FontWeight.Thin),
)

val fontsCorp =
    FontFamily(
        Font(R.font.poppins_bold,FontWeight.Bold),
        Font(R.font.poppins_black,FontWeight.Black),
        Font(R.font.poppins_extrabold, FontWeight.ExtraBold),
        Font(R.font.poppins_extralight, FontWeight.ExtraLight),
        Font(R.font.poppins_light,FontWeight.Light),
        Font(R.font.poppins_medium,FontWeight.Medium),
        Font(R.font.poppins_regular,FontWeight.Normal),
        Font(R.font.poppins_semibold,FontWeight.SemiBold),
        Font(R.font.poppins_thin, FontWeight.Thin),
    )

val Typography = Typography(
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.W500,
        fontSize = 30.sp,
    ),
    h2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.W500,
        fontSize = 24.sp,
    ),
    h3 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp,
    ),
    h4 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp,
    ),
    h5 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
    ),
    h6 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
    ),
    subtitle1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = fonts,
    fontWeight = FontWeight.W400,
    fontSize = 14.sp
)
)

val TypographyCorp = Typography(
    body1 = TextStyle(
        fontFamily = fontsCorp,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = fontsCorp,
        fontWeight = FontWeight.W500,
        fontSize = 30.sp,
    ),
    h2 = TextStyle(
        fontFamily = fontsCorp,
        fontWeight = FontWeight.W500,
        fontSize = 24.sp,
    ),
    h3 = TextStyle(
        fontFamily = fontsCorp,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp,
    ),
    h4 = TextStyle(
        fontFamily = fontsCorp,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp,
    ),
    h5 = TextStyle(
        fontFamily = fontsCorp,
        fontWeight = FontWeight.W400,
        fontSize = 16.sp,
    ),
    h6 = TextStyle(
        fontFamily = fontsCorp,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp,
    ),
    subtitle1 = TextStyle(
        fontFamily = fontsCorp,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = fontsCorp,
        fontWeight = FontWeight.W400,
        fontSize = 14.sp
    )
)

fun replacementTypography(ctx: Configuration):TypographyCexup{
    return TypographyCexup(
        h1 = TextStyle(
            fontFamily = fontsCorp,
            fontSize = 92.sp.from(ctx),
            lineHeight = 124.sp.from(ctx),
            fontWeight = FontWeight.Normal
        ),
        h2 = TextStyle(
            fontFamily = fontsCorp,
            fontSize = 60.sp.from(ctx),
            lineHeight = 84.sp.from(ctx),
            fontWeight = FontWeight.Normal
        ),
        h3 = TextStyle(
            fontFamily = fontsCorp,
            fontSize = 48.sp.from(ctx),
            lineHeight = 66.sp.from(ctx),
            fontWeight = FontWeight.Normal
        ),
        h4 = TextStyle(
            fontFamily = fontsCorp,
            fontSize = 32.sp.from(ctx),
            lineHeight = 44.sp.from(ctx),
            fontWeight = FontWeight.Normal
        ),
        h5 = TextStyle(
            fontFamily = fontsCorp,
            fontSize = 28.sp.from(ctx),
            lineHeight = 40.sp.from(ctx),
            fontWeight = FontWeight.Normal
        ),
        h6 = TextStyle(
            fontFamily = fontsCorp,
            fontSize = 24.sp.from(ctx),
            lineHeight = 34.sp.from(ctx),
            fontWeight = FontWeight.Normal
        ),
        hh1 = TextStyle(
            fontFamily = fontsCorp,
            fontSize = 20.sp.from(ctx),
            lineHeight = 28.sp.from(ctx),
            fontWeight = FontWeight.Normal
        ),
        hh2 = TextStyle(
            fontFamily = fontsCorp,
            fontSize = 18.sp.from(ctx),
            lineHeight = 26.sp.from(ctx),
            fontWeight = FontWeight.Normal
        ),
        hh3 = TextStyle(
            fontFamily = fontsCorp,
            fontSize = 16.sp.from(ctx),
            lineHeight = 24.sp.from(ctx),
            fontWeight = FontWeight.Normal
        ),
        hh4 = TextStyle(
            fontFamily = fontsCorp,
            fontSize = 14.sp.from(ctx),
            lineHeight = 22.sp.from(ctx),
            fontWeight = FontWeight.Normal
        ),
        hh5 = TextStyle(
            fontFamily = fontsCorp,
            fontSize = 12.sp.from(ctx),
            lineHeight = 18.sp.from(ctx),
            fontWeight = FontWeight.Normal
        ),
        hh6 = TextStyle(
            fontFamily = fontsCorp,
            fontSize = 10.sp.from(ctx),
            lineHeight = 14.sp.from(ctx),
            fontWeight = FontWeight.Normal
        ),
        button = TextStyle(
            fontFamily = fontsCorp,
            fontSize = 14.sp.from(ctx),
            fontWeight = FontWeight.SemiBold
        ),
        textButton1 = TextStyle(
            fontFamily = fontsCorp,
            fontSize = 14.sp.from(ctx),
            fontWeight = FontWeight.SemiBold
        ),
        textButton2 = TextStyle(
            fontFamily = fontsCorp,
            fontSize = 12.sp.from(ctx),
            fontWeight = FontWeight.SemiBold
        ),
        textButton3 = TextStyle(
            fontFamily = fontsCorp,
            fontSize = 10.sp.from(ctx),
            fontWeight = FontWeight.SemiBold
        ),
    )
}