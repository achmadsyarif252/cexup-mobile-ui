package com.cexup.ui.corporate.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.app_corporate.ui.component.cards.CardGlucoseMeal
import com.cexup.ui.R
import com.cexup.ui.theme.Heading
import com.cexup.ui.utils.mediaquery.from

@Composable
fun DialogEditMealTypeGlucose(
    modifier: Modifier = Modifier,
    mealState: Int,
    show:Boolean,
    onCancel: () -> Unit,
    onChoose: (valueMealState:Int) -> Unit,
){
    val ctx = LocalConfiguration.current
    var valueMealState by remember { mutableStateOf(0) }
    if(show){
        Dialog(onDismissRequest = { onCancel() }) {
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
                    CardGlucoseMeal(
                        stateMeal = mealState,
                        onSelectedMeal = { valueMealState = it }
                    )
                    Button(
                        onClick = { onChoose(valueMealState) },
                        modifier = modifier
                            .fillMaxWidth()
                            .height(48.dp.from(ctx)),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Heading,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(6.dp),
                    )
                    {
                        Text(
                            text = stringResource(id = R.string.choose),
                            style = MaterialTheme.typography.h1.copy(
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp.from(ctx),
                                lineHeight = 24.sp.from(ctx),
                            )
                        )
                    }
                }
            }
        }
    }
}