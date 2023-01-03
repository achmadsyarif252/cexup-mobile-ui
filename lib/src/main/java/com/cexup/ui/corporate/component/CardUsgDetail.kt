package com.cexup.ui.corporate.component

import android.gesture.GestureLibraries.fromFile
import android.net.Uri.fromFile
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.cexup.ui.R
import com.cexup.ui.corporate.screen.ItemDetailUsg
import com.cexup.ui.corporate.theme.*
import com.cexup.ui.utils.coloredShadow
import com.cexup.ui.utils.mediaquery.from
import com.github.barteksc.pdfviewer.PDFView
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import compose.icons.Octicons
import compose.icons.octicons.ChevronRight16
import java.io.File

@Composable
fun CardPatientUsgDetail(
    patientName: String,
    date: String,
) {
    val ctx = LocalContext.current
    Surface(
        shape = RoundedCornerShape(8.dp.from(ctx)),
        modifier = Modifier
            .width(207.dp.from(ctx)),
        elevation = 2.dp.from(ctx)
    ) {
        Column(
            modifier = Modifier
                .background(BlueUSG)
                .padding(
                    horizontal = 10.dp.from(ctx),
                    vertical = 8.dp.from(ctx),
                )
        ) {
            Text(
                text = patientName,
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 20.sp.from(ctx),
                    lineHeight = 32.sp.from(ctx),
                    color = Natural
                )
            )
            Text(
                text = date,
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp.from(ctx),
                    lineHeight = 28.sp.from(ctx),
                    color = Natural80.copy(alpha = 0.8f)
                )
            )
        }
    }
}

@Composable
fun CardImageUsgDetail(
    stateLoading: Boolean,
    imageBitmap: ImageBitmap?,
    descriptionValue: String,
    diagnosisValue: String,
) {
    val ctx = LocalContext.current
    Surface(
        shape = RoundedCornerShape(8.dp.from(ctx)),
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 2.dp.from(ctx)
    ) {
        Column(
            modifier = Modifier
                .padding(
                    start = 16.dp.from(ctx),
                    end = 16.dp.from(ctx)
                )
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp.from(ctx)))
            if (imageBitmap == null||stateLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp.from(ctx)))
                        .height(326.dp.from(ctx))
                        .coloredShadow(MaterialTheme.colors.primary)
                        .placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.shimmer(),
                            color = Color.LightGray,
                        )
                        .background(GrayLoadingUSG),

                    ) {

                }
            } else {
                Image(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp.from(ctx)))
                        .fillMaxWidth()
                        .height(326.dp.from(ctx)),
                    bitmap = imageBitmap,
                    contentDescription = "Image"
                )
            }
            Spacer(modifier = Modifier.height(8.dp.from(ctx)))
            Text(
                text = stringResource(id = R.string.description),
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp.from(ctx),
                    lineHeight = 28.sp.from(ctx),
                    color = Natural
                )
            )
            Text(
                text = descriptionValue,
                fontSize = 12.sp.from(ctx),
                lineHeight = 20.sp.from(ctx),
                color = Natural80.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.height(8.dp.from(ctx)))
            Text(
                text = stringResource(id = R.string.diagnosis),
                style = MaterialTheme.typography.subtitle1.copy(
                    fontWeight = FontWeight.Medium,
                    fontSize = 16.sp.from(ctx),
                    lineHeight = 28.sp.from(ctx),
                    color = Natural
                )
            )
            Text(
                text = diagnosisValue,
                fontSize = 12.sp.from(ctx),
                lineHeight = 20.sp.from(ctx),
                color = Natural80.copy(alpha = 0.8f)
            )
            Spacer(modifier = Modifier.height(16.dp.from(ctx)))
        }
    }
}

