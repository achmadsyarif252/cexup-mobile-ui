package com.cexup.ui.corporate.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.R
import com.cexup.ui.corporate.theme.GreyBlackStetoscope
import com.cexup.ui.corporate.theme.inactive

@Composable
fun CardEmptySearch(
    modifier: Modifier = Modifier,
    searchPattern: String = ""
){
    Column(
        modifier = modifier
            .width(421.dp)
            .padding(vertical = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_empty_state_search),
            contentDescription = " Empty Search",
            modifier = modifier.size(39.dp)
        )
        Spacer(modifier = modifier.height(10.dp))
        Text(
            text = stringResource(id = R.string.oops),
            style = MaterialTheme.typography.body1.copy(
                color = GreyBlackStetoscope,
                fontSize = 22.sp,
            )
        )
        Spacer(modifier = modifier.height(14.dp))
        Text(
            text = stringResource(
                id = R.string.search_no_result,
                formatArgs = arrayOf(searchPattern)
            ),
            style = MaterialTheme.typography.body1.copy(
                color = inactive,
                fontSize = 12.sp,
                textAlign = TextAlign.Center
            ),
            maxLines = 1,
        )
    }
}
