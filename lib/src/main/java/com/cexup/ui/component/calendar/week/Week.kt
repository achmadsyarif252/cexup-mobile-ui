package com.cexup.ui.component.calendar.week

import androidx.compose.runtime.Immutable
import com.cexup.ui.component.calendar.day.Day


/**
 *
 * author Trian Damai
 * created_at 12/03/22 - 22.50
 * site https://trian.app
 */

@Immutable
internal data class Week(
    val isFirstWeekOfTheMonth:Boolean=false,
    val days:List<Day>
)