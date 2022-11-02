package com.cexup.ui.corporate.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.GreyBlack
import com.cexup.ui.corporate.theme.Heading
import com.cexup.ui.corporate.theme.inactive

@Composable
fun CardReportDocument(
    modifier: Modifier = Modifier,
    nameFile: String,
    fileSize: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(5.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bx_file),
                contentDescription = "",
                tint = Heading,
                modifier = modifier
                    .size(21.dp)
            )
            Spacer(modifier = Modifier.width(10.33.dp))
            Column {
                Text(
                    text = nameFile,
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight(500),
                        color = GreyBlack
                    ),

                    )

                Text(
                    text = fileSize,
                    fontSize = 12.sp,
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight(300),
                        color = inactive
                    ),

                    )
            }
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_bx_printer),
                contentDescription = "",
                tint = Heading,
                modifier = modifier
                    .size(17.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_download),
                contentDescription = "",
                tint = Heading,
                modifier = modifier
                    .size(17.dp)
            )
        }
    }

}