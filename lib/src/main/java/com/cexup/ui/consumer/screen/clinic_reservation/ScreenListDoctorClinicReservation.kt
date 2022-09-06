package com.cexup.ui.consumer.screen.clinic_reservation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.consumer.component.CardDoctorOnline
import com.cexup.ui.consumer.component.CardDoctorOnlineShimmer
import com.cexup.ui.consumer.component.ChipFilter
import com.cexup.ui.consumer.component.SearchInput
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.cexup.ui.consumer.theme.RedLight
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.mediaquery.from
import compose.icons.Octicons
import compose.icons.octicons.ChevronLeft24


data class DoctorHospitalUIState(
    var loading: Boolean = true,
    var error: Boolean = false,
    var errorMessage: String = "",
    var data: List<DoctorOnlineUIState> = listOf()
)

data class DoctorOnlineUIState(
    var title: String,
    var slug: String,
    var description: String,
    var speciality: String,
    var hospital: String,
    var thumb_original: String,
    var thumb: String,
    var price:String
)

data class SpecialityUIState(
    var loading: Boolean = true,
    var error: Boolean = false,
    var errorMessage: String = "",
    var data: List<Pair<String,String>> = listOf()
)



@Composable
fun ScreenListDoctorClinicReservation(
    modifier: Modifier = Modifier,
    hospitalName:String="",
    selectedFiltered:String="",
    doctorState: DoctorHospitalUIState,
    speciality: SpecialityUIState,
    onBackPressed: () -> Unit = {},
    toDetailDoctor: (slug: String) -> Unit,

    onSearchByName: (filter: String) -> Unit,
    onSearchBySpecialist: (filter: String) -> Unit,
) {

    val ctx = LocalContext.current

    val screenWidth = ctx
        .resources
        .displayMetrics.widthPixels.dp /
            LocalDensity.current.density
    
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
                        text = hospitalName,
                        style = MaterialTheme.typography.h2.copy(
                            fontWeight = FontWeight.SemiBold,
                            color =  Color.Gray,
                            fontSize = 18.sp.from(ctx),
                            lineHeight = 28.sp.from(ctx),
                            letterSpacing = (-0.25).sp.from(ctx)
                        ),
                        textAlign = TextAlign.Center
                    )
                },
                elevation = 0.dp,
                backgroundColor = MaterialTheme.colors.background
            )

        },
    ) {
        Column(
            modifier = modifier
                .padding(it)
                .padding(
                    horizontal = 16.dp
                )
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            SearchInput(
                hint = "Search for doctor",
                trilling = true,
                widthSearch = screenWidth,
                heightSearch = 40.dp.from(ctx),
                horizontalPadding = 16.32.dp.from(ctx),
                verticalPadding = 10.dp.from(ctx),
                iconSize = 16.dp.from(ctx),
                fontHintSize = 16.sp.from(ctx),
                onSearch = {
                    onSearchByName(it)
                }
            )
            Spacer(modifier = Modifier.height(16.dp.from(ctx)))
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(16.dp.from(ctx))
            ) {
                item {
                    Row(
                        modifier = modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = modifier
                                .padding(
                                    end = 16.dp.from(ctx),
                                    top = 8.dp.from(ctx),
                                    bottom = 8.dp.from(ctx)
                                )
                                .clickable {

                                }
                        ) {
                            Row(
                                modifier = modifier
                                    .coloredShadow(
                                        color = Color.Black.copy(0.05f),
                                        alpha = 0.05f,
                                        offsetY = 2.dp,
                                        shadowRadius = 2.dp.from(ctx),
                                        borderRadius = 10.dp.from(ctx)
                                    )
                                    .width(67.dp.from(ctx))
                                    .height(27.dp.from(ctx))
                                    .background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(10.dp.from(ctx))
                                    ),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {

                                Badge(
                                    backgroundColor = RedLight,
                                    content = {
                                        Text(
                                            text = "1",
                                            style = MaterialTheme.typography.subtitle1.copy(
                                                color = Color.White,
                                                fontSize = 8.sp.from(ctx),
                                                fontWeight = FontWeight(500),
                                                lineHeight = 12.sp.from(ctx),
                                                letterSpacing = 0.8.sp.from(ctx)
                                            ),
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                )

                                Spacer(modifier = Modifier.width(6.dp.from(ctx)))
                                Text(
                                    text = "Filter",
                                    style = MaterialTheme.typography.body1.copy(
                                        color = MaterialTheme.colors.onBackground,
                                        fontSize = 12.sp.from(ctx),
                                        fontWeight = FontWeight(500),
                                        lineHeight = 12.sp.from(ctx)
                                    )
                                )
                            }
                        }
                        LazyRow(
                            modifier = modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp.from(ctx))
                        ) {
                            itemsIndexed(speciality.data) { index, data ->
                                ChipFilter(
                                    text = data.first,
                                    selected = selectedFiltered == data.second,
                                    marginEnd = 8.dp.from(ctx),
                                    marginStart = if (index == 0) 0.dp else 0.dp,
                                    onClick = {
                                        onSearchBySpecialist(data.first)
                                    }
                                )
                            }
                        }
                    }
                }
                if(doctorState.loading){
                    items(4){
                        CardDoctorOnlineShimmer()
                    }
                }
                items(doctorState.data) { doctor ->
                    CardDoctorOnline(
                        doctorName = doctor.title,
                        doctorThumb = doctor.thumb_original,
                        speciality = doctor.speciality,
                        price = doctor.price,
                        hospital = doctor.hospital,
                        onClick = {
                            toDetailDoctor(doctor.slug)
                        }
                    )
                }
            }
        }
    }


}

@Preview
@Composable
fun PreviewScreenListDoctorClinicReservation() {
    ConsumerTheme {

        ScreenListDoctorClinicReservation(
            hospitalName="RS Universitas Indonesia",
            doctorState = DoctorHospitalUIState(
                loading = true,
                error = false,
                data = listOf()
            ),
            speciality = SpecialityUIState(
                loading = false,
                error = false,
                data = listOf(Pair("All","all"), Pair("Second","second"))
            ),
            toDetailDoctor = {},
            onSearchByName = {},
            onSearchBySpecialist = {}
        )
    }
}