@Composable
fun CardListDetailItemUSG(
    stateLoading: Boolean,
    listPicture: List<ImageBitmap>?,
    pathPDF: File,
    onItemClick: (indexItem: Int, typeItem: ItemDetailUsg) -> Unit,
) {
    val ctx = LocalContext.current
    var stateOnClickPicture by remember { mutableStateOf(false) }
    var stateOnClickPDF by remember { mutableStateOf(false) }
    Surface(
        shape = RoundedCornerShape(8.dp.from(ctx)),
        modifier = Modifier
            .fillMaxWidth(),
        elevation = 2.dp.from(ctx)
    ) {
        Column(
            modifier = Modifier
                .padding(
                    vertical = 8.dp.from(ctx),
                    horizontal = 12.dp.from(ctx),
                ),
            verticalArrangement = Arrangement.spacedBy(12.dp.from(ctx))
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    stateOnClickPicture = !stateOnClickPicture
                }
            ) {
                Text(
                    text = stringResource(id = R.string.picture),
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp.from(ctx),
                        lineHeight = 24.sp.from(ctx),
                        color = Natural90.copy(alpha = 0.9f)
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    Octicons.ChevronRight16,
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp.from(ctx))
                        .rotate(if (stateOnClickPicture) 270f else 90f),
                    tint = Natural.copy(alpha = 0.7f)
                )
            }
            if (listPicture == null || listPicture.isEmpty() || stateLoading) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp.from(ctx)))
                        .height(110.dp.from(ctx))
                        .coloredShadow(MaterialTheme.colors.primary)
                        .placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.shimmer(),
                            color = Color.LightGray,
                        )
                        .background(GrayLoadingUSG),
                )
            } else {
                if (stateOnClickPicture) {
                    listPicture.forEachIndexed { index, item ->
                        Image(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp.from(ctx)))
                                .height(110.dp.from(ctx))
                                .clickable {
                                    onItemClick(index, ItemDetailUsg.Picture)
                                }
                                .fillMaxWidth(),
                            bitmap = item,
                            contentDescription = "Image"
                        )
                    }
                } else {
                    Image(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp.from(ctx)))
                            .height(110.dp.from(ctx))
                            .clickable {
                                onItemClick(0, ItemDetailUsg.Picture)
                            }
                            .fillMaxWidth(),
                        bitmap = listPicture[0],
                        contentDescription = "Image"
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    stateOnClickPDF = !stateOnClickPDF
                }
            ) {
                Text(
                    text = stringResource(id = R.string.pdf),
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp.from(ctx),
                        lineHeight = 24.sp.from(ctx),
                        color = Natural90.copy(alpha = 0.9f)
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    Octicons.ChevronRight16,
                    contentDescription = "",
                    modifier = Modifier
                        .size(24.dp.from(ctx))
                        .rotate(if (stateOnClickPDF) 270f else 90f),
                    tint = Natural.copy(alpha = 0.7f)
                )
            }
            if (stateLoading){
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(8.dp.from(ctx)))
                        .height(if (stateOnClickPDF) 400.dp.from(ctx) else 194.dp.from(ctx))
                        .coloredShadow(MaterialTheme.colors.primary)
                        .placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.shimmer(),
                            color = Color.LightGray,
                        )
                        .background(GrayLoadingUSG),
                )
            }
            else{
                AndroidView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(if (stateOnClickPDF) 400.dp.from(ctx) else 194.dp.from(ctx)),
                    factory = {
                        PDFView(it, null)
                    },
                    update = {
    //                        it.fromAsset("Skripsi Pengenalan Kanker Melanoma [FINAL].pdf")
                        it.fromFile(pathPDF)
                            .onTap {
                                onItemClick(0, ItemDetailUsg.PDF)
                                true
                            }
                            .enableDoubletap(false)
                            .enableSwipe(false)
                            .load()
                    }
                )
            }

            Spacer(modifier = Modifier.height(8.dp.from(ctx)))
        }
    }
}

@Composable
fun CardReportDetailUSG(
    stateLoading: Boolean,
    pathPDF: File,
){
    val ctx = LocalContext.current
    var zoomLevel by remember { mutableStateOf(100) }
    Surface(
        shape = RoundedCornerShape(8.dp.from(ctx)),
        modifier = Modifier
            .padding(bottom = 16.dp.from(ctx))
            .fillMaxWidth(),
        elevation = 2.dp.from(ctx)
    ) {
        Column(
            modifier = Modifier
                .padding(
                    top = 8.dp.from(ctx),
                    start = 12.dp.from(ctx),
                    end = 12.dp.from(ctx),
                    bottom = if (stateLoading) 8.dp.from(ctx) else 0.dp
                ),
            verticalArrangement = Arrangement.spacedBy(12.dp.from(ctx))
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp.from(ctx))) {
                Text(
                    text = stringResource(id = R.string.report),
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp.from(ctx),
                        lineHeight = 24.sp.from(ctx),
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.weight(1f))
//                Icon(painter = painterResource(id = R.drawable.ic_zoom_out), contentDescription = "Zoom Out")
                Text(
                    text = "$zoomLevel%",
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Medium,
                        fontSize = 16.sp.from(ctx),
                        lineHeight = 24.sp.from(ctx),
                        color = Color.Black
                    )
                )
//                Icon(painter = painterResource(id = R.drawable.ic_zoom_in), contentDescription = "Zoom In")
                Spacer(modifier = Modifier.width(15.dp.from(ctx)))
                Icon(painter = painterResource(id = R.drawable.ic_share), contentDescription = "Share")
            }
            if (stateLoading){
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp.from(ctx)))
                        .height(480.dp.from(ctx))
                        .fillMaxWidth()
                        .coloredShadow(MaterialTheme.colors.primary)
                        .placeholder(
                            visible = true,
                            highlight = PlaceholderHighlight.shimmer(),
                            color = Color.LightGray,
                        ),
                )
            }
            else {
                AndroidView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(480.dp.from(ctx)),
                    factory = {
                        PDFView(it, null)
                    },
                    update = { pdfView ->
//                    pdfView.fromAsset("Skripsi Pengenalan Kanker Melanoma [FINAL].pdf")

                        pdfView.fromFile(pathPDF)
                            .onDraw { canvas, pageWidth, pageHeight, displayedPage ->
                                zoomLevel = (pdfView.zoom * 100f).toInt()
                            }

                            .load()
                        pdfView.zoomTo(1.75f)
                    }
                )
            }
        }
    }
}


@Preview(device = Devices.TABLET)
@Composable
fun PreviewCardUsgDetail() {
    Column {
        CardPatientUsgDetail(
            patientName = "Nijika Ichiji",
            date = "22/03/2020 12:34 AM"
        )
        CardImageUsgDetail(
            imageBitmap = ImageBitmap.imageResource(id = R.drawable.bg_rs),
            descriptionValue = "asd",
            diagnosisValue = "asd",
            stateLoading = false
        )
        CardListDetailItemUSG(
            onItemClick = { _, _ -> },
            listPicture = listOf(),
            pathPDF = File(""),
            stateLoading = false
        )
    }
}