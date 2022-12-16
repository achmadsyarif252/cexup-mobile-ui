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
import com.cexup.ui.corporate.screen.InsulinType
import com.cexup.ui.corporate.theme.GrayDivider
import com.cexup.ui.utils.mediaquery.from
import com.example.app_corporate.ui.component.cards.*

data class DetailsGlucose(
    var typeMedicine: Int = 0,
    var brandMedicine: String? = null,
    var valueMedicine: Int? = null,
    var valueDetailMedicine: Int? = null,
    var foodAndDrink: String,
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
                    when(dataDetailsGlucose.typeMedicine){
                        1 -> {
                            CardBrandMedicine(
                                valueText = dataDetailsGlucose.brandMedicine.toString(),
                                enable = false,
                                resourceStringTypeBrand = R.string.pill_name,
                                onValueChange = {}
                            )
                            CardAddDataPill(
                                valueText = dataDetailsGlucose.valueMedicine.toString(),
                                enable = false,
                                onValueChange = {}
                            )
                            CardDetailValueMedicine(
                                valueText = "${dataDetailsGlucose.valueDetailMedicine} Pills"
                            )
                        }
                        2 -> {
                            CardBrandMedicine(
                                valueText = dataDetailsGlucose.brandMedicine.toString(),
                                enable = false,
                                resourceStringTypeBrand = R.string.insulin_name,
                                onValueChange = {}
                            )
                            CardAddDataInsulin(
                                valueText = dataDetailsGlucose.valueMedicine.toString(),
                                enable = false,
                                onValueChange = {}
                            )
                            CardDetailValueMedicine(
                                valueText =
                                if (dataDetailsGlucose.valueDetailMedicine==InsulinType.ShortActing)
                                    stringResource(id = R.string.short_acting)
                                else
                                    stringResource(id = R.string.long_acting)
                            )
                        }
                        else ->{}
                    }
                    if (dataDetailsGlucose.foodAndDrink != "" && !dataDetailsGlucose.foodAndDrink.isEmpty()) {
                        CardNoteGlucose(
                            valueText = dataDetailsGlucose.foodAndDrink,
                            enable = false,
                            onValueChange = {},
                            titleText = stringResource(id = R.string.food_and_drink_note),
                            placeHolderText = stringResource(id = R.string.your_food_and_drink),
                        )
                    }
                }
            }
        }
    }
}