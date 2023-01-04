@file:OptIn(ExperimentalMaterialApi::class)

package com.cexup.ui.corporate.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.corporate.component.CardImageUsgDetail
import com.cexup.ui.corporate.component.CardListDetailItemUSG
import com.cexup.ui.corporate.component.CardPatientUsgDetail
import com.cexup.ui.corporate.component.CardReportDetailUSG
import com.cexup.ui.corporate.theme.BlueJade
import com.cexup.ui.corporate.theme.SecondaryCorporate
import com.cexup.ui.utils.mediaquery.from
import java.io.File

data class ScreenDetailUsgUIState(
    val data: DataDetailUSG? = DataDetailUSG(),
    val message: String = "",
    val error: Boolean = false,
    val loading: Boolean = true,
)

data class DataDetailUSG(
    val patientName: String? = "",
    val idData: Long? = 0,
    val date: String? = "",
    val gestationalAge: String? = "",
    val imageList: List<ImageBitmap>? = listOf(),
    val description: String? = "",
    val diagnosis: String? = "",
    val fileLocation: File? = File(""),
)


enum class ItemDetailUsg {
    Picture, PDF
}

@Composable
fun ScreenDetailUsg(
    dataDetailsUsg: ScreenDetailUsgUIState,
    onButtonBackPressed: () -> Unit = {},
) {
    var typeItemDetail by remember { mutableStateOf(ItemDetailUsg.Picture) }
    var indexImageClicked by remember { mutableStateOf(0) }
    val ctx = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colors.background)
            .padding(top = 32.dp.from(ctx), end = 32.dp.from(ctx), start = 32.dp.from(ctx)),
        verticalArrangement = Arrangement.spacedBy(23.dp.from(ctx))
    ) {
        Row {
            Text(
                text = stringResource(id = R.string.usg_detail_examination),
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 24.sp.from(ctx),
                    lineHeight = 32.sp.from(ctx),
                    letterSpacing = -2.sp.from(ctx),
                    color = BlueJade
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            CompositionLocalProvider(
                LocalMinimumTouchTargetEnforcement provides false,
            ) {
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
                            fontSize = 14.sp.from(ctx),
                            letterSpacing = 1.sp.from(ctx),
                            color = Color.White
                        )
                    )
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp.from(ctx))) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(bottom = 16.dp.from(ctx), end = 12.dp.from(ctx))
                    .width(207.dp.from(ctx)),
                verticalArrangement = Arrangement.spacedBy(16.dp.from(ctx))
            ) {
                CardPatientUsgDetail(
                    patientName = dataDetailsUsg.data?.patientName ?: "",
                    date = dataDetailsUsg.data?.date ?: ""
                )
                CardListDetailItemUSG(
                    listPicture = dataDetailsUsg.data?.imageList ?: listOf(),
                    pathPDF = dataDetailsUsg.data?.fileLocation ?: File(""),
                    onItemClick = { indexItem, typeItem ->
                        indexImageClicked = indexItem
                        typeItemDetail = typeItem
                    },
                    stateLoading = dataDetailsUsg.loading
                )
            }
            if (typeItemDetail == ItemDetailUsg.Picture) {
                CardImageUsgDetail(
                    imageBitmap =
                    if (dataDetailsUsg.data?.imageList == null || dataDetailsUsg.data.imageList.isEmpty())
                        null
                    else
                        dataDetailsUsg.data?.imageList[indexImageClicked],
                    descriptionValue = dataDetailsUsg.data?.description ?: "",
                    diagnosisValue = dataDetailsUsg.data?.diagnosis ?: "",
                    stateLoading = dataDetailsUsg.loading
                )
            } else {
                CardReportDetailUSG(
                    stateLoading = dataDetailsUsg.loading,
                    pathPDF = dataDetailsUsg.data?.fileLocation?: File("")
                )
            }
        }
    }
}

@Preview(device = Devices.TABLET)
@Composable
fun PreviewScreenDetailUSGNew() {
    ScreenDetailUsg(
        dataDetailsUsg =
        ScreenDetailUsgUIState(
            data = DataDetailUSG(
                patientName = "sumbul asli",
                date = "22/03/2020 12:34 AM",
                idData = 0,
                gestationalAge = "",
                fileLocation = File(""),
                description = "Bocchi the rock season 2",
                imageList = listOf(),
                diagnosis = "Anjay mujay mabar",
            )
        )
    )
}