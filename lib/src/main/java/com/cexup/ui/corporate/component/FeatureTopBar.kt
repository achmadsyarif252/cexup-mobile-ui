package com.cexup.ui.corporate.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.corporate.theme.BlueJade
import com.cexup.ui.corporate.theme.SecondaryCorporate
import com.cexup.ui.R

@Composable
fun FeatureTopBar(
    onButtonBackPressed: () -> Unit,
    onManualInputPressed: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        CardPatientInFeature(thumb = "", name = "John Stones", id = 2202020)
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                onManualInputPressed()
            } ,
            modifier = Modifier
                .height(35.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = BlueJade),
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues(horizontal = 11.dp)
        ){
            Text(
                text = stringResource(id = R.string.corporate_input_manual),
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight(600),
                    fontSize = 14.sp,
                    letterSpacing = 1.sp,
                    color = Color.White
                ),
            )
        }
        Button(
            onClick = {
                onButtonBackPressed()
            },
            modifier = Modifier
                .width(89.dp)
                .height(35.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = SecondaryCorporate),
            shape = RoundedCornerShape(10.dp),
            contentPadding = PaddingValues(horizontal = 11.dp)
        ) {
            Text(
                text = stringResource(id = R.string.corporate_back ),
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight(600),
                    fontSize = 14.sp,
                    letterSpacing = 1.sp,
                    color = Color.White
                ),
                modifier = Modifier.padding(5.dp)
            )
        }
    }
}