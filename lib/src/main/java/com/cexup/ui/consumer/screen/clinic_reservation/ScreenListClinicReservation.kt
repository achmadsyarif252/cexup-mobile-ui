package com.cexup.ui.consumer.screen.clinic_reservation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.consumer.component.*
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.cexup.ui.consumer.theme.TextInactive
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.mediaquery.from
import compose.icons.Octicons
import compose.icons.octicons.ChevronLeft24
import kotlinx.coroutines.launch

data class ClinicHospitalUIState(
    var loading: Boolean = true,
    var error: Boolean = false,
    var errorMessage: String = "",
    var data: List<HospitalUIState> = listOf()
)
data class HospitalUIState(
    var slug:String,
    var description:String?="",
    var name:String,
    var address:String?="",
    var others:String,
    var thumb_original:String?="",
    var thumb:String?="",
)

enum class BottomSheetType {
    TYPE1, TYPE2, TYPE3
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ScreenListClinicReservation(
    modifier: Modifier = Modifier,
    hospitalState: ClinicHospitalUIState,
    onBackPressed: () -> Unit = {},
    toDetailHospital: (slug: String, name: String) -> Unit = { slug, name -> },

    onSearchHospital:(String)->Unit
){

    val ctx = LocalContext.current

    val screenWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    val scope = rememberCoroutineScope()

    val bottomSheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        skipHalfExpanded = true
    )
    var currentBottomSheet by remember {
        mutableStateOf<BottomSheetType?>(BottomSheetType.TYPE1)
    }
    val closeSheet = {
        scope.launch { bottomSheetState.hide() }
    }

    val openSheet = {
        scope.launch { bottomSheetState.show() }
    }
    val listContent = listOf(
        "Health facility type",
        "Inspection service",
        "kota",
        "kecamatan",
        "Facility"
    )
    var filterName by remember {
        mutableStateOf("data")
    }
    ModalBottomSheetLayout(
        sheetShape = RoundedCornerShape(topStart = 30.dp.from(ctx), topEnd = 30.dp.from(ctx)),
        sheetState = bottomSheetState,
        sheetContent = {
            currentBottomSheet?.let {

                SheetLayout(
                    closeSheet = {
                        closeSheet()
                    },
                    bottomSheetType = it,
                    listContent = listContent,
                    onClick = {
                        filterName = it
                        currentBottomSheet = BottomSheetType.TYPE3
                        openSheet()
                    },
                    filterName = filterName
                )
            }

        },
        sheetElevation = 1.dp.from(ctx),
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { onBackPressed() }) {
                            Icon(
                                Octicons.ChevronLeft24,
                                contentDescription = "",
                                tint = Color.Gray,
                            )
                        }
                    },
                    title = {
                        Text(
                            text = stringResource(id = R.string.meet_doctor),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.h2.copy(
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = MaterialTheme.colors.onBackground
                        )
                    },
                    elevation = 0.dp,
                    backgroundColor = MaterialTheme.colors.background
                )
            }
        ) {
            Box(
                modifier = modifier
                    .fillMaxSize()
                    .padding(it)
//                    .background(color = MaterialTheme.colors.secondary)

            ) {
                Column(
                    modifier = modifier
                        .padding(
                            horizontal = 16.dp
                        )
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.spacedBy(16.dp.from(ctx))
                ) {
                    InputSearch(
                        hint = stringResource(id = R.string.text_search_medical_facility),
                        trilling = true,
                        widthSearch = screenWidth,
                        heightSearch = 40.dp.from(ctx),
                        iconSize = 16.dp.from(ctx),
                        fontHintSize = 14.sp.from(ctx),
                        onSearch = {
                            onSearchHospital(it)
                        }
                    )
                    Text(
                        text = "Total ${hospitalState.data.size} health facilities are ready to serve you",
                        style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight(600),
                            fontSize = 12.sp.from(ctx),
                            color = MaterialTheme.colors.onSecondary,
                            letterSpacing = 0.3.sp.from(ctx),
                            lineHeight = 18.sp.from(ctx),
                        ),
                        modifier = modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colors.secondary)
                            .padding(horizontal = 24.dp.from(ctx))
                    )

                    LazyColumn(
                        modifier = modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(horizontal = 16.dp.from(ctx)),
                        verticalArrangement = Arrangement.spacedBy(16.dp.from(ctx))
                    ) {
                        if (hospitalState.loading) {
                            items(4) {
                                CardHospitalMeetDoctorShimmer()
                            }
                        }
                        items(
                            hospitalState.data
                        ) { hospital ->
                            CardHospitalMeetDoctor(
                                hospitalName = hospital.name,
                                hospitalAddress = hospital.address,
                                hospitalThumb = hospital.thumb,
                                hospitalSlug = hospital.slug,
                                onClick = { slug, name ->
                                    toDetailHospital(slug, name)
                                },
                            )
                        }
                    }
                }
                Card(
                    modifier = modifier
                        .padding(bottom = 30.dp.from(ctx))
                        .align(Alignment.BottomCenter)
                        .coloredShadow(
                            color = Color.Black.copy(0.05f),
                            alpha = 0.05f,
                            offsetY = 4.dp,
                            shadowRadius = 20.dp.from(ctx),
                            borderRadius = 15.dp.from(ctx)
                        )
                        .width(191.dp.from(ctx))
                        .height(40.dp.from(ctx)),
                    shape = RoundedCornerShape(15.dp.from(ctx)),
                    elevation = 0.3.dp.from(ctx),
                ) {
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .fillMaxHeight(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Row(
                            modifier = modifier
                                .fillMaxHeight()
                                .clickable {
                                    currentBottomSheet = BottomSheetType.TYPE1
                                    openSheet()
                                }
                                .then(Modifier.padding(horizontal = 24.dp.from(ctx)))
                            ,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_sort),
                                contentDescription = "",
                                modifier = Modifier.size(
                                    width = 13.dp.from(ctx),
                                    height = 14.dp.from(ctx)
                                )
                            )
                            Spacer(modifier = Modifier.width(7.dp.from(ctx)))
                            Text(
                                text = stringResource(id = R.string.sort),
                                style = MaterialTheme.typography.body2.copy(
                                    fontWeight = FontWeight(400),
                                    fontSize = 14.sp.from(ctx),
                                    lineHeight = 20.sp.from(ctx),
                                    letterSpacing = 0.1.sp.from(ctx),
                                )
                            )
                        }
                        Divider(
                            modifier = modifier
                                .width(1.dp.from(ctx))
                                .padding(vertical = 9.dp.from(ctx))
                                .fillMaxHeight(),
                            color = TextInactive
                        )
                        Row(
                            modifier = modifier
                                .fillMaxHeight()
                                .clickable {
                                    currentBottomSheet = BottomSheetType.TYPE2
                                    openSheet()
                                }
                                .then(Modifier.padding(start = 20.dp.from(ctx))),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_filter),
                                contentDescription = "",
                                modifier = Modifier.size(
                                    width = 16.dp.from(ctx),
                                    height = 12.dp.from(ctx)
                                )
                            )
                            Spacer(modifier = Modifier.width(6.dp.from(ctx)))
                            Text(modifier = Modifier.fillMaxWidth(),
                                text = stringResource(id = R.string.filter),
                                style = MaterialTheme.typography.body1.copy(
                                    fontWeight = FontWeight(400),
                                    fontSize = 14.sp.from(ctx),
                                    lineHeight = 20.sp.from(ctx),
                                    letterSpacing = 0.1.sp.from(ctx),
                                )
                            )
                        }

                    }
                }
            }
        }
    }

}

