package com.cexup.ui.corporate.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberImagePainter
import com.cexup.ui.R
import com.cexup.ui.component.common.TextFieldCexup
import com.cexup.ui.corporate.theme.*
import com.cexup.ui.utils.capitalizeWords
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.gridItems
import com.cexup.ui.utils.mediaquery.from
import com.cexup.ui.utils.noRippleClick
import com.github.mikephil.charting.data.Entry
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.skydoves.landscapist.CircularReveal
import com.skydoves.landscapist.coil.CoilImage


enum class AllergyLevel {
    High, Medium, Low
}

enum class TypeHealthDataPatient{
    BloodPressure,HeartRate,Spo2,Temperature,BMI
}

data class dataHealthPatient(
    val iconData: Int,
    val typeHealthDataPatient: TypeHealthDataPatient,
    val listData: List<Entry>,
    val listData2: List<Entry> = listOf(),
    val nameData: String,
    val satuanItem: String,
    val ValueNow: String,
)

@Composable
fun CardProfilePatient(
    modifier: Modifier = Modifier,
    patientName: String,
    patientMail: String,
    shape: RoundedCornerShape = CircleShape
) {
    val ctx = LocalContext.current
    Row(
        modifier = modifier
            .width(326.49.dp.from(ctx))
            .height(103.dp.from(ctx))
            .padding(6.dp.from(ctx)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = rememberImagePainter(data = "", builder = {
                crossfade(true)
                placeholder(R.drawable.dummy_profile_small)
            }), contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .clip(shape)
                .coloredShadow(MaterialTheme.colors.primary)
                .width(91.dp.from(ctx))
                .height(91.dp.from(ctx))
                .align(Alignment.CenterVertically)
        )
        Spacer(modifier = modifier.width(15.dp.from(ctx)))
        Column {
            Column {
                Text(
                    text = patientName.capitalizeWords(),
                    fontSize = 15.sp.from(ctx),
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight(700),
                    color = Heading
                )
            }
            Text(
                text = patientMail,
                fontSize = 12.sp.from(ctx),
                style = MaterialTheme.typography.body1,
                color = inactive
            )
        }
    }
}

@Composable
fun CardProfilePatientNew(
    patientFullName: String,
    patientGender: String,
    patientAge: String,
    patientWeight: String,
    patientHeight: String,
    patientGolDarah: String,
    patientStatus: Boolean,
    ewsStatus: String,
) {
    val ctx = LocalContext.current
    Card(
        modifier = Modifier
            .width(216.dp.from(ctx)),
        elevation = MaterialThemeCexup.elevation.skim,
        shape = RoundedCornerShape(8.dp.from(ctx))
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp.from(ctx)),
            modifier = Modifier.padding(16.dp.from(ctx))
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp.from(ctx)),
                verticalAlignment = Alignment.CenterVertically
            ) {
//                if (thumbPatient.isEmpty()) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(48.dp.from(ctx))
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    BlueDarkJade,
                                    BlueLightJade
                                )
                            )
                        ),
                ) {
                    CoilImage(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .size(21.33f.dp.from(ctx)),
                        imageModel = painterResource(id = R.drawable.ic_profile_dummy),
                        // Crop, Fit, Inside, FillHeight, FillWidth, None
                        contentScale = ContentScale.Crop,
                        // shows an image with a circular revealed animation.
                        circularReveal = CircularReveal(duration = 250),
                        // shows a placeholder ImageBitmap when loading.
                        placeHolder = painterResource(id = R.drawable.ic_profile_dummy),
                        // shows an error ImageBitmap when the request failed.
                        error = painterResource(id = R.drawable.ic_profile_dummy)
                    )
                }
