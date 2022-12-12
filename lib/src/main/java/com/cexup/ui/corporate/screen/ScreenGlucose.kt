package com.cexup.ui.corporate.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.corporate.component.*
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.SecondaryCorporate
import com.cexup.ui.utils.mediaquery.from
import com.example.app_corporate.ui.component.cards.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

data class GlucoseDataUIState(
    var patientName: String,
    var patientUserCode: String,
    var patientThumb: String = "",

    var listDataHemoglobin: List<ValueHemoglobin>,
    var listDataGlucose1Day: List<ValueBloodGlucose>,
    var listDataGlucose1Week: List<ValueBloodGlucose>,
    var listDataGlucose2Week: List<ValueBloodGlucose>,
    var listDataGlucose1Month: List<ValueBloodGlucose>,
    var totalPills1Day: Int?,
    var totalPills1Week: Int?,
    var totalPills2Week: Int?,
    var totalPills1Month: Int?,
    var totalGlucose1Day: Int?,
    var totalGlucose1Week: Int?,
    var totalGlucose2Week: Int?,
    var totalGlucose1Month: Int?,
)

data class ValueBloodGlucose(
    var id:Long,
    var value: Int,
    var time: String,
    var mealState: Int,
    var insulin: Int? = null,
    var pills: Int? = null,
    var foodAndDrink: String? = null,
    var isDetail: Boolean = false,
)

object MealType {
    const val BeforeMeal = 0
    const val AfterMeal = 1
    const val NoMeal = 2
}
object InsulinType{
    const val ShortActing = 0
    const val LongActing = 1
}
object TypeAddData{
    const val Glucose = "Glucose"
    const val Hemoglobin = "Hemoglobin"
    const val Insulin = "Insulin"
    const val Pill = "Pill"
    const val FoodAndDrink = "Food & Drink Note"
}

data class StateGlucoseSDK(
    val connect: Boolean = false,
    val sync: Boolean = false,
    val connecting: Boolean = false,
)


