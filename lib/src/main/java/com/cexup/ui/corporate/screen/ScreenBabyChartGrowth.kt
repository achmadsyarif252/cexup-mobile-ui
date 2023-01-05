package com.cexup.ui.corporate.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.component.CardWeightToAge
import com.cexup.ui.corporate.theme.SecondaryCorporate
import com.cexup.ui.utils.mediaquery.from

data class ScreenBabyChartGrowthUIState(
    val data : DataBabyChartGrowth = DataBabyChartGrowth(),
    val loading : Boolean = true,
    val error : Boolean = false,
    val message : String = ""
)

data class DataBabyChartGrowth(
    val listDropdown: List<String> = listOf(),
    val listDataChart: List<Int> = listOf(),
)

@Composable
fun ScreenBabyChartGrowth(

) {
    val ctx = LocalContext.current
    Column(
        modifier = Modifier
            .padding(
                top = 32.dp.from(ctx),
                start = 32.dp.from(ctx),
                end = 32.dp.from(ctx),
            )
            .verticalScroll(
                rememberScrollState()
            ),
        verticalArrangement = Arrangement.spacedBy(32.dp.from(ctx)),
    ) {
        Row {
            Text(
                text = "Baby Chart Growth",
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.SemiBold,
                    lineHeight = 32.sp.from(ctx),
                    fontSize = 24.sp.from(ctx),
                    letterSpacing = -2.sp.from(ctx)
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {

                },
                modifier = Modifier
                    .width(89.dp.from(ctx))
                    .height(35.dp.from(ctx)),
                colors = ButtonDefaults.buttonColors(backgroundColor = SecondaryCorporate),
                shape = RoundedCornerShape(10.dp.from(ctx)),
                contentPadding = PaddingValues(horizontal = 11.dp.from(ctx))
            ) {
                Text(
                    text = "Back",
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight(600),
                        fontSize = 14.sp.from(ctx),
                        letterSpacing = 1.sp.from(ctx),
                        color = Color.White
                    )
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CardWeightToAge(
                index = 0, onLeftClick = {  },
                title = "Weight to Height Curve",
                xAxisUnit = "Height(cm)") {

            }
            CardWeightToAge(index = 1, onLeftClick = {  }) {  }
        }
        Spacer(modifier = Modifier.height(1.dp.from(ctx)))
    }
}