//                }
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp.from(ctx))
                ) {
                    Text(
                        text = patientFullName,
                        style = MaterialThemeCexup.typography.hh2.copy(
                            color = MaterialThemeCexup.colors.color.text.textMain,
                            fontWeight = FontWeight.SemiBold
                        ),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 2
                    )
                    Box(
                        modifier = Modifier
                            .width(86.dp.from(ctx))
                            .height(26.dp.from(ctx))
                            .background(BluePatientProfileNew, RoundedCornerShape(4.dp.from(ctx))),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = ewsStatus,
                            style = MaterialThemeCexup.typography.hh4.copy(
                                color = MaterialThemeCexup.colors.palette.neutral.neutral1
                            )
                        )
                    }
                }
            }
            Divider(
                color = MaterialThemeCexup.colors.color.borderline.borderline1,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
            )
            Column(verticalArrangement = Arrangement.spacedBy(4.dp.from(ctx))) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.gender),
                        style = MaterialThemeCexup.typography.hh4.copy(
                            color = MaterialThemeCexup.colors.color.text.textSecondary
                        )
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.age),
                        style = MaterialThemeCexup.typography.hh4.copy(
                            color = MaterialThemeCexup.colors.color.text.textSecondary
                        )
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = patientGender,
                        style = MaterialThemeCexup.typography.hh4.copy(
                            fontWeight = FontWeight.Medium,
                            color = MaterialThemeCexup.colors.color.text.textMain
                        )
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = patientAge,
                        style = MaterialThemeCexup.typography.hh4.copy(
                            fontWeight = FontWeight.Medium,
                            color = MaterialThemeCexup.colors.color.text.textMain
                        )
                    )
                }
                Spacer(modifier = Modifier.height(8.dp.from(ctx)))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.corporate_measurement_weight),
                        style = MaterialThemeCexup.typography.hh4.copy(
                            color = MaterialThemeCexup.colors.color.text.textSecondary
                        )
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.corporate_measurement_height),
                        style = MaterialThemeCexup.typography.hh4.copy(
                            color = MaterialThemeCexup.colors.color.text.textSecondary
                        )
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = patientWeight,
                        style = MaterialThemeCexup.typography.hh4.copy(
                            fontWeight = FontWeight.Medium,
                            color = MaterialThemeCexup.colors.color.text.textMain
                        )
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = patientHeight,
                        style = MaterialThemeCexup.typography.hh4.copy(
                            fontWeight = FontWeight.Medium,
                            color = MaterialThemeCexup.colors.color.text.textMain
                        )
                    )
                }

                Spacer(modifier = Modifier.height(8.dp.from(ctx)))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.gol_darah),
                        style = MaterialThemeCexup.typography.hh4.copy(
                            color = MaterialThemeCexup.colors.color.text.textSecondary
                        )
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text = stringResource(id = R.string.status).capitalizeWords(),
                        style = MaterialThemeCexup.typography.hh4.copy(
                            color = MaterialThemeCexup.colors.color.text.textSecondary
                        )
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = patientGolDarah,
                        style = MaterialThemeCexup.typography.hh4.copy(
                            fontWeight = FontWeight.Medium,
                            color = MaterialThemeCexup.colors.color.text.textMain
                        )
                    )
                    Text(
                        modifier = Modifier.weight(1f),
                        text =
                        if (patientStatus)
                            stringResource(id = R.string.active)
                        else stringResource(id = R.string.not_active),
                        style = MaterialThemeCexup.typography.hh4.copy(
                            fontWeight = FontWeight.Medium,
                            color =
                            if (patientStatus)
                                MaterialThemeCexup.colors.color.success.successMain
                            else
                                MaterialThemeCexup.colors.color.danger.dangerMain
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun CardAllergies(
    listAllergies: List<Pair<String, AllergyLevel>> = listOf(),
    onAddAllergy: () -> Unit = {},
    onRemoveAllergy: (index: Int) -> Unit = {},
    onDoneEditAllergy: (newListAllergy: List<Pair<String,AllergyLevel>>) -> Unit = {},
) {
    val ctx = LocalContext.current
    var isEditAllergy by remember {
        mutableStateOf(false)
    }
    var expanded by remember {
        mutableStateOf(false)
    }
    var tempList by remember {
        mutableStateOf(listAllergies.toMutableList())
    }

    var selectedItem by remember { mutableStateOf(0) }
    val listAllergyLevel = listOf(AllergyLevel.High,AllergyLevel.Medium,AllergyLevel.Low)
    Card(
        modifier = Modifier
            .width(216.dp.from(ctx)),
        elevation = MaterialThemeCexup.elevation.skim,
        shape = RoundedCornerShape(8.dp.from(ctx))
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp.from(ctx)),
            modifier = Modifier.padding(16.dp.from(ctx))
        ) {
            Row (verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = R.string.allergies),
                    style = MaterialThemeCexup.typography.hh2.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialThemeCexup.colors.color.text.textMain
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                if (!isEditAllergy) {
                    Icon(
                        modifier = Modifier
                            .size(18.dp.from(ctx))
                            .noRippleClick {
                                isEditAllergy = true
                            },
                        painter = painterResource(id = R.drawable.ic_edit_corporate),
                        contentDescription = "Icon Edit Allergy",
                        tint = MaterialThemeCexup.colors.color.text.textInactive
                    )
                }
            }
            Divider(
                color = MaterialThemeCexup.colors.color.borderline.borderline1,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
            )
            tempList.forEachIndexed { index,it ->
                var textValue by remember {
                    mutableStateOf(it.first)
                }
                var allergyValue by remember{
                    mutableStateOf(it.second)
                }
                Row {
                    TextFieldCexup(
                        modifier = Modifier
                            .wrapContentHeight()
                            .weight(1f),
                        value = textValue,
                        onValueChange = { text->
                            textValue = text
                            tempList[index] = Pair(text,it.second)
                        },
                        textStyle = MaterialThemeCexup.typography.hh4,
                        shape = RoundedCornerShape(8.dp.from(ctx)),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            cursorColor = Color.Black,
                            disabledIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            disabledTextColor = MaterialThemeCexup.colors.color.text.textSecondary,
                            textColor = MaterialThemeCexup.colors.color.text.textMain
                        ),
                        enabled = isEditAllergy,
                        placeholder = {
                            Text(
                                text = "Patient Allergy...",
                                style = MaterialThemeCexup.typography.hh4,
                                color = MaterialThemeCexup.colors.color.text.textInactive
                            )
                        }
                    )
                    Spacer(modifier = Modifier.width(6.dp.from(ctx)))

                    if (isEditAllergy){
                        Box(
                            modifier = Modifier.weight(1f),
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth().clickable {
                                    selectedItem = index
                                    expanded = true
                                },
                                text = allergyValue.name,
                                style = MaterialThemeCexup.typography.hh4.copy(
                                    fontWeight = FontWeight.Medium,
                                    color =
                                    if (it.second == AllergyLevel.High)
                                        MaterialThemeCexup.colors.color.danger.dangerMain
                                    else if (it.second == AllergyLevel.Medium)
                                        MaterialThemeCexup.colors.color.info.infoMain
                                    else
                                        MaterialThemeCexup.colors.color.success.successMain
                                )
                            )
                            if (selectedItem == index){
                                DropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = {
                                        expanded = false
                                    }
                                ) {
                                    listAllergyLevel.forEachIndexed { itemIndex, itemValue ->
                                        DropdownMenuItem(
                                            onClick = {
                                                tempList[index] = Pair(it.first,itemValue)
                                                allergyValue = itemValue
                                                expanded = false
                                            },
                                        ) {
                                            Text(
                                                text = itemValue.name,
                                                style = MaterialThemeCexup.typography.hh5,
                                                color = MaterialThemeCexup.colors.color.text.textMain
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        Icon(
                            modifier = Modifier
                                .padding(top = 8.dp.from(ctx), end = 8.dp.from(ctx))
                                .size(12.dp.from(ctx))
                                .noRippleClick {
                                    tempList.removeAt(index)
                                    onRemoveAllergy(index)
                                },
                            painter = painterResource(id = R.drawable.ic_close),
                            contentDescription = "Icon Delete Allergy",
                            tint = MaterialThemeCexup.colors.palette.neutral.neutral7
                        )
                    }else{
                        Text(
                            modifier = Modifier.weight(1f),
                            text = allergyValue.name,
                            style = MaterialThemeCexup.typography.hh4.copy(
                                fontWeight = FontWeight.Medium,
                                color =
                                if (it.second == AllergyLevel.High)
                                    MaterialThemeCexup.colors.color.danger.dangerMain
                                else if (it.second == AllergyLevel.Medium)
                                    MaterialThemeCexup.colors.color.info.infoMain
                                else
                                    MaterialThemeCexup.colors.color.success.successMain
                            )
                        )
                        Box(modifier = Modifier.size(20.dp.from(ctx))) {

                        }
                    }
                }
            }
            if (isEditAllergy){
                Row {
                    TextButton(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            tempList.add(Pair("",AllergyLevel.Low))
                            onAddAllergy()
                        },
                        contentPadding = PaddingValues(vertical = 8.dp.from(ctx), horizontal = 0.dp)
                    ) {
                        Icon(
                            modifier = Modifier.offset(y = (-2).dp),
                            painter = painterResource(id = R.drawable.ic_plus),
                            contentDescription = "Icon Add Allergy",
                            tint = MaterialThemeCexup.colors.color.text.textInactive
                        )
                        Text(
                            text = stringResource(id = R.string.add_allergy),
                            style = MaterialThemeCexup.typography.textButton2.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialThemeCexup.colors.color.text.textInactive
                            )
                        )
                    }
                    Spacer(modifier = Modifier.width(24.dp.from(ctx)))
                    Button(
                        modifier = Modifier.weight(1f),
                        onClick = {
                            isEditAllergy = false
                            onDoneEditAllergy(tempList)
                        },
                        contentPadding = PaddingValues(vertical = 8.dp.from(ctx), horizontal = 0.dp),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialThemeCexup.colors.color.primary.primaryMain
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.done),
                            style = MaterialThemeCexup.typography.textButton1.copy(
                                color = MaterialThemeCexup.colors.palette.neutral.neutral1
                            )
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CardChartPatientInformation(
    pathIcon: Int,
    descriptionIcon: String,
    chartName: String,
    valueItem: String,
    satuanItem: String,
    listData: List<Entry>,
    listData2: List<Entry> = listOf(),
    listDateData: List<String> = listOf(),
    maxAxis: Float = 100f,
    minAxis: Float =0f,
    onIconArrowUpClicked: () -> Unit,
) {
    val ctx = LocalContext.current
    Card(
        elevation = MaterialThemeCexup.elevation.skim,
        shape = RoundedCornerShape(8.dp.from(ctx))
    ) {
        Column(
            modifier = Modifier.padding(16.dp.from(ctx))
        ) {
            Row(
                modifier = Modifier.padding(vertical = 2.dp.from(ctx)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp.from(ctx))
                        .clip(CircleShape)
                        .background(
                            MaterialThemeCexup.colors.color.primary.primarySurface,
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = pathIcon),
                        contentDescription = descriptionIcon,
                        tint = MaterialThemeCexup.colors.color.primary.primaryMain
                    )
                }
                Spacer(modifier = Modifier.width(12.dp.from(ctx)))
                Column(
                    verticalArrangement = Arrangement.spacedBy(4.dp.from(ctx))
                ) {
                    Text(
                        text = chartName,
                        style = MaterialThemeCexup.typography.hh4.copy(
                            fontWeight = FontWeight.Medium,
                            color = MaterialThemeCexup.colors.color.text.textMain
                        )
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp.from(ctx)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = valueItem,
                            style = MaterialThemeCexup.typography.hh2.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialThemeCexup.colors.color.text.textMain
                            )
                        )
                        Text(
                            text = satuanItem,
                            style = MaterialThemeCexup.typography.hh6.copy(
                                color = MaterialThemeCexup.colors.color.text.textSecondary
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { onIconArrowUpClicked() }) {
                    Icon(
                        modifier = Modifier.rotate(90f),
                        painter = painterResource(id = R.drawable.ic_arrow_up),
                        contentDescription = "Icon Arrow Up",
                        tint = GreenPatientProfileNew
                    )
                }
            }
            Column(modifier = Modifier
                .width(297.dp.from(ctx))
                .height(154.dp.from(ctx))) {
                ChartPatientProfile(
                    data = listData,
                    data2 = listData2,
                    description = "",
                    label1 = Pair(
                        chartName,
                        MaterialThemeCexup.colors.palette.tertiary.redTertiary6.toArgb()
                    ),
                    label2 = Pair(
                        chartName,
                        BlueDashboardNew.toArgb()
                    ),
                    FormatXLabel = listDateData,
                    maxAxis = maxAxis,
                    minAxis = minAxis,
                )
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CardInformationPatient(
    patientFirstName: String,
    patientLastName: String,
    patientBirthDate: String,
    patientAssignedDoctor: String,
    patientReligion: String,
    patientStatusMarried: String,
    patientDateIn: String,
    patientUserCode: String,
    patientAddress: String,
    patientPhoneNumber: String,
    patientEmail: String,
    patientNoteFromNurse: String,
    patientSystoleValue: List<Entry> = listOf(),
    patientDiastoleValue: List<Entry> = listOf(),
    patientHeartRateValue: List<Entry> = listOf(),
    patientSpo2Value: List<Entry> = listOf(),
    patientBMIValue: List<Entry> = listOf(),
    patientTemperatureValue: List<Entry> = listOf(),
    onToDetailChart: (typeData: String) -> Unit = {},
    onValueChangeNote: (valueNote: String) -> Unit = {}
) {
    val ctx = LocalContext.current
    val pagerState = rememberPagerState()
    var heightPage by remember {
        mutableStateOf(0.dp)
    }

    val tabs = listOf(
        TabContentRow(header = stringResource(id = R.string.details)) {
            if (pagerState.currentPage == 0) {
                ContentDetailsInformationPatient(
                    patientFirstName = patientFirstName,
                    patientLastName = patientLastName,
                    patientBirthDate = patientBirthDate,
                    patientAssignedDoctor = patientAssignedDoctor,
                    patientReligion = patientReligion,
                    patientStatusMarried = patientStatusMarried,
                    patientDateIn = patientDateIn,
                    patientUserCode = patientUserCode,
                    patientAddress = patientAddress,
                    patientPhoneNumber = patientPhoneNumber,
                    patientEmail = patientEmail,
                    patientNoteFromNurse = patientNoteFromNurse,
                    onValueChangeNote = onValueChangeNote,
                    heightBeside = {
                        heightPage = it + 16.dp.from(ctx)
                    }
                )
            } else {
                ContentLoadingTabPatientProfile(height = heightPage)
            }
        },
        TabContentRow(header = stringResource(id = R.string.orders)) {
            if (pagerState.currentPage == 1) {
                ContentOrdersInformationPatient()
            } else {
                ContentLoadingTabPatientProfile(height = heightPage)
            }
        },
        TabContentRow(header = stringResource(id = R.string.health)) {
            if (pagerState.currentPage == 2) {
                ContentHealthInformationPatient(
                    patientDiastoleValue = patientDiastoleValue,
                    patientSystoleValue = patientSystoleValue,
                    patientTemperatureValue = patientTemperatureValue,
                    patientSpo2Value = patientSpo2Value,
                    patientHeartRateValue = patientHeartRateValue,
                    patientBMIValue = patientBMIValue,
                    heightBeside = {
                        heightPage = it + 16.dp.from(ctx)
                    },
                    onToDetailChart = {
                        onToDetailChart(it)
                    }
                )
            } else {
                ContentLoadingTabPatientProfile(height = heightPage)
            }
        }
    )
    Card(
        elevation = MaterialThemeCexup.elevation.skim,
        shape = RoundedCornerShape(8.dp.from(ctx))
    ) {
        Column(modifier = Modifier.padding(top = 16.dp.from(ctx))) {
            TabView(
                modifier = Modifier
                    .padding(horizontal = 16.dp.from(ctx))
                    .width(285.dp.from(ctx)),
                tabContents = tabs,
                pagerState = pagerState,
                colorUnderline = MaterialThemeCexup.colors.color.primary.primaryMain,
            )
            Spacer(modifier = Modifier.height(16.dp.from(ctx)))
            TabContent(tabContents = tabs, pagerState = pagerState)
        }
    }
}

@Composable
fun ContentLoadingTabPatientProfile(
    height: Dp,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
    ) {

    }
}

@Composable
fun ContentDetailsInformationPatient(
    patientFirstName: String,
    patientLastName: String,
    patientBirthDate: String,
    patientAssignedDoctor: String,
    patientReligion: String,
    patientStatusMarried: String,
    patientDateIn: String,
    patientUserCode: String,
    patientAddress: String,
    patientPhoneNumber: String,
    patientEmail: String,
    patientNoteFromNurse: String,
    heightBeside: (height: Dp) -> Unit = {},
    onValueChangeNote: (valueNote: String) -> Unit = {}
) {
    var textFieldValue by remember {
        mutableStateOf(patientNoteFromNurse)
    }
    val ctx = LocalContext.current
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp.from(ctx))
            .padding(bottom = 16.dp.from(ctx))
            .onGloballyPositioned {
                heightBeside(it.size.height.dp)
            },
        verticalArrangement = Arrangement.spacedBy(12.dp.from(ctx))
    ) {
        Text(
            text = stringResource(id = R.string.personal_details),
            style = MaterialThemeCexup.typography.hh3.copy(
                fontWeight = FontWeight.SemiBold,
                color = MaterialThemeCexup.colors.color.text.textSecondary
            )
        )
        Column {
            Row {
                Text(
                    modifier = Modifier
                        .weight(0.197f),
                    text = stringResource(id = R.string.first_name),
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textSecondary
                )
                Text(
                    modifier = Modifier
                        .weight(0.207f),
                    text = stringResource(id = R.string.last_name),
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textSecondary
                )
                Text(
                    modifier = Modifier
                        .weight(0.234f),
                    text = stringResource(id = R.string.birth_date),
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textSecondary
                )
                Text(
                    modifier = Modifier
                        .weight(0.361f),
                    text = stringResource(id = R.string.assigned_doctor),
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textSecondary
                )
            }
            Row {
                Text(
                    modifier = Modifier
                        .weight(0.197f),
                    text = patientFirstName,
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textMain,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    modifier = Modifier
                        .weight(0.207f),
                    text = patientLastName,
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textMain,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    modifier = Modifier
                        .weight(0.234f),
                    text = patientBirthDate,
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textMain,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    modifier = Modifier
                        .weight(0.361f),
                    text = patientAssignedDoctor,
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textMain,
                    fontWeight = FontWeight.Medium
                )
            }

        }

        Column {
            Row {
                Text(
                    modifier = Modifier
                        .weight(0.197f),
                    text = stringResource(id = R.string.religion),
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textSecondary
                )
                Text(
                    modifier = Modifier
                        .weight(0.207f),
                    text = stringResource(id = R.string.status).capitalizeWords(),
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textSecondary
                )
                Text(
                    modifier = Modifier
                        .weight(0.234f),
                    text = stringResource(id = R.string.date_in),
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textSecondary
                )
                Text(
                    modifier = Modifier
                        .weight(0.361f),
                    text = stringResource(id = R.string.id_patient),
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textSecondary
                )
            }
            Row {
                Text(
                    modifier = Modifier
                        .weight(0.197f),
                    text = patientReligion,
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textMain,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    modifier = Modifier
                        .weight(0.207f),
                    text = patientStatusMarried,
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textMain,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    modifier = Modifier
                        .weight(0.234f),
                    text = patientDateIn,
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textMain,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    modifier = Modifier
                        .weight(0.361f),
                    text = patientUserCode,
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textMain,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Column {
            Row {
                Text(
                    modifier = Modifier
                        .weight(0.404f),
                    text = stringResource(id = R.string.address),
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textSecondary
                )
                Text(
                    modifier = Modifier
                        .weight(0.234f),
                    text = stringResource(id = R.string.phone_number),
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textSecondary
                )
                Text(
                    modifier = Modifier
                        .weight(0.361f),
                    text = stringResource(id = R.string.email),
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textSecondary
                )
            }
            Row {
                Text(
                    modifier = Modifier
                        .weight(0.404f),
                    text = patientAddress,
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textMain,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    modifier = Modifier
                        .weight(0.234f),
                    text = patientPhoneNumber,
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textMain,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    modifier = Modifier
                        .weight(0.361f),
                    text = patientEmail,
                    style = MaterialThemeCexup.typography.hh4,
                    color = MaterialThemeCexup.colors.color.text.textMain,
                    fontWeight = FontWeight.Medium
                )
            }
        }
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp.from(ctx))
        ) {
            Text(
                text = stringResource(id = R.string.note),
                style = MaterialThemeCexup.typography.hh4,
                color = MaterialThemeCexup.colors.color.text.textSecondary
            )
            TextFieldCexup(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        1.dp.from(ctx),
                        MaterialThemeCexup.colors.color.borderline.borderline3,
                        RoundedCornerShape(
                            4.dp.from(ctx)
                        )
                    ),
                value = textFieldValue,
                onValueChange = {
                    textFieldValue = it
                    onValueChangeNote(it)
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black,
                ),
                textStyle = MaterialThemeCexup.typography.hh4.copy(
                    Color.Black
                ),
                placeholder = {
                    Text(
                        text = "Your Note about this patient",
                        style = MaterialThemeCexup.typography.hh4.copy(
                            color = Color.Black
                        )
                    )
                },
                innerPaddingValue = PaddingValues(16.dp.from(ctx)),
                shape = RoundedCornerShape(4.dp.from(ctx)),
            )
        }
    }
}

@Composable
fun ContentOrdersInformationPatient() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Comming Soon",
            style = MaterialThemeCexup.typography.h2.copy(
                color = MaterialThemeCexup.colors.color.text.textMain
            )
        )
    }
}

@Composable
fun ContentHealthInformationPatient(
    patientSystoleValue: List<Entry> = listOf(),
    patientDiastoleValue: List<Entry> = listOf(),
    patientHeartRateValue: List<Entry> = listOf(),
    patientSpo2Value: List<Entry> = listOf(),
    patientBMIValue: List<Entry> = listOf(),
    patientTemperatureValue: List<Entry> = listOf(),
    heightBeside: (height: Dp) -> Unit = {},
    onToDetailChart: (typeData: String) -> Unit = {},
) {
    val ctx = LocalContext.current
    val dataList: List<dataHealthPatient> = listOf(
        dataHealthPatient(
            typeHealthDataPatient = TypeHealthDataPatient.BloodPressure,
            iconData = R.drawable.ic_blood_pressure,
            listData = patientSystoleValue,
            nameData = "Blood Pressure",
            satuanItem = "mg/dl",
            ValueNow = if (patientSystoleValue.isEmpty() || patientDiastoleValue.isEmpty()) {
                "0/0"
            } else {
                "${patientSystoleValue[0].y}/${patientDiastoleValue[0].y}"
            },
            listData2 = patientDiastoleValue
        ),
        dataHealthPatient(
            typeHealthDataPatient = TypeHealthDataPatient.HeartRate,
            iconData = R.drawable.ic_heart_beat,
            listData = patientHeartRateValue,
            nameData = "Heart Rate",
            satuanItem = "bpm",
            ValueNow = if (patientHeartRateValue.isEmpty()) {
                "0"
            } else {
                patientHeartRateValue[0].y.toString()
            },
        ),
        dataHealthPatient(
            typeHealthDataPatient = TypeHealthDataPatient.Spo2,
            iconData = R.drawable.ic_heart_beat,
            listData = patientSpo2Value,
            nameData = "Spo2",
            satuanItem = "mg/dl",
            ValueNow = if (patientSpo2Value.isEmpty()) {
                "0"
            } else {
                patientSpo2Value[0].y.toString()
            },
        ),
        dataHealthPatient(
            typeHealthDataPatient = TypeHealthDataPatient.BMI,
            iconData = R.drawable.ic_heart_beat,
            listData = patientBMIValue,
            nameData = "BMI",
            satuanItem = "mg/dl",
            ValueNow = if (patientBMIValue.isEmpty()) {
                "0"
            } else {
                patientBMIValue[0].y.toString()
            },
        ),
        dataHealthPatient(
            typeHealthDataPatient = TypeHealthDataPatient.Temperature,
            iconData = R.drawable.ic_heart_beat,
            listData = patientTemperatureValue,
            nameData = "Temperature",
            satuanItem = "mg/dl",
            ValueNow = if (patientTemperatureValue.isEmpty()) {
                "0"
            } else {
                patientTemperatureValue[0].y.toString()
            },
        ),
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp.from(ctx)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp.from(ctx))
            .padding(bottom = 16.dp.from(ctx))
            .then(
                Modifier.onGloballyPositioned {
                    heightBeside(it.size.height.dp)
                }
            )
    ) {
        this.gridItems(
            data = dataList,
            columnCount = 2,
            horizontalArrangement = Arrangement.spacedBy(16.dp.from(ctx))
        ) { dataHealthPatient ->
            val tempDataDate = mutableListOf<String>("")
            val tempData1 = mutableListOf<Entry>()
            val tempData2 = mutableListOf<Entry>()
            dataHealthPatient.listData.forEachIndexed { index, it->
                tempDataDate.add(it.x.toInt().toString())
                tempData1.add(Entry(index+1f,it.y))
            }
            dataHealthPatient.listData2.forEachIndexed { index, it ->
                tempData2.add(Entry(index+1f,it.y))
            }
            Log.e("wowo2", tempDataDate.toString())
            CardChartPatientInformation(
                pathIcon = dataHealthPatient.iconData,
                descriptionIcon = "Icon ${dataHealthPatient.nameData}",
                chartName = dataHealthPatient.nameData,
                valueItem = dataHealthPatient.ValueNow,
                satuanItem = dataHealthPatient.satuanItem,
                listData = tempData1,
                listData2 = tempData2,
                listDateData = tempDataDate,
                maxAxis =
                when(dataHealthPatient.typeHealthDataPatient){
                    TypeHealthDataPatient.BMI->{
                        200f
                    }
                    TypeHealthDataPatient.Temperature->{
                        50f
                    }
                    TypeHealthDataPatient.Spo2->{
                        110f
                    }
                    TypeHealthDataPatient.HeartRate->{
                        200f
                    }
                    TypeHealthDataPatient.BloodPressure->{
                        200f
                    }
                },
                minAxis =
                when(dataHealthPatient.typeHealthDataPatient){
                    TypeHealthDataPatient.BMI->{
                        10f
                    }
                    TypeHealthDataPatient.Temperature->{
                        20f
                    }
                    TypeHealthDataPatient.Spo2->{
                        40f
                    }
                    TypeHealthDataPatient.HeartRate->{
                        40f
                    }
                    TypeHealthDataPatient.BloodPressure->{
                        40f
                    }
                },
                onIconArrowUpClicked = {
                    onToDetailChart(dataHealthPatient.typeHealthDataPatient.name)
                }
            )
        }
    }
}
