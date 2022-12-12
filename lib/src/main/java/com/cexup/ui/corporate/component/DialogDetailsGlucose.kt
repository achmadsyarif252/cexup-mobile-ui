package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.GrayDivider
import com.cexup.ui.utils.mediaquery.from
import com.example.app_corporate.ui.component.cards.CardAddDataInsulin
import com.example.app_corporate.ui.component.cards.CardAddDataPill
import com.example.app_corporate.ui.component.cards.CardFoodAndDrink

data class DetailsGlucose(
    val insulin:Int ?= null,
    val pills:Int ?= null,
    val foodDrink:String,
)

@Composable
fun DialogDetailsGlucose(
    dataDetailsGlucose: DetailsGlucose,
    show:Boolean,
    onCancel: () -> Unit,
){
    val ctx = LocalContext.current
    if (show){
        Dialog(onDismissRequest = { onCancel() }) {
            Surface(
                modifier = Modifier
                .verticalScroll(rememberScrollState()),
                shape = RoundedCornerShape(20.dp.from(ctx)),
                color = Color.White
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp.from(ctx))
                        .width(531.dp.from(ctx)),
                    verticalArrangement = Arrangement.spacedBy(16.dp.from(ctx))
                ) {
                    Row {
                        Text(
                            text = stringResource(id = R.string.details),
                            style = MaterialTheme.typography.subtitle1.copy(
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp.from(ctx),
                                lineHeight = 28.sp.from(ctx),
                            )
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Image(
                            modifier = Modifier
                                .clickable {
                                    onCancel()
                                }
                                .padding(5.dp.from(ctx)),
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = ""
                        )
                    }
                    Divider(color = GrayDivider, thickness = 1.dp.from(ctx))
                    if (dataDetailsGlucose.insulin != 0 && dataDetailsGlucose.insulin != null){
                        CardAddDataInsulin(
                            valueText = dataDetailsGlucose.insulin.toString(),
                            enable = false,
                            onValueChange = {}
                        )
                    }else if (dataDetailsGlucose.pills != 0 && dataDetailsGlucose.pills != null){
                        CardAddDataPill(
                            valueText = dataDetailsGlucose.pills.toString(),
                            enable = false,
                            onValueChange = {}
                        )
                    }
                    if (dataDetailsGlucose.foodDrink != "" && !dataDetailsGlucose.foodDrink.isEmpty()) {
                        CardFoodAndDrink(
                            valueText = dataDetailsGlucose.foodDrink,
                            enable = false,
                            onValueChange = {}
                        )
                    }
                }
            }
        }
    }
}