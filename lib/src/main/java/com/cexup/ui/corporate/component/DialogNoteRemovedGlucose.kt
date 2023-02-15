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
import androidx.compose.ui.platform.LocalConfiguration
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



@Composable
fun DialogNoteRemovedGlucose(
    valueNoteRemoved: String,
    show:Boolean,
    onCancel: () -> Unit,
){
    val ctx = LocalConfiguration.current
    if (show){
        Dialog(onDismissRequest = { onCancel() }) {
            Surface(
                modifier = Modifier
                .verticalScroll(rememberScrollState()),
                shape = RoundedCornerShape(20.dp.from(ctx)),
                color = Color.White
            ) {
                CardNoteGlucose(
                    valueText = valueNoteRemoved,
                    enable = false,
                    onValueChange = {},
                    titleText = stringResource(id = R.string.note_data_removed),
                    placeHolderText = stringResource(id = R.string.reason_why_data_deleted),
                    onCancel = { onCancel() },
                    isNote = true,
                )
            }
        }
    }
}