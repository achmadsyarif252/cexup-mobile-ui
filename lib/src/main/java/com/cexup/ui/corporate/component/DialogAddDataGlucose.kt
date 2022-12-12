package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.GrayGlucose
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.utils.mediaquery.from
import com.example.app_corporate.ui.component.cards.*

@Composable
fun DialogAddDataGlucose(
    modifier: Modifier = Modifier,
    show: Boolean = false,
    onCancel: () -> Unit,
    onSave: (TypeAddData: String, Date: String, Hours: String, Value: String, ValueDetail: Int) -> Unit,
    isAddMedicine: Boolean = false,
    isAddFoodAndDrink: Boolean = false,
) {
    val ctx = LocalContext.current
    var textTypeAddData by remember { mutableStateOf("") }
    var textDateAddData by remember { mutableStateOf("") }
    var textHoursAddData by remember { mutableStateOf("") }
    var textValueAddData by remember { mutableStateOf("") }
    var textValueDetail by remember { mutableStateOf(0) }
    var listTypeAddData = listOf("")

    if (show) {
        if (isAddMedicine) {
            listTypeAddData = listOf(
                "Insulin",
                "Pill"
            )
            textTypeAddData = "Insulin"
        } else if (isAddFoodAndDrink) {
            listTypeAddData = listOf(
                "Food & Drink Note"
            )
            textTypeAddData = "Food & Drink Note"
        } else {
            listTypeAddData = listOf(
                "Glucose",
                "Hemoglobin",
            )
            textTypeAddData = "Glucose"
        }
        Dialog(onDismissRequest = {
            onCancel()
            textTypeAddData = "Glucose"
        }) {
            Surface(
                modifier = modifier
                    .verticalScroll(rememberScrollState()),
                shape = RoundedCornerShape(20.dp.from(ctx)),
                color = Color.White
            ) {
                Column(
                    modifier = modifier
                        .padding(16.dp.from(ctx))
                        .width(531.dp.from(ctx)),
                    verticalArrangement = Arrangement.spacedBy(24.dp.from(ctx))
                ) {
                    if (!isAddFoodAndDrink) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Add Data",
                                style = MaterialTheme.typography.h2.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp.from(ctx),
                                    lineHeight = 28.sp.from(ctx),
                                    letterSpacing = -0.25.sp.from(ctx),
                                ),
                                modifier = modifier
                                    .weight(1f),
                                textAlign = TextAlign.Center,
                                color = GrayGlucose
                            )
                            Image(
                                modifier = Modifier
                                    .clickable {
                                        onCancel()
                                        textTypeAddData = "Glucose"
                                    }
                                    .padding(5.dp.from(ctx)),
                                painter = painterResource(id = R.drawable.ic_close),
                                contentDescription = ""
                            )
                        }
                        DropDownCorporate(
                            valueTextFieldDefault = textTypeAddData,
                            typeDropDown = dropDownType.DEFFAULT,
                            dropDownWidth = 531.dp.from(ctx),
                            textFieldWidth = 531.dp.from(ctx),
                            onChange = { textTypeAddData = it },
                            listDropDown = listTypeAddData
                        )
                        if (textTypeAddData == "Glucose" || textTypeAddData == "Hemoglobin") {
                            DropDownCorporate(
                                typeDropDown = dropDownType.DATE,
                                dropDownWidth = 531.dp.from(ctx),
                                textFieldWidth = 531.dp.from(ctx),
                                onChange = { textDateAddData = it },
                            )
                            DropDownCorporate(
                                typeDropDown = dropDownType.HOURS,
                                dropDownWidth = 531.dp.from(ctx),
                                textFieldWidth = 531.dp.from(ctx),
                                onChange = { textHoursAddData = it },
                            )
                        }

                    }
                    when (textTypeAddData) {
                        "Glucose" -> {
                            CardAddDataGlucose(
                                onValueChange = {
                                    textValueAddData = it
                                }
                            )
                            CardGlucoseMeal(
                                onSelectedMeal = {
                                    textValueDetail = it
                                }
                            )
                        }
                        "Insulin" -> {
                            CardAddDataInsulin(
                                onValueChange = {
                                    textValueAddData = it
                                }
                            )
                            CardGlucoseActing(
                                onSelectedActing = {
                                    textValueDetail = it
                                }
                            )
                        }
                        "Pill" -> {
                            CardAddDataPill(
                                onValueChange = {
                                    textValueAddData = it
                                }
                            )
                            CardGlucosePill(
                                onValueChange = {
                                    textValueDetail = it
                                }
                            )
                        }
                        "Hemoglobin" -> {
                            CardGlucoseHemoglobin(
                                onValueChange = {
                                    textValueAddData = it
                                }
                            )
                        }
                        "Food & Drink Note" -> {
                            CardFoodAndDrink(onValueChange = {
                                textValueAddData = it
                            }
                            )
                        }
                    }
                    Button(
                        onClick = {
                            onSave(
                                textTypeAddData,
                                textDateAddData,
                                textHoursAddData,
                                textValueAddData,
                                textValueDetail
                            )
                            textTypeAddData = "Glucose"
                        },
                        enabled = when (textTypeAddData) {
                            "Glucose" -> {
                                !textTypeAddData.isEmpty() &&
                                !textDateAddData.isEmpty() &&
                                !textHoursAddData.isEmpty() &&
                                !textValueAddData.isEmpty() &&
                                textValueDetail > -1
                            }
                            "Insulin", "Pill" -> {

                                !textTypeAddData.isEmpty() &&
                                !textValueAddData.isEmpty() &&
                                textValueDetail > -1
                            }
                            "Food & Drink Note" -> {
                                !textTypeAddData.isEmpty() &&
                                !textValueAddData.isEmpty()
                            }
                            else -> {
                                !textTypeAddData.isEmpty() &&
                                !textDateAddData.isEmpty() &&
                                !textHoursAddData.isEmpty() &&
                                !textValueAddData.isEmpty()
                            }
                        },
                        modifier = modifier
                            .fillMaxWidth()
                            .height(48.dp.from(ctx)),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Heading,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(6.dp),
                    ) {
                        Text(
                            text = "Save",
                            style = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp.from(ctx),
                                lineHeight = 24.sp.from(ctx),
                            ),
                        )
                    }
                }
            }
        }
    }
}