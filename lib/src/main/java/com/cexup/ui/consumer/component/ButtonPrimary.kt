package com.cexup.ui.consumer.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cexup.ui.consumer.screen.auth.TAG_BTN_SIGN_IN
import com.cexup.ui.consumer.theme.ConsumerTheme
import com.cexup.ui.utils.mediaquery.from

@Composable
fun ButtonPrimary(
    text:String="",
    enabled:Boolean=true,
    modifier:Modifier=Modifier,
    onClick:()->Unit={},

){
    val ctx = LocalContext.current
    Button(
        onClick = onClick,
        enabled=enabled,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primary,
            disabledBackgroundColor = MaterialTheme.colors.secondary
        ),
        shape = RoundedCornerShape(8.dp.from(ctx)),
        modifier = modifier
            .testTag(TAG_BTN_SIGN_IN)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp.from(ctx)))
            .height(48.dp.from(ctx))
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.h1.copy(
                fontSize = 16.sp.from(ctx),
                lineHeight = 20.sp.from(ctx),
                fontWeight = FontWeight.Medium,
                color = if(enabled) Color.White else Color.Gray
            )
        )
    }
}

@Preview
@Composable
fun PreviewButtons() {
    ConsumerTheme {
        Column {
            ButtonPrimary(
                text = "Sign In"
            )
            Spacer(modifier = Modifier.height(20.dp))
            ButtonPrimary(
                text = "Sign In",
                enabled = false
            )
        }
    }
}