package com.cexup.ui.corporate.theme

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp
import com.cexup.ui.utils.mediaquery.from

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

fun replacementElevation(ctx: Configuration):ElevationCexup{
    return ElevationCexup(
        none = 0.dp.from(ctx),
        skim = 4.dp.from(ctx),
        lifted = 8.dp.from(ctx),
        raised = 16.dp.from(ctx),
        floating = 24.dp.from(ctx),
    )
}