package com.cexup.ui.corporate.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.component.DialogFilterPatientList
import compose.icons.Octicons
import compose.icons.octicons.ChevronLeft24
import compose.icons.octicons.ChevronRight24
import com.cexup.ui.R
import com.cexup.ui.corporate.component.CardPatientList
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.mediaquery.from

@Composable
fun ScreenPatients(
    modifier: Modifier = Modifier,
    listPatient: List<PatientProfileUIState>,
    onGetPatient:(isNext: Boolean) -> Unit,
    onClickPatient: (userCode: String) -> Unit,
    next: Int,
) {
    val ctx = LocalContext.current
    var dialogFilter by remember { mutableStateOf(false) }
    val listUserCode = listPatient.map { patientData -> patientData.userCode }
    val listName = listPatient.map { patientData -> patientData.name }
    val listCurrentDisease = listPatient.map { patientData -> patientData.currentDisease }

    fun onNext() {
        if (listPatient.isEmpty()) {
            Toast.makeText(ctx, "Latest page!", Toast.LENGTH_LONG).show()
        } else {
            onGetPatient( true)
        }
    }

    fun onPrev() {
        if (next > 1) {
            onGetPatient( false)
        } else {
            Toast.makeText(ctx, "First page!", Toast.LENGTH_LONG).show()
        }
    }

    fun onDialog() {
        dialogFilter = true
    }

    DialogFilterPatientList(
        show = dialogFilter,
        onDismiss = {
            dialogFilter = false
        }
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp.from(ctx))
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Patients List",
                style = MaterialTheme.typography.body1.copy(
                    color = Heading,
                    fontSize = 22.sp.from(ctx),
                    fontWeight = FontWeight(700)
                ),
            )
            Button(
                onClick = { onDialog() },
                modifier = modifier
                    .width(100.dp.from(ctx))
                    .height(38.54.dp.from(ctx)),
                colors = ButtonDefaults.buttonColors(backgroundColor = Heading),
                shape = RoundedCornerShape(10.dp.from(ctx)),
                contentPadding = PaddingValues(horizontal = 11.dp.from(ctx))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painterResource(id = R.drawable.ic_filter),
                        contentDescription = "cancel",
                        modifier = modifier.size(12.dp.from(ctx)),
                    )
                    Text(
                        text = "Filter",
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight(600),
                            fontSize = 14.sp.from(ctx),
                            letterSpacing = 1.sp.from(ctx),
                            color = Color.White
                        ),
                        modifier = modifier.padding(5.dp.from(ctx))
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp.from(ctx)))
        CardPatientList(
            listUserCode = listUserCode,
            listName = listName,
            listCurrentDisease = listCurrentDisease,
            onClicked = { userCode ->
                onClickPatient(userCode)
            }
        )
        Spacer(modifier = Modifier.height(10.dp.from(ctx)))
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp.from(ctx)),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = "showing $next page",
                style = MaterialTheme.typography.body1.copy(
                    color = Heading,
                    fontSize = 16.sp.from(ctx),
                    fontWeight = FontWeight(400)
                ),
            )
            Row(
                modifier = modifier
                    .width(318.dp.from(ctx))
                    .height(35.dp.from(ctx))
                    .padding(horizontal = 9.dp.from(ctx))
                    .coloredShadow(
                        color = Color.Black.copy(0.25f),
                        offsetY = 4.dp.from(ctx),
                        borderRadius = 10.dp.from(ctx),
                        shadowRadius = 2.dp.from(ctx)
                    )
                    .background(MaterialTheme.colors.background, RoundedCornerShape(10.dp.from(ctx))),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.clickable {
                        onPrev()
                    }
                ) {
                    Icon(
                        Octicons.ChevronLeft24,
                        contentDescription = "left",
                        modifier = modifier.size(16.dp.from(ctx)),
                        tint = MaterialTheme.colors.secondary
                    )
                    Text(
                        text = "Previous",
                        style = MaterialTheme.typography.body1.copy(
                            color = MaterialTheme.colors.secondary,
                            fontSize = 16.sp.from(ctx),
                            fontWeight = FontWeight(400)
                        ),
                    )
                }
                Spacer(modifier = modifier.width(20.dp.from(ctx)))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.clickable {
                        onNext()
                    }
                ) {
                    Text(
                        text = "Next",
                        style = MaterialTheme.typography.body1.copy(
                            color = MaterialTheme.colors.secondary,
                            fontSize = 16.sp.from(ctx),
                            fontWeight = FontWeight(400)
                        ),
                    )
                    Icon(
                        Octicons.ChevronRight24,
                        contentDescription = "right",
                        modifier = modifier.size(16.dp.from(ctx)),
                        tint = MaterialTheme.colors.secondary
                    )
                }
            }
        }
    }
}