@ExperimentalComposeUiApi
@ExperimentalPagerApi
@Composable
fun ScreenGlucose(
    glucoseDataUIState: GlucoseDataUIState,
    stateGlucoseSDK: StateGlucoseSDK = StateGlucoseSDK(),
    onSync: () -> Unit,
    onConnect: () -> Unit,
    onDisconnect: () -> Unit,
    onAddGlucose: (Date:String,Hours:String,Value:String,ValueDetail:Int) -> Unit,
    onAddMedicine: (Date:String,Hours:String,Value:String,ValueDetail:Int,idDataGlucose: Long, TypeMedicine:Boolean) -> Unit,
    onAddHemoglobin: (Date:String,Hours:String,Value:String) -> Unit,
    onAddFoodAndDrink: (Value:String,idDataGlucose: Long) -> Unit,
    onButtonBackPressed: () -> Unit,
) {
    val ctx = LocalContext.current
    val pagerState = rememberPagerState()
    var isList by remember { mutableStateOf(false) }
    var isAddMedicine by remember { mutableStateOf(false) }
    var isAddFoodAndDrink by remember { mutableStateOf(false) }
    var dataDetailGlucose by remember { mutableStateOf(DetailsGlucose(null, null, "")) }
    var showDialogAddData by remember { mutableStateOf(false) }
    var showDialogHistoryHemoglobin by remember { mutableStateOf(false) }
    var showDialogDetailsGlucose by remember { mutableStateOf(false) }
    var idDataGlucose by remember { mutableStateOf(0L) }
    val tabs = listOf(
        TabContentRow(header = stringResource(id = R.string.one_day)) {
            ContentTabGlucose(
                onAddMedicineClicked = { bool, id ->
                    isAddMedicine = bool
                    idDataGlucose = id
                    showDialogAddData = true
                },
                onAddFoodAndDrinkClicked = { bool, id ->
                    isAddFoodAndDrink = bool
                    idDataGlucose = id
                    showDialogAddData = true
                },
                isList = isList,
                listDataValueGlucose = glucoseDataUIState.listDataGlucose1Day,
                onIconClick = {
                    isList = it
                },
                is1Day = true,
                onDetailsClicked = { insulin, pill, foodAndDrink ->
                    dataDetailGlucose = DetailsGlucose(insulin, pill, foodAndDrink)
                    showDialogDetailsGlucose = true
                }
            )
        },
        TabContentRow(header = stringResource(id = R.string.one_weeks)) {
            ContentTabGlucose(
                onAddMedicineClicked = { bool, id ->
                    isAddMedicine = bool
                    idDataGlucose = id
                    showDialogAddData = true
                },
                onAddFoodAndDrinkClicked = { bool, id ->
                    isAddFoodAndDrink = bool
                    idDataGlucose = id
                    showDialogAddData = true
                },
                isList = isList,
                listDataValueGlucose = glucoseDataUIState.listDataGlucose1Week,
                onIconClick = {
                    isList = it
                },
                onDetailsClicked = { insulin, pill, foodAndDrink ->
                    dataDetailGlucose = DetailsGlucose(insulin, pill, foodAndDrink)
                    showDialogDetailsGlucose = true
                }
            )
        },
        TabContentRow(header = stringResource(id = R.string.two_weeks)) {
            ContentTabGlucose(
                onAddMedicineClicked = { bool, id ->
                    isAddMedicine = bool
                    idDataGlucose = id
                    showDialogAddData = true
                },
                onAddFoodAndDrinkClicked = { bool, id ->
                    isAddFoodAndDrink = bool
                    idDataGlucose = id
                    showDialogAddData = true
                },
                isList = isList,
                listDataValueGlucose = glucoseDataUIState.listDataGlucose2Week,
                onIconClick = {
                    isList = it
                },
                onDetailsClicked = { insulin, pill, foodAndDrink ->
                    dataDetailGlucose = DetailsGlucose(insulin, pill, foodAndDrink)
                    showDialogDetailsGlucose = true
                }
            )
        },
        TabContentRow(header = stringResource(id = R.string.one_month)) {
            ContentTabGlucose(
                onAddMedicineClicked = { bool, id ->
                    isAddMedicine = bool
                    idDataGlucose = id
                    showDialogAddData = true
                },
                onAddFoodAndDrinkClicked = { bool, id ->
                    isAddFoodAndDrink = bool
                    idDataGlucose = id
                    showDialogAddData = true
                },
                isList = isList,
                listDataValueGlucose = glucoseDataUIState.listDataGlucose1Month,
                onIconClick = {
                    isList = it
                },
                onDetailsClicked = { insulin, pill, foodAndDrink ->
                    dataDetailGlucose = DetailsGlucose(insulin, pill, foodAndDrink)
                    showDialogDetailsGlucose = true
                }
            )
        },
    )

    DialogAddDataGlucose(
        onCancel = {
            showDialogAddData = false
            isAddMedicine = false
            isAddFoodAndDrink = false
        },
        onSave = {typeAddData, Date, Hours, Value, ValueDetail->
            when(typeAddData){
                TypeAddData.Glucose -> {
                    onAddGlucose(Date,Hours,Value,ValueDetail)
                }
                TypeAddData.Pill -> {
                    onAddMedicine(Date,Hours,Value,ValueDetail,idDataGlucose,true)
                }
                TypeAddData.Insulin -> {
                    onAddMedicine(Date,Hours,Value,ValueDetail,idDataGlucose,false)
                }
                TypeAddData.Hemoglobin -> {
                    onAddHemoglobin(Date,Hours,Value)
                }
                TypeAddData.FoodAndDrink -> {
                    onAddFoodAndDrink(Value,idDataGlucose)
                }
            }
            showDialogAddData = false
            isAddMedicine = false
            isAddFoodAndDrink = false
        },
        show = showDialogAddData,
        isAddMedicine = isAddMedicine,
        isAddFoodAndDrink = isAddFoodAndDrink
    )
    DialogHistoryHemoglobin(
        onCancel = {
            showDialogHistoryHemoglobin = false
        },
        show = showDialogHistoryHemoglobin,
        onSortDateClicked = {},
        onSortResultClicked = {},
        dataHemoglobin = glucoseDataUIState.listDataHemoglobin
    )
    DialogDetailsGlucose(
        dataDetailsGlucose = dataDetailGlucose,
        show = showDialogDetailsGlucose,
        onCancel = { showDialogDetailsGlucose = false }
    )
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 15.dp.from(ctx), vertical = 30.dp.from(ctx)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp.from(ctx)),
            verticalAlignment = Alignment.CenterVertically
        ) {
            CardPatientInFeature(
                thumb = glucoseDataUIState.patientThumb,
                name = glucoseDataUIState.patientName,
                id = glucoseDataUIState.patientUserCode
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = { onSync() },
                enabled = stateGlucoseSDK.connect && !stateGlucoseSDK.sync
            ) {
                Image(
                    painter = painterResource(
                        id = R.drawable.ic_sync),
                    contentDescription = "",
                )
            }
            Spacer(modifier = Modifier.width(24.dp.from(ctx)))
            Button(
                onClick = {
                    onButtonBackPressed()
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
                        fontSize = 14.sp,
                        letterSpacing = 1.sp,
                        color = Color.White
                    )
                )
            }
        }
        TabView(
            modifier = Modifier
                .align(CenterHorizontally)
                .width(385.dp.from(ctx)),
            tabContents = tabs,
            pagerState = pagerState,
            colorUnderline = Heading
        )
        Spacer(modifier = Modifier.height(23.dp.from(ctx)))
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp.from(ctx)),
            modifier = Modifier.padding(horizontal = 15.dp.from(ctx)),
        ) {
            CardHemoglobin(
                textValue = glucoseDataUIState.listDataHemoglobin[0].valueHemoglobin.toString(),
                onHistoryClicked = { showDialogHistoryHemoglobin = it })
            CardPills(
                valuePills = when (pagerState.currentPage) {
                    0 -> glucoseDataUIState.totalPills1Day ?: 0
                    1 -> glucoseDataUIState.totalPills1Week ?: 0
                    2 -> glucoseDataUIState.totalPills2Week ?: 0
                    3 -> glucoseDataUIState.totalPills1Month ?: 0
                    else -> 0
                }
            )
            CardValueGlucose(
                valueGlucose = when (pagerState.currentPage) {
                    0 -> glucoseDataUIState.totalGlucose1Day ?: 0
                    1 -> glucoseDataUIState.totalGlucose1Week ?: 0
                    2 -> glucoseDataUIState.totalGlucose2Week ?: 0
                    3 -> glucoseDataUIState.totalGlucose1Month ?: 0
                    else -> 0
                }
            )
            CardConnectGlucose(
                onAddDataClicked = { showDialogAddData = it },
                stateGlucoseSDK = stateGlucoseSDK,
                onDisconnect = { onDisconnect() },
                onConnect = { onConnect() }
            )
        }
        Spacer(modifier = Modifier.height(23.dp.from(ctx)))
        TabContent(tabContents = tabs, pagerState = pagerState)
    }
}

@Composable
fun ContentTabGlucose(
    isList: Boolean,
    is1Day: Boolean = false,
    onAddMedicineClicked: (Boolean,Long) -> Unit,
    onAddFoodAndDrinkClicked: (Boolean,Long) -> Unit,
    onIconClick: (isList: Boolean) -> Unit,
    onDetailsClicked: (Insulin: Int, Pill: Int, FoodAndDrink: String) -> Unit,
    listDataValueGlucose: List<ValueBloodGlucose>
) {
    CardGlucoseLevels(
        isList = isList,
        onIconClick = {
            onIconClick(it)
        },
        listDataValueGlucose = listDataValueGlucose,
        onAddMedicine = { bool, id ->
            onAddMedicineClicked(bool,id)
        },
        onAddFoodAndDrink = { bool, id ->
            onAddFoodAndDrinkClicked(bool,id)
        },
        is1Day = is1Day,
        onDetailsClicked = { insulin, pill, foodAndDrink ->
            onDetailsClicked(insulin, pill, foodAndDrink)
        }
    )

}