@Composable
fun SheetLayout(
    bottomSheetType: BottomSheetType,
    closeSheet: () -> Unit,
    listContent: List<String>,
    onClick: (filterName: String) -> Unit,
    filterName: String = ""
) {
    var listselected by remember {
        mutableStateOf(listOf(""))
    }
    val inspection = listOf(
        stringResource(id = R.string.medical_check_up),
        stringResource(id = R.string.swab_pcr),
        stringResource(id = R.string.rapid_test),
        stringResource(id = R.string.vaksinasi),
        stringResource(id = R.string.rontgen),
        stringResource(id = R.string.test_darah),
        stringResource(id = R.string.ct_darah),
    )
    val Kota = listOf(
        stringResource(id = R.string.jkt_barat),
        stringResource(id = R.string.jkt_pusat),
        stringResource(id = R.string.jkt_timur),
        stringResource(id = R.string.jkt_selatan),
        stringResource(id = R.string.jkt_utara),
    )

    var listContentFilter = when (filterName) {
        "Health facility type" -> listselected
        "Inspection service" -> inspection
        "kota" -> Kota
        "kecamatan" -> inspection
        "Facility" -> inspection
        else -> listOf()
    }
    when (bottomSheetType) {
        BottomSheetType.TYPE1 -> BottomSheetSortMeetDoctor(closed = closeSheet)
        BottomSheetType.TYPE2 -> BottomSheetFilterListMeetDoctor(
            listContent = listContent,
            onCloseSheet = closeSheet,
            onClick = onClick
        )
        BottomSheetType.TYPE3 -> BottomSheetContentFilterListMeet(
            listContent = listContentFilter,
            nameBottomSheet = filterName
        )
    }

}

@Preview
@Composable
fun PreviewScreenListClinicReservation(){
   ConsumerTheme {
       ScreenListClinicReservation(
           hospitalState = ClinicHospitalUIState(
               loading = true,
               error = false,
               data = listOf()
           ),
           onSearchHospital = {}
       )
   }
}