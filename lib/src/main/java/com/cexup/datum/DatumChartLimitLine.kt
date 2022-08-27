package com.cexup.datum

import com.github.mikephil.charting.components.LimitLine


val limitWidth = 0.7f
val limitLineTemperature = listOf(
    LimitLine(37.5f).apply {
        lineColor = android.graphics.Color.RED
        lineWidth = limitWidth
    },
    LimitLine(35.0f).apply {
        lineColor = android.graphics.Color.GREEN
        lineWidth = limitWidth
    }
)

val limitLineHeartRate = listOf(
    LimitLine(50f).apply {
        lineColor = android.graphics.Color.GREEN
        lineWidth = limitWidth
        textColor = android.graphics.Color.GREEN
    },
    LimitLine(100f).apply {
        lineColor = android.graphics.Color.RED
        lineWidth = limitWidth
        textColor = android.graphics.Color.RED
    }
)

val limitLineBodyMassIndex = listOf(
    LimitLine(18.5f).apply {
        lineColor = android.graphics.Color.GREEN
        lineWidth = limitWidth
        textColor = android.graphics.Color.GREEN
    },
    LimitLine(25f).apply {
        lineColor = android.graphics.Color.MAGENTA
        lineWidth = limitWidth
        textColor = android.graphics.Color.MAGENTA
    },
    LimitLine(30f).apply {
        lineColor = android.graphics.Color.RED
        lineWidth = limitWidth
        textColor = android.graphics.Color.RED
    },
    LimitLine(40f).apply {
        lineColor = android.graphics.Color.BLACK
        lineWidth = limitWidth
        textColor = android.graphics.Color.BLACK
    }
)

val limitLineBloodOxygen = listOf(
    LimitLine(75f).apply {
        lineColor = android.graphics.Color.RED
        lineWidth = limitWidth
        textColor = android.graphics.Color.RED
    },
    LimitLine(90f).apply {
        lineColor = android.graphics.Color.YELLOW
        lineWidth = limitWidth
        textColor = android.graphics.Color.YELLOW
    },
    LimitLine(95f).apply {
        lineColor = android.graphics.Color.GREEN
        lineWidth = limitWidth
        textColor = android.graphics.Color.GREEN
    },

)

val limitLineRespiration = listOf(
    LimitLine(12f).apply {
        lineColor = android.graphics.Color.GREEN
        lineWidth = limitWidth
        textColor = android.graphics.Color.GREEN
    },
    LimitLine(20f).apply {
        lineColor = android.graphics.Color.RED
        lineWidth = limitWidth
        textColor = android.graphics.Color.RED
    }
)

val limitLineSystole = listOf(
    LimitLine(120f).apply {
        lineColor = android.graphics.Color.GREEN
        lineWidth = limitWidth
        textColor = android.graphics.Color.GREEN
    },
    LimitLine(130f).apply {
        lineColor = android.graphics.Color.YELLOW
        lineWidth = limitWidth
        textColor = android.graphics.Color.YELLOW
    },
    LimitLine(140f).apply {
        lineColor = android.graphics.Color.MAGENTA
        lineWidth = limitWidth
        textColor = android.graphics.Color.MAGENTA
    },
    LimitLine(160f).apply {
        lineColor = android.graphics.Color.RED
        lineWidth = limitWidth
        textColor = android.graphics.Color.RED
    },
    LimitLine(180f).apply {
        lineColor = android.graphics.Color.BLUE
        lineWidth = limitWidth
        textColor = android.graphics.Color.BLACK
    }
)

val limitLineDiastole = listOf(
    LimitLine(80f).apply {
        lineColor = android.graphics.Color.GREEN
        lineWidth = limitWidth
        textColor = android.graphics.Color.GREEN
    },
    LimitLine(85f).apply {
        lineColor = android.graphics.Color.YELLOW
        lineWidth = limitWidth
        textColor = android.graphics.Color.YELLOW
    },
    LimitLine(90f).apply {
        lineColor = android.graphics.Color.MAGENTA
        lineWidth = limitWidth
        textColor = android.graphics.Color.MAGENTA
    },
    LimitLine(100f).apply {
        lineColor = android.graphics.Color.RED
        lineWidth = limitWidth
        textColor = android.graphics.Color.RED
    },
    LimitLine(110f).apply {
        lineColor = android.graphics.Color.BLACK
        lineWidth = limitWidth
        textColor = android.graphics.Color.BLACK
